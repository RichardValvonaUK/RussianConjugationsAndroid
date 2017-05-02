package richardvalvona.uk.android.russianconjugations.myapplication;

import android.content.Context;
import android.graphics.Color;
import android.graphics.Typeface;
import android.util.AttributeSet;
import android.widget.LinearLayout;
import android.widget.TextView;

import richardvalvona.uk.android.russianconjugations.database.AdjectivesTable;
import richardvalvona.uk.android.russianconjugations.database.DatabaseTable;
import richardvalvona.uk.android.russianconjugations.database.NounsTable;
import richardvalvona.uk.android.russianconjugations.languages.Word;
import richardvalvona.uk.android.russianconjugations.languages.WordWithInfo;

/**
 * Created by richard on 10/11/16.
 */

public class ResultHolder extends LinearLayout {

    private int backgroundColor = Color.rgb(255, 255, 0);
    private int foregroundColor = createContrastColor(backgroundColor);
    private int textColor = Color.parseColor("#222222");

    public static int createContrastColor(int inputColor) {

        int r = Color.red(inputColor);
        int g = Color.green(inputColor);
        int b = Color.blue(inputColor);

        int brightness = (r+g+b) / 3;

        if (brightness < 128) {
            return Color.rgb(249, 249, 249);
        }
        else {
            return Color.rgb(16, 16, 16);
        }
    }

    public ResultHolder(Context context) {
        super(context);
    }

    public ResultHolder(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public ResultHolder(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    public void setWordResult(WordWithInfo wordWithInfo) {
        removeAllViews();

        if (wordWithInfo == null) {

        }
        else if (wordWithInfo instanceof WordWithInfo) {
            String tableName = wordWithInfo.word.table.name;

            Context context = getContext();

            if (tableName.equals(DatabaseTable.TABLE_NAME_NOUNS)) {
                setWordResultForNoun(wordWithInfo.word, context);
            }
        }
    }

    private void setWordResultForNoun(Word word, Context context) {
        addView(createNounHeading(word, context));
        addView(createNounFormsGrid(word, context));
    }

    private WordGrid createAdjectiveHeading(Word word, Context context) {

        AdjectivesTable table = (AdjectivesTable) word.table;

        WordGrid wordGrid = new WordGrid(context);
        wordGrid.setNumberOfRows(1);
        wordGrid.setColumnWeights(new float[]{7,2});

        ModifiedTextView dictFormText = new ModifiedTextView(context);
        ModifiedTextView genderText = new ModifiedTextView(context);

        // Setting main word
        dictFormText.setTextSize(28);

        String wordWithAccent = word.table.getDictForm(word);
        String allMainWord = "<b>" + wordWithAccent + "</b> (noun)";

        dictFormText.setText(wordWithAccent);

        // Adding text views to grid
        wordGrid.addViewToRow(dictFormText, 0);

        return wordGrid;
    }


    private WordGrid createNounHeading(Word word, Context context) {

        NounsTable table = (NounsTable) word.table;

        WordGrid wordGrid = new WordGrid(context);
        wordGrid.setNumberOfRows(1);
        wordGrid.setColumnWeights(new float[]{7,2});

        ModifiedTextView dictFormText = new ModifiedTextView(context);
        ModifiedTextView genderText = new ModifiedTextView(context);

        // Setting main word
        dictFormText.setTextSize(28);

        String wordWithAccent = Word.mergeAccent(word.table.getDictForm(word));
        String allMainWord = "<b>" + wordWithAccent + "</b> (noun)";

        dictFormText.setText(wordWithAccent);

        // Setting gender text
        int gender = table.getGender(word);

        switch(gender) {
            case NounsTable.GENDER_M: genderText.setText("masc."); break;
            case NounsTable.GENDER_F: genderText.setText("fem."); break;
            case NounsTable.GENDER_N: genderText.setText("neut."); break;
            default: genderText.setText(""); break;
        }

        // Adding text views to grid
        wordGrid.addViewToRow(dictFormText, 0);
        wordGrid.addViewToRow(genderText, 0);


        return wordGrid;
    }

    private LinearLayout createIntroGrid(Context context) {
        LinearLayout introGrid = new LinearLayout(context);

        ModifiedTextView heading = new ModifiedTextView(context);

        return introGrid;
    }

    private WordGrid createNounFormsGrid(Word word, Context context) {

        NounsTable table = (NounsTable) word.table;

        WordGrid wordGrid = new WordGrid(context);
        wordGrid.setBackgroundColor(Color.parseColor("#ffe99b"));

        final int numberOfRows = 7;
        final int numberOfColumns = 3;

        wordGrid.setNumberOfRows(numberOfRows);
        wordGrid.setColumnWeights(new float[]{0,1,1});

        // Defining the grid row indices for each case
        final int HEADING = 0;
        final int CASE_N = 1;
        final int CASE_A = 2;
        final int CASE_G = 3;
        final int CASE_D = 4;
        final int CASE_I = 5;
        final int CASE_P = 6;

        // Defining the grid column indices for noun number
        final int CASE_LABELS = 0;
        final int NUMBER_S = 1;
        final int NUMBER_P = 2;

        ModifiedTextView[][] grid = new ModifiedTextView[numberOfRows][numberOfColumns];

        // Creating all ModifiedTextViews and making all headings and case labels bold first.
        for (int i=0; i<numberOfRows; i++) {

            for (int j=0; j<numberOfColumns; j++) {
                ModifiedTextView nextEntry = new ModifiedTextView(context);

                grid[i][j] = nextEntry;

                if (i==0 || j==0) {
                    nextEntry.setTypeface(nextEntry.getTypeface(), Typeface.BOLD);
                }
                nextEntry.setTextSize(16f);

                wordGrid.addViewToRow(nextEntry, i);
            }
        }

        grid[HEADING][CASE_LABELS].setText("Case");
        grid[HEADING][NUMBER_S].setText("Singular");
        grid[HEADING][NUMBER_P].setText("Plural");

        grid[CASE_N][CASE_LABELS].setText("Nom");
        grid[CASE_A][CASE_LABELS].setText("Acc");
        grid[CASE_G][CASE_LABELS].setText("Gen");
        grid[CASE_D][CASE_LABELS].setText("Dat");
        grid[CASE_I][CASE_LABELS].setText("Ins");
        grid[CASE_P][CASE_LABELS].setText("Gen");

        for (int i=CASE_N; i<=CASE_P; i++) {

            for (int j = NUMBER_S; j <= NUMBER_P; j++) {

                int tableCaseIndex = i-1;
                int tableNumberIndex = j-1;

                int gender = ((NounsTable) word.table).getGender(word);
                boolean animate = ((NounsTable) word.table).isAnimate(word);

                int tableWordIndex = NounsTable.getColumnIndex(tableCaseIndex, tableNumberIndex, gender, animate, false);
                int tableWordAltIndex = NounsTable.getColumnIndex(tableCaseIndex, tableNumberIndex, gender, animate, true);

                String mainWord = word.getWordForm(tableWordIndex, true);
                String altWord = word.getWordForm(tableWordAltIndex, true);

                boolean mainWordExists = mainWord != null && mainWord.length() > 0;
                boolean altWordExists = altWord != null && altWord.length() > 0;

                String wordToPut;

                if (mainWordExists && altWordExists) wordToPut = mainWord + "\n" + altWord;
                else if (mainWordExists) wordToPut = mainWord;
                else if (altWordExists) wordToPut = altWord;
                else wordToPut = "";

                grid[i][j].setText(wordToPut);
            }
        }

        return wordGrid;
    }

    private WordGrid createAdjectiveFormsGrid(Word word, Context context) {

        NounsTable table = (NounsTable) word.table;


        WordGrid wordGrid = new WordGrid(context);

        final int numberOfRows = 7;
        final int numberOfColumns = 5;

        wordGrid.setNumberOfRows(numberOfRows);
        wordGrid.setColumnWeights(new float[]{0,1,1});

        // Defining the grid row indices for each case
        final int HEADING = 0;
        final int CASE_N = 1;
        final int CASE_A = 2;
        final int CASE_G = 3;
        final int CASE_D = 4;
        final int CASE_I = 5;
        final int CASE_P = 6;

        // Defining the grid column indices for noun number
        final int CASE_LABELS = 0;
        final int GENDER_M = 1;
        final int GENDER_F = 2;
        final int GENDER_N = 3;
        final int PLURAL = 4;

        ModifiedTextView[][] grid = new ModifiedTextView[numberOfRows][numberOfColumns];

        // Creating all ModifiedTextViews and making all headings and case labels bold first.
        for (int i=0; i<numberOfRows; i++) {

            for (int j=0; j<numberOfColumns; j++) {
                ModifiedTextView nextEntry = new ModifiedTextView(context);

                grid[i][j] = nextEntry;

                if (i==0 || j==0) {
                    nextEntry.setTypeface(nextEntry.getTypeface(), Typeface.BOLD);
                }
                nextEntry.setTextSize(16f);

                wordGrid.addViewToRow(nextEntry, i);
            }
        }

        grid[HEADING][CASE_LABELS].setText("Case");
        grid[HEADING][GENDER_M].setText("Masc");
        grid[HEADING][GENDER_F].setText("Fem");
        grid[HEADING][GENDER_N].setText("Neut");
        grid[HEADING][PLURAL].setText("Plur");

        grid[CASE_N][CASE_LABELS].setText("Nom");
        grid[CASE_A][CASE_LABELS].setText("Acc");
        grid[CASE_G][CASE_LABELS].setText("Gen");
        grid[CASE_D][CASE_LABELS].setText("Dat");
        grid[CASE_I][CASE_LABELS].setText("Ins");
        grid[CASE_P][CASE_LABELS].setText("Gen");

//        for (int i=CASE_N; i<=CASE_P; i++) {
//
//            for (int j = NUMBER_S; j <= NUMBER_P; j++) {
//
//                int tableCaseIndex = i-1;
//                int tableNumberIndex = j-1;
//
//                int gender = ((NounsTable) word.table).getGender(word);
//                boolean animate = ((NounsTable) word.table).isAnimate(word);
//
//                int tableWordIndex = NounsTable.getColumnIndex(tableCaseIndex, tableNumberIndex, gender, animate, false);
//                int tableWordAltIndex = NounsTable.getColumnIndex(tableCaseIndex, tableNumberIndex, gender, animate, true);
//
//                String mainWord = word.getWordForm(tableWordIndex, true);
//                String altWord = word.getWordForm(tableWordAltIndex, true);
//
//                boolean mainWordExists = mainWord != null && mainWord.length() > 0;
//                boolean altWordExists = altWord != null && altWord.length() > 0;
//
//                String wordToPut;
//
//                if (mainWordExists && altWordExists) wordToPut = mainWord + "\n" + altWord;
//                else if (mainWordExists) wordToPut = mainWord;
//                else if (altWordExists) wordToPut = altWord;
//                else wordToPut = "";
//
//                grid[i][j].setText(wordToPut);
//            }
//        }

        return wordGrid;
    }

    private class ModifiedTextView extends TextView {
        public ModifiedTextView(Context context) {
            super(context);

            setTextColor(textColor);
        }
    }
}
