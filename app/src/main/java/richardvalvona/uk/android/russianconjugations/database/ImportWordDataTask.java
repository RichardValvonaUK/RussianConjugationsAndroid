package richardvalvona.uk.android.russianconjugations.database;

import android.app.ProgressDialog;
import android.content.ContentValues;
import android.content.Context;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteException;
import android.os.AsyncTask;
import android.provider.ContactsContract;
import android.util.JsonReader;
import android.util.Log;
import android.widget.Toast;

import org.json.JSONArray;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.lang.reflect.Array;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.Iterator;

import richardvalvona.uk.android.russianconjugations.myapplication.MainActivity;
import richardvalvona.uk.android.russianconjugations.myapplication.MyPreferences;
import richardvalvona.uk.android.russianconjugations.myapplication.MyProgressDialog;
import richardvalvona.uk.android.russianconjugations.myapplication.R;

/**
 * Created by richard on 17/04/17.
 */
class ImportWordDataTask extends AsyncTask<Void, Object, Integer> {

    private final static String UNIQUE_DATA_DOWNLOAD_KEY = "uk.richardvalvona.PJKDVRJWL2391367909393";

    private DatabaseOperations databaseOperations;
    private final Context context;
    private final ProgressDialog progressUpdater;

    private final SQLiteDatabase db;

    ImportWordDataTask(DatabaseOperations databaseOperations, Context context) {
        this.databaseOperations = databaseOperations;
        this.context = context;

        db = databaseOperations.getWritableDatabase();

        this.progressUpdater = new MyProgressDialog(context, "Starting data download...", "Please be patient...");
        ;
    }




    public long readLineIntoTable(String[] externamColumnNames, String[] words, DatabaseTable table, ContentValues values, DatabaseTagGenerator dGenerator) {
        int searchCount = words.length < externamColumnNames.length ? words.length : externamColumnNames.length;

        if (words.length < searchCount) {
            searchCount = words.length;
        }

        for (int i=0; i<searchCount; i++) {
            String nextWordTrim = words[i].trim();
            values.put(externamColumnNames[i].trim(), nextWordTrim);
            dGenerator.addEntryIfCanMatch(i, table.lang.removeVowelsSimplifyConsonants(nextWordTrim));
        }

        values.put(table.getTagColumn().name, dGenerator.getTag());
        return db.insert(table.name, null, values);
    }


    private static boolean isValidConnection(URL url, HttpURLConnection connection, int responseCode) throws IOException {
        return 200 <= responseCode && responseCode < 300 && url.getHost().equals(connection.getURL().getHost());
    }

    private static HttpURLConnection createDbConnection(URL url) throws IOException {
        HttpURLConnection connection = (HttpURLConnection) url.openConnection();
        connection.setRequestMethod("GET");
        connection.setInstanceFollowRedirects(false);
        connection.connect();
        return connection;
    }


    private static int getStatusFromResponseCode(int responseCode) {
        if (300 <= responseCode && responseCode < 400) {
            return DatabaseOperations.UNSUCCESSFUL_URL_REDIRECT;
        }
        else if (200 <= responseCode && responseCode < 300) {
            return DatabaseOperations.SUCCESSFUL;
        }

        return DatabaseOperations.UNSUCCESSFUL;
    }

    @Override
    protected void onPreExecute() {
        progressUpdater.show();
        progressUpdater.setMax(3456);
        progressUpdater.setProgress(0);
    }

    private class WordTypeInfo {
        public final String[] columnNames;
        public final ArrayList<String[]> data;
        public final int numberOfRows;
        public DatabaseTable dbTable;

        private WordTypeInfo(String[] columnNames, ArrayList<String[]> data, int numberOfRows, DatabaseTable dbTable) {
            this.columnNames = columnNames;
            this.data = data;
            this.numberOfRows = numberOfRows;
            this.dbTable = dbTable;
        }

        public boolean isValidData() {
            Log.d("numberOfRows", "" + numberOfRows);
            Log.d("data.size()", "" + data.size());
            Log.d("columnNames.length", "" + columnNames.length);
            if (numberOfRows > -1 && data.size() == numberOfRows && columnNames.length > 0) {
                return true;
            }

            return false;
        }
    }

    private WordTypeInfo extractInformationForWordType(JsonReader jReader, final DatabaseTable dbTable) throws IOException {

        int rowCount = -1;
        String[] fields = null;
        ArrayList<String[]> allData = new ArrayList<>();

        jReader.beginObject();

        while (jReader.hasNext()) {
            final String name = jReader.nextName();

            // The number of rows expected from the table
            if ("data-count".equals(name)) {
                rowCount = jReader.nextInt();
                allData.ensureCapacity(rowCount);
                publishProgress(DatabaseOperations.PROGRESS_SET_MAX, rowCount);
            }
            // Gets all fields for this table
            else if ("fields".equals(name)) {
                ArrayList<String> fieldsArrayList = new ArrayList<>();

                jReader.beginArray();
                while (jReader.hasNext()) {
                    fieldsArrayList.add(jReader.nextString());
                }
                fields = fieldsArrayList.toArray(new String[fieldsArrayList.size()]);
                jReader.endArray();
            }
            // Gets the actual data for this table
            else if ("data".equals(name)) {
                int rowIndex = 0;
                int rowIndexMod = 0;
                ArrayList<String> firstRow = null;
                jReader.beginArray();
                while (jReader.hasNext()) {

                    /** The first row uses an arraylist to determine the number of fields
                     * in this row assuming the order isn't important when extracting the
                     * data and fields.
                     */
                    if (firstRow == null) {
                        firstRow = new ArrayList<>();
                        jReader.beginArray();
                        while (jReader.hasNext()) {
                            firstRow.add(jReader.nextString());
                        }
                        String[] row = firstRow.toArray(new String[firstRow.size()]);
                        allData.add(row);
                        jReader.endArray();
                    }
                    /** Once the first row is created, the number of fields for this row is
                     * known and therefore it's more efficient to create the row as an
                     * array directly.
                     */
                    else {
                        String[] row = new String[firstRow.size()];
                        int positionInRow = 0;

                        jReader.beginArray();
                        while (jReader.hasNext()) {
                            row[positionInRow++] = jReader.nextString();
                        }
                        allData.add(row);
                        jReader.endArray();
                    }

                    // Updating display for user.
                    if (rowIndexMod == 0) {
                        if (rowCount > -1) {
                            publishProgress(DatabaseOperations.PROGRESS_UPDATE_LINES_READ, rowIndex);
                        }
                    }

                    // Increments
                    if (rowIndexMod == 99) {
                        rowIndexMod = 0;
                    }
                    else rowIndexMod++;
                    rowIndex++;
                }
                jReader.endArray();
            }
            else {
                jReader.skipValue();
            }
        }

        jReader.endObject();

        return new WordTypeInfo(fields, allData, rowCount, dbTable);
    }

    @Override
    protected Integer doInBackground(Void... noValues) {

        int status = DatabaseOperations.UNSUCCESSFUL;

        WordTypeInfo nounsInfo = null;
        WordTypeInfo adjectivesInfo = null;
        WordTypeInfo verbsInfo = null;

        boolean dataVerifiedToBeCorrect = false;

        JsonReader jReader = null;
        HttpURLConnection urlConnection = null;

        // Downloading the word data and placing them into appropriate arrays for each word type.
        try {
            URL url = new URL("http://richardvalvona.uk/ajax/get-russian-word-data");
            urlConnection = createDbConnection(url);

            InputStreamReader in = new InputStreamReader(urlConnection.getInputStream(), "UTF-8");

            jReader = new JsonReader(in);

            jReader.beginObject();
                while (jReader.hasNext()) {
                    final String name = jReader.nextName();

                    if ("uniqueKey".equals(name)) {
                        final String uniqueKey = jReader.nextString();
                        if (uniqueKey.equals(UNIQUE_DATA_DOWNLOAD_KEY)) {
                            dataVerifiedToBeCorrect = true;
                        }
                    }
                    else if ("nouns".equals(name)) {
                        publishProgress(DatabaseOperations.PROGRESS_SET_TITLE_AND_MESSAGE, "Downloading Nouns...", "Please wait...");
                        nounsInfo = extractInformationForWordType(jReader, databaseOperations.nounsTable);
                    }
                    else if ("adjectives".equals(name)) {
                        publishProgress(DatabaseOperations.PROGRESS_SET_TITLE_AND_MESSAGE, "Downloading Adjectives...", "Please wait...");
                        adjectivesInfo = extractInformationForWordType(jReader, databaseOperations.adjectivesTable);
                    }
                    else if ("verbs".equals(name)) {
                        publishProgress(DatabaseOperations.PROGRESS_SET_TITLE_AND_MESSAGE, "Downloading Verbs...", "Please wait...");
                        verbsInfo = extractInformationForWordType(jReader, databaseOperations.verbsTable);
                    }
                    else {
                        jReader.skipValue();
                    }
                }
            jReader.endObject();
        } catch (MalformedURLException e) {
            e.printStackTrace();
            dataVerifiedToBeCorrect = false;
        } catch (IOException e) {
            e.printStackTrace();
            dataVerifiedToBeCorrect = false;
        }
        finally {
            // Close the reader if possible
            if (jReader != null) {
                try {
                    jReader.close();
                } catch (IOException e) {
                    // Do nothing.
                }
            }

            if (urlConnection != null) urlConnection.disconnect();
        }

        // The data will only be added to the database if all of the data is verified to be valid.
        if (dataVerifiedToBeCorrect && nounsInfo.isValidData() && adjectivesInfo.isValidData() && verbsInfo.isValidData()) {

            WordTypeInfo[] allWordTypeInfos = new WordTypeInfo[] {nounsInfo, adjectivesInfo, verbsInfo};

            try {
                for (WordTypeInfo nextInfos : allWordTypeInfos) {

                    publishProgress(DatabaseOperations.PROGRESS_SET_TITLE_AND_MESSAGE, "Populating `" + nextInfos.dbTable.name + "` table", "Please wait...");

                    ArrayList<String[]> allRows = nextInfos.data;
                    Iterator<String[]> allRowsIt = allRows.iterator();

                    DatabaseTagGenerator dGenerator = new DatabaseTagGenerator(nextInfos.dbTable.getColumnNamesForMatching(), nextInfos.columnNames, 4);

                    db.execSQL(nextInfos.dbTable.getDropIndexIfExistsCode());
                    db.execSQL(nextInfos.dbTable.getDeleteAllRowsCode());

                    publishProgress(DatabaseOperations.PROGRESS_SET_MAX, nextInfos.numberOfRows);

                    int linesRead = 0;
                    int linesReadMod = 0;

                    while (allRowsIt.hasNext()) {
                        long insertPos = readLineIntoTable(nextInfos.columnNames, allRowsIt.next(), nextInfos.dbTable, new ContentValues(), dGenerator);
                        dGenerator.clearTag();

                        if (insertPos == -1) {
                            throw new SQLException();
                        }

                        linesRead++;

                        if (linesReadMod == 99) {
                            linesReadMod = 0;
                            publishProgress(DatabaseOperations.PROGRESS_UPDATE_LINES_READ, linesRead);
                        } else {
                            linesReadMod++;
                        }
                    }

                    db.execSQL(nextInfos.dbTable.getCreateIndex(nextInfos.dbTable.getTagColumn()));
                }

                status = DatabaseOperations.SUCCESSFUL;
                MyPreferences.getInstance().saveBoolean(context, MyPreferences.DATA_PROPERLY_IN_DATABASE, true);
            }
            catch (SQLException e) {
                status = DatabaseOperations.UNSUCCESSFUL;
            }
            finally {
                if (urlConnection != null) {
                    urlConnection.disconnect();
                }
            }
        }

        // Close the database if it exists
        if (db != null) {
            db.close();
        }

        return status;
    }

    @Override
    protected void onPostExecute(Integer downloadedDataSuccessfully) {
        progressUpdater.hide();

        MainActivity mainActivity = MainActivity.getInstance();

        switch (downloadedDataSuccessfully) {
            case DatabaseOperations.SUCCESSFUL:
                if (mainActivity != null) mainActivity.switchMainContentView(R.id.with_data_startup);
                Toast.makeText(context, "Words downloaded successfully!", Toast.LENGTH_SHORT).show();
                break;
            case DatabaseOperations.UNSUCCESSFUL:
                if (mainActivity != null) mainActivity.switchToDownloadingDataView(R.string.downloading_data_failed_msg);
                Toast.makeText(context, "Couldn't download the dictionary. Error downloading the data!", Toast.LENGTH_LONG).show();
                break;
        }
    }

    @Override
    protected void onProgressUpdate(Object... values) {

        switch ((Integer) values[0]) {
            case DatabaseOperations.PROGRESS_UPDATE_LINES_READ: {
                progressUpdater.setProgress((int) values[1]);
                break;
            }
            case DatabaseOperations.PROGRESS_SET_MAX: {
                progressUpdater.setMax((int) values[1]);
                break;
            }
            case DatabaseOperations.PROGRESS_SET_TITLE_AND_MESSAGE: {
                progressUpdater.setTitle((String) values[1]);
                progressUpdater.setMessage((String) values[2]);
                break;
            }
        }
    }

    @Override
    protected void onCancelled(Integer s) {
        super.onCancelled(s);
    }
}
