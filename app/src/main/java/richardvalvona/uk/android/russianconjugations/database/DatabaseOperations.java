package richardvalvona.uk.android.russianconjugations.database;

import android.app.Activity;
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.util.ArrayList;

import richardvalvona.uk.android.russianconjugations.languages.Language;
import richardvalvona.uk.android.russianconjugations.languages.Word;

/**
 * Created by richard on 29/10/16.
 */

public class DatabaseOperations extends SQLiteOpenHelper {

    public final static int UNSUCCESSFUL = -1;
    public final static int UNSUCCESSFUL_INTERNET_ERROR = -2;
    public final static int UNSUCCESSFUL_URL_REDIRECT = -3;
    public final static int SUCCESSFUL = 0;

    private static final String DATABASE_NAME = "richardvalvona_uk_russianconjugations";
    private static final int DATABASE_VERSION = 1;

    public static final int PROGRESS_UPDATE_LINES_READ = 0;
    public static final int PROGRESS_SET_MAX = 1;
    public static final int PROGRESS_SET_TITLE_AND_MESSAGE = 2;

    private static DatabaseOperations database = null;

    public final AdjectivesTable adjectivesTable = new AdjectivesTable();
    public final NounsTable nounsTable = new NounsTable();
    public final VerbsTable verbsTable = new VerbsTable();

    private boolean onCreateCalled = false;

    public static DatabaseOperations getHelper(Activity activity) {
        if (database == null) {
            database = new DatabaseOperations(activity);
        }

        return database;
    }

    private DatabaseOperations(Activity context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

//
//    public void ensureDatabaseExists(Context context) {
//        SQLiteDatabase mydatabase = context.openOrCreateDatabase(database.databaseName, Context.MODE_PRIVATE, null);
//    }



    @Override
    public void onCreate(SQLiteDatabase db) {

        DatabaseTable[] allTables = new DatabaseTable[]{nounsTable, adjectivesTable, verbsTable};

        for (DatabaseTable table : allTables) {
            db.execSQL(table.getDropTableCode());
            db.execSQL(table.generateCreateCode());
        }


        onCreateCalled = true;
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        onCreate(db);
    }

    @Override
    public void onDowngrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        super.onDowngrade(db, oldVersion, newVersion);
    }

    public void addDataToDatabase(Context context) {

        final ImportWordDataTask task = new ImportWordDataTask(this, context);
        task.execute();
    }

    public ArrayList<Word> selectRowsUsingEnglishWords(DatabaseTable table, DatabaseColumn englishWordsColumn, String searchString) {

        // All characters other than English latin letters and spaces are removed
        searchString = Language.retainLatinCharsAndSpacesOnly(searchString);

        SQLiteDatabase db = getReadableDatabase();

        StringBuilder sb = new StringBuilder("SELECT ");

        int numberOfColumns = table.columns.size();

        sb.append(table.getColumnsSeperatedByCommas());
        sb.append(" FROM "); sb.append(table.name);

        int numberOfAppends = Math.min(searchString.length(), Language.MAX_NUMBER_OF_LETTERS_IN_TAG);


        if (searchString.length() == 0) {
            sb.append(" LIMIT 20");
        }
        else if (searchString.length() == 1) {
            sb.append(" WHERE ");
            sb.append(englishWordsColumn.name);
            sb.append(" LIKE ");
            sb.append("'%[[" + searchString + "]]%'");
            sb.append(" LIMIT 50");
        }
        else {
            sb.append(" WHERE ");
            sb.append(englishWordsColumn.name);
            sb.append(" LIKE ");
            sb.append("'%[[" + searchString + "%'");
        }

        Cursor cursor = db.rawQuery(sb.toString(), null);

        if (cursor != null && cursor.moveToFirst()) {

            int cursorCount = cursor.getCount();
            /*
                The cursor count should be correct, but in case it's -1 or incorrect then a
                safety mechanism is put in place for the capacity of the arraylist.
             */
            ArrayList<Word> allWords = new ArrayList<>(cursorCount > 1 ? cursorCount : 1000);

            do {
                String[] allForms = new String[numberOfColumns];
                for(int i=0; i<numberOfColumns; i++) {
                    allForms[i] = cursor.getString(i);
                }

                Word w = new Word(table, allForms);
                allWords.add(w);

            } while (cursor.moveToNext());

            return allWords;
        }

        return new ArrayList<>(0);
    }

    public ArrayList<Word> selectRowsUsingRussianTags(DatabaseTable table, String searchString) {
        SQLiteDatabase db = getReadableDatabase();

        DatabaseColumn tagColumn = table.getTagColumn();

        StringBuilder sb = new StringBuilder("SELECT ");

        int numberOfColumns = table.columns.size();

        sb.append(table.getColumnsSeperatedByCommas());
        sb.append(" FROM "); sb.append(table.name); sb.append(" WHERE "); sb.append(tagColumn.name);
        sb.append(" = ''");

        int numberOfAppends = Math.min(searchString.length(), Language.MAX_NUMBER_OF_LETTERS_IN_TAG);

        String[] valuesToMatch = new String[numberOfAppends];

        for (int i=1; i<=numberOfAppends; i++) {
            sb.append(" OR "); sb.append(tagColumn.name); sb.append(" = ?");

            valuesToMatch[i-1] = searchString.substring(0, i);
        }

//        sb.append(" LIMIT 50");

        Cursor cursor = db.rawQuery(sb.toString(), valuesToMatch);

        if (cursor != null && cursor.moveToFirst()) {

            int cursorCount = cursor.getCount();
            /*
                The cursor count should be correct, but in case it's -1 or incorrect then a
                safety mechanism is put in place for the capacity of the arraylist.
             */
            ArrayList<Word> allWords = new ArrayList<>(cursorCount > 1 ? cursorCount : 1000);

            do {
                String[] allForms = new String[numberOfColumns];
                for(int i=0; i<numberOfColumns; i++) {
                    allForms[i] = cursor.getString(i);
                }

                Word w = new Word(table, allForms);
                allWords.add(w);

            } while (cursor.moveToNext());

            return allWords;
        }

        return new ArrayList<>(0);
    }

    public boolean isOnCreateCalled() {
        return onCreateCalled;
    }

}
