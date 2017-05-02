package richardvalvona.uk.android.russianconjugations.database;

import java.util.HashSet;

import richardvalvona.uk.android.russianconjugations.languages.Language;
import richardvalvona.uk.android.russianconjugations.languages.Word;

/**
 * Created by richard on 29/10/16.
 */

public abstract class DatabaseTable {

    public final static int GENDER_UNKNOWN = 0;
    public final static int GENDER_M = 1;
    public final static int GENDER_F = 2;
    public final static int GENDER_N = 3;

    public final static int CASE_N = 0;
    public final static int CASE_A = 1;
    public final static int CASE_G = 2;
    public final static int CASE_D = 3;
    public final static int CASE_I = 4;
    public final static int CASE_P = 5;

    public final static int NUMBER_S = 0;
    public final static int NUMBER_P = 1;

    public final static String TABLE_NAME_NOUNS = "nouns";
    public final static String TABLE_NAME_ADJECTIVES = "adjectives";
    public final static String TABLE_NAME_VERBS = "verbs";

    protected final static int DEFAULT_LENGTH = 40;

    public final String name;
    public final AssignedArrayList<DatabaseColumn> columns;
    public final Language lang;

    DatabaseTable(String name, AssignedArrayList<DatabaseColumn> columns, Language lang) {
        this.name = name;
        this.columns = columns;
        this.lang = lang;

        setThisTableForAllColumns();
    }

    protected void setThisTableForAllColumns() {
        for(int i=0; i<columns.size(); i++) {
            columns.getItem(i).setTable(this);
            columns.getItem(i).setIndex(i);
        }
    }

    public String getColumnsSeperatedByCommas() {

        StringBuilder sb = new StringBuilder();

        for (int i=0; i<columns.size(); i++) {
            DatabaseColumn nextColumn = columns.getItem(i);

            if (i > 0) {
                sb.append(',');
            }

            sb.append(nextColumn.name);
        }

        return sb.toString();
    }

    public String generateCreateCode() {

        StringBuilder sb = new StringBuilder("CREATE TABLE ");
        sb.append(name);
        sb.append('(');

        boolean isFirstToAdd = true;

        for (int i=0; i<columns.size(); i++) {
            DatabaseColumn nextColumn = columns.getItem(i);

            if (isFirstToAdd) {
                sb.append(',');
                isFirstToAdd = false;
            }

            sb.append(nextColumn.getCreateCode());
        }

        sb.append(')');

        return sb.toString();
    }

    /**
     * The indices for matching are a subset of the available columns in the database.
     * This ensures that only relevant columns are used in the matching process when a
     * user provides input.
     *
     * @return an array containing a subset of all valid DatabaseColumn indices
     */
    public abstract int[] wordFormIndicesForMatching();

    public HashSet<String> getColumnNamesForMatching() {
        HashSet<String> cNames = new HashSet<>();

        int[] indicesForMatching = wordFormIndicesForMatching();

        for (int i=0; i<indicesForMatching.length; i++) {
            int nextIndex = indicesForMatching[i];
            DatabaseColumn column = columns.getItem(nextIndex);
            cNames.add(column.name);
        }

        return cNames;
    }

    /**
     * @return The SQL code used for a dropping this table. Each table name is
     * defined in each subclass.
     */
    public String getDropTableCode() {
        StringBuilder sb = new StringBuilder("DROP TABLE IF EXISTS ");
        sb.append(name);
        return sb.toString();
    }

    public String getDropIndexIfExistsCode() {
        StringBuilder sb = new StringBuilder("DROP INDEX IF EXISTS ");
        sb.append(name.toLowerCase()); sb.append("_index");
        return sb.toString();
    }

    public String getCreateIndex(DatabaseColumn column) {
        StringBuilder sb = new StringBuilder("CREATE INDEX ");
        sb.append(name.toLowerCase()); sb.append("_index");
        sb.append(" ON "); sb.append(name);
        sb.append(" ("); sb.append(column.name); sb.append(")");
        return sb.toString();
    }

    /**
     * Returns the code for removing all rows from this table. The table and its
     * columns are not removed.
     *
     * @return The code to remove all rows from this table
     */
    public String getDeleteAllRowsCode() {
        StringBuilder sb = new StringBuilder("DELETE FROM ");
        sb.append(name);
        return sb.toString();
    }

    /**
     * Gets the column relevant to the tag. The tag is a code consisting of
     * a common word for all of the columns with vowels removed and consonants
     * simplifies.
     *
     * @return The column representing the tag for searching
     */
    public abstract DatabaseColumn getTagColumn();

    public DatabaseColumn getColumnAt(int index) {
        return columns.getItem(index);
    }

    /**
     * Gets the dictionary form for a word.
     *
     * @param word The word used for getting the dictionary form
     * @return The dictionary form for this word.
     */
    public abstract String getDictForm(Word word);
//    public abstract DatabaseColumn getDictFormColumn();


    public abstract int getTranslationsColumnIndex(Language lang);
}
