package richardvalvona.uk.android.russianconjugations.myapplication;

import android.content.Context;
import android.os.AsyncTask;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.GestureDetector;
import android.view.MotionEvent;
import android.view.View;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.AdapterView;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.Spinner;
import android.widget.TextView;

import com.google.android.gms.ads.MobileAds;

import java.util.ArrayList;
import java.util.Iterator;

import richardvalvona.uk.android.russianconjugations.LightWord;
import richardvalvona.uk.android.russianconjugations.database.AdjectivesTable;
import richardvalvona.uk.android.russianconjugations.database.DatabaseOperations;
import richardvalvona.uk.android.russianconjugations.database.DatabaseTable;
import richardvalvona.uk.android.russianconjugations.database.NounsTable;
import richardvalvona.uk.android.russianconjugations.database.VerbsTable;
import richardvalvona.uk.android.russianconjugations.languages.Language;
import richardvalvona.uk.android.russianconjugations.languages.RussianWordMatcherFromEnglish;
import richardvalvona.uk.android.russianconjugations.languages.Word;
import richardvalvona.uk.android.russianconjugations.languages.RussianWordMatcher;
import richardvalvona.uk.android.russianconjugations.languages.WordMatcher;
import richardvalvona.uk.android.russianconjugations.languages.WordResultSet;
import richardvalvona.uk.android.russianconjugations.languages.WordWithInfo;

public class MainActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {

    private static MainActivity instance = null;

    ListView resultsList = null;
    ResultsListAdapter resultsListAdapter = null;
    DatabaseOperations dbImporter;
    private GestureDetector.SimpleOnGestureListener myGestureDetector;
    DrawerLayout drawer;

    private EditText searchTextInputBox;
    private TextView cyrillicInputDisplayBox;
    private Spinner wordTypesSelector;
    private String lastSearchText;

    public static MainActivity getInstance() {
        return instance;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        instance = this;

        setContentView(R.layout.activity_main);

        MobileAds.initialize(getApplicationContext(), getString(R.string.ad_unit_id));

        dbImporter = DatabaseOperations.getHelper(this);

        resultsList = (ListView) findViewById(R.id.searchResultsList);

        resultsListAdapter = new ResultsListAdapter(this, new ArrayList<AbstractResultsListEntry>());
        resultsList.setAdapter(resultsListAdapter);

        wordTypesSelector = (Spinner) findViewById(R.id.wordTypesSelector);

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
//
//        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
//        fab.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
//                        .setAction("Action", null).show();
//            }
//        });

        drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.setDrawerListener(toggle);
        toggle.syncState();

        searchTextInputBox = (EditText) findViewById(R.id.searchTextInput);
        cyrillicInputDisplayBox = (TextView) findViewById(R.id.russianCyrillicDisplay);

        searchTextInputBox.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {}

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {}

            @Override
            public void afterTextChanged(Editable editable) {

                String wordToSearch = getWordToSearch();

                if (wordToSearch.equals("")) {
                    setCyrillicDisplayBoxText("");
                }
                else if (Language.containsLatinLetters(wordToSearch)) {
                    setCyrillicDisplayBoxText(Language.tranliterateLatinToCyrillic(wordToSearch));
                    cyrillicInputDisplayBox.setVisibility(View.VISIBLE);
                }
                else {
                    cyrillicInputDisplayBox.setVisibility(View.GONE);
                }

                new Thread(new Runnable() {
                    @Override
                    public void run() {
                        doSearchOnWord();
                    }
                }).start();
            }
        });


        wordTypesSelector.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                doSearchOnWord();
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {}
        });

//        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
//        navigationView.setNavigationItemSelectedListener(this);


        // If the data isn't successfully in database then make sure it is.
        downloadDataFromInternetIfApplicable();

//        AdView adView = (AdView) findViewById(R.id.adView);
//
//
//        final String DEVICE_ID = Settings.Secure.getString(this.getContentResolver(),
//                Settings.Secure.ANDROID_ID);
//
//        AdRequest adRequest = new AdRequest.Builder()
//                .addTestDevice(AdRequest.DEVICE_ID_EMULATOR)
//                .addTestDevice(DEVICE_ID)
//                .build();
//
//        adView.loadAd(adRequest);
    }


    public void downloadDataFromInternetIfApplicable() {
        boolean dataSuccessfullyAdded = MyPreferences.getInstance().getBoolean(this, MyPreferences.DATA_PROPERLY_IN_DATABASE, false);

        if (!dataSuccessfullyAdded) {
            switchMainContentView(R.id.downloading_data_view);
            dbImporter.addDataToDatabase(MainActivity.this);
        }
    }


    @Override
    public void onBackPressed() {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();

        if (id == R.id.nav_camera) {
            // Handle the camera action
        } else if (id == R.id.nav_gallery) {

        } else if (id == R.id.nav_slideshow) {

        } else if (id == R.id.nav_manage) {

        } else if (id == R.id.nav_share) {

        } else if (id == R.id.nav_send) {

        }

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }

    public void searchButtonClicked(View v) {
        new Thread(new Runnable() {
            @Override
            public void run() {
                doSearchOnWord();
            }
        }).start();
    }

    private String getWordToSearch() {
        return searchTextInputBox.getText().toString().trim().toLowerCase();
    }

    private void setCyrillicDisplayBoxText(String str) {
        cyrillicInputDisplayBox.setText(str);
    }

    private void doSearchOnWord() {

        String selectedItem = ((String) wordTypesSelector.getSelectedItem()).toLowerCase();

        // For searching English words
        if (selectedItem.equals("english words")) {
            String searchText = Language.retainLatinCharsAndSpacesOnly(getWordToSearch());
            lastSearchText = searchText;

            ArrayList<Word>[] allWordGroups = null;

            ArrayList<Word> allAdjectives = dbImporter.selectRowsUsingEnglishWords(dbImporter.adjectivesTable, dbImporter.adjectivesTable.getColumnAt(AdjectivesTable.COLUMN_INDEX_ENGLISH_WORDS), searchText);
            ArrayList<Word> allNouns = dbImporter.selectRowsUsingEnglishWords(dbImporter.nounsTable, dbImporter.nounsTable.getColumnAt(NounsTable.COLUMN_INDEX_ENGLISH_WORDS), searchText);
            ArrayList<Word> allVerbs = dbImporter.selectRowsUsingEnglishWords(dbImporter.verbsTable, dbImporter.verbsTable.getColumnAt(VerbsTable.COLUMN_INDEX_ENGLISH_WORDS), searchText);

            allWordGroups = new ArrayList[]{ allAdjectives, allNouns, allVerbs };

            LightWord lWord = new LightWord(Language.noLanguage, searchText);

            // Ensures no jumping twice when typing.
            if (lastSearchText == searchText) {
                MatcherTask matcherTask = new MatcherTask(this, allWordGroups, lWord, Language.russian, new RussianWordMatcherFromEnglish(), null);
                matcherTask.execute();
            }
        }
        // For searching Russian words
        else {

            String searchTextCyrillic = Language.tranliterateLatinToCyrillic(getWordToSearch());

            lastSearchText = searchTextCyrillic;

            char[] altChars;
            if (searchTextCyrillic.equals("")) {
                altChars = new char[0];
            } else {
                altChars = Language.russian.getAlternativeChars(searchTextCyrillic.charAt(0));
            }

            LightWord lWord = new LightWord(Language.russian, searchTextCyrillic);

            String wordWithoutVowelsSimplifiedConsonants = Language.russian.removeVowelsSimplifyConsonants(lWord.wordWithoutPronunciation);

            ArrayList<Word>[] allWordGroups = null;
            if (selectedItem.equals("all words")) {
                ArrayList<Word> allAdjectives = dbImporter.selectRowsUsingRussianTags(dbImporter.adjectivesTable, wordWithoutVowelsSimplifiedConsonants);
                ArrayList<Word> allNouns = dbImporter.selectRowsUsingRussianTags(dbImporter.nounsTable, wordWithoutVowelsSimplifiedConsonants);
                ArrayList<Word> allVerbs = dbImporter.selectRowsUsingRussianTags(dbImporter.verbsTable, wordWithoutVowelsSimplifiedConsonants);

                allWordGroups = new ArrayList[]{ allAdjectives, allNouns, allVerbs };
            }
            else if (selectedItem.equals("only nouns")) {
                ArrayList<Word> allNouns = dbImporter.selectRowsUsingRussianTags(dbImporter.nounsTable, wordWithoutVowelsSimplifiedConsonants);

                allWordGroups = new ArrayList[]{ allNouns };
            }
            else if (selectedItem.equals("only adjectives")) {
                ArrayList<Word> allAdjectives = dbImporter.selectRowsUsingRussianTags(dbImporter.adjectivesTable, wordWithoutVowelsSimplifiedConsonants);

                allWordGroups = new ArrayList[]{ allAdjectives };
            }
            else if (selectedItem.equals("only verbs")) {
                ArrayList<Word> allVerbs = dbImporter.selectRowsUsingRussianTags(dbImporter.verbsTable, wordWithoutVowelsSimplifiedConsonants);

                allWordGroups = new ArrayList[]{ allVerbs };
            }

            if (allWordGroups != null) {
                // Ensures no jumping twice when typing.
                if (lastSearchText == searchTextCyrillic) {
                    MatcherTask matcherTask = new MatcherTask(this, allWordGroups, lWord, Language.russian, new RussianWordMatcher(), altChars);
                    matcherTask.execute();
                }
            }
        }
    }



    public void switchToDownloadingDataView(int msg) {
        View v = switchMainContentView(R.id.downloading_data_view);

        TextView messageView = (TextView) v.findViewById(R.id.downloading_data_text_view);
        messageView.setText(msg);
    }

    public View switchMainContentView(int viewId) {
        LinearLayout mainContent = (LinearLayout) findViewById(R.id.main_content);

        int numberOfChildren = mainContent.getChildCount();

        View viewToReturn = null;

        for (int i=0; i<numberOfChildren; i++) {
            View nextChild = mainContent.getChildAt(i);
            if (nextChild.getId() == viewId) {
                viewToReturn = nextChild;
                nextChild.setVisibility(View.VISIBLE);
            }
            else {
                nextChild.setVisibility(View.GONE);
            }
        }

        return viewToReturn;
    }

    public void displayResult(WordWithInfo wordWithInfo) {
        drawer.closeDrawers();

        switchMainContentView(R.id.result_holder);

        ResultHolder resultHolder = (ResultHolder) findViewById(R.id.result_holder);
        resultHolder.setWordResult(wordWithInfo);
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        return super.onTouchEvent(event);
    }

    public void pauseDataDownloadIfRunning() {

    }

    private class MatcherTask extends AsyncTask<Void, Integer, WordResultSet> {

        private final ArrayList<Word>[] allWordGroups;
        private final LightWord lWord;
        private final Context context;
        private final Language lang;
        private final WordMatcher matcher;
        private final char[] altChars;

        MatcherTask(Context context, ArrayList<Word>[] allWordGroups, LightWord lWord, Language lang, WordMatcher matcher, char[] altChars) {
            this.context = context;
            this.allWordGroups = allWordGroups;
            this.lWord = lWord;
            this.lang = lang;
            this.matcher = matcher;
            this.altChars = altChars;
        }


        @Override
        protected WordResultSet doInBackground(Void... voids) {
            WordResultSet resultSet = new WordResultSet(RussianWordMatcher.MATCH_TYPE_NO_MATCH);

            for (int wordGroupsIndex=0; wordGroupsIndex<allWordGroups.length; wordGroupsIndex++) {
                ArrayList<Word> nextWordGroup = allWordGroups[wordGroupsIndex];

                Iterator<Word> nextWordGroupIt = nextWordGroup.iterator();
                while (nextWordGroupIt.hasNext()) {
                    Word nextWord = nextWordGroupIt.next();
                    int matchType = matcher.getMatchness(nextWord, lWord, altChars);
                    int lastWordFormsIndex = matcher.getLastWordFormsIndex();

                    resultSet.addWordResult(nextWord, matchType, lastWordFormsIndex);
                }
            }

            return resultSet;
        }

        @Override
        protected void onPostExecute(WordResultSet resultSet) {
            WordWithInfo[] bestResults = resultSet.getAllWordsInOrderOfResultIndex(20);

            resultsListAdapter.clear();

            for (int i=0; i<bestResults.length; i++) {
                WordWithInfo nextResult = bestResults[i];
                if (nextResult.word.table.name == DatabaseTable.TABLE_NAME_ADJECTIVES) {
                    resultsListAdapter.add(new ResultsListAdjectiveEntry(nextResult));
                }
                else if (nextResult.word.table.name == DatabaseTable.TABLE_NAME_NOUNS) {
                    resultsListAdapter.add(new ResultsListNounEntry(nextResult));
                }
                else if (nextResult.word.table.name == DatabaseTable.TABLE_NAME_VERBS) {
                    resultsListAdapter.add(new ResultsListVerbEntry(nextResult));
                }

            }
        }
    }
}
