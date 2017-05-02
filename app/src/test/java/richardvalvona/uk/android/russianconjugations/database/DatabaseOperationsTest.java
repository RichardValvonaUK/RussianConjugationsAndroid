package richardvalvona.uk.android.russianconjugations.database;

import android.content.ContentValues;

import org.junit.Test;
import org.mockito.Mock;
import org.mockito.Mockito;

import java.util.HashMap;
import java.util.HashSet;

import static org.junit.Assert.assertArrayEquals;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import richardvalvona.uk.android.russianconjugations.languages.Language;
import richardvalvona.uk.android.russianconjugations.languages.Word;

/**
 * Created by richard on 01/04/17.
 */

public class DatabaseOperationsTest {

    public static void readLineIntoTable(DatabaseWrapper dbWrapper, String[] externamColumnNames, String[] words, DatabaseTable table, HashMap<String, String> values, DatabaseTagGenerator dGenerator) {
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
        dbWrapper.insert(table.name, null, null);
    }

    @Test
    public void databaseOperations_readLineIntoTable() {
        DatabaseWrapper emptyWrapper = new DatabaseWrapper() {
            @Override
            public long insert(String table, String nullColumnHack, ContentValues values) { return 0; }
        };

        final String[] columnNames = new String[] {"column_1", "column_2", "column_3", "column_4", "column_5"};

        final AssignedArrayList<DatabaseColumn> columns = new AssignedArrayList<>();
        final int INDEX_TAG = columns.addItem(new DatabaseColumn("tag", "VARCHAR").setLength(4));
        final int INDEX_COL_1 = columns.addItem(new DatabaseColumn("column_1", "VARCHAR"));
        final int INDEX_COL_2 = columns.addItem(new DatabaseColumn("column_2", "VARCHAR"));
        final int INDEX_COL_3 = columns.addItem(new DatabaseColumn("column_3", "VARCHAR"));
        final int INDEX_COL_4 = columns.addItem(new DatabaseColumn("column_4", "VARCHAR"));
        final int INDEX_COL_5 = columns.addItem(new DatabaseColumn("column_5", "VARCHAR"));

        DatabaseTable table1 = new DatabaseTable("table_1", columns, Language.english) {
            @Override
            public int[] wordFormIndicesForMatching() { return new int[] {INDEX_COL_1, INDEX_COL_2, INDEX_COL_3, INDEX_COL_4}; }

            @Override
            public DatabaseColumn getTagColumn() { return columns.getItem(INDEX_TAG); }

            @Override
            public String getDictForm(Word word) { return columns.getItem(INDEX_COL_1).name; }

            @Override
            public int getTranslationsColumnIndex(Language lang) {
                return 0;
            }
        };


        String[] words1 = new String[] {"external", "excellent", "extreme", "extras", "Some English words"};
        HashMap<String, String> values1 = new HashMap<>();

        DatabaseTagGenerator databaseTagGenerator = new DatabaseTagGenerator(table1.getColumnNamesForMatching(), columnNames, 4);
        readLineIntoTable(emptyWrapper, columnNames, words1, table1, values1, databaseTagGenerator);
        assertEquals("tag", table1.getTagColumn().name);
        assertEquals(6, values1.size());
        assertEquals("external", values1.get("column_1"));
        assertEquals("excellent", values1.get("column_2"));
        assertEquals("extreme", values1.get("column_3"));
        assertEquals("extras", values1.get("column_4"));
        assertEquals("Some English words", values1.get("column_5"));
        assertEquals("ex", values1.get(table1.getTagColumn().name));

        // Words 2
        DatabaseTable table2 = new DatabaseTable("table_2", columns, Language.russian) {
            @Override
            public int[] wordFormIndicesForMatching() { return new int[] {INDEX_COL_1, INDEX_COL_2, INDEX_COL_3, INDEX_COL_4}; }

            @Override
            public DatabaseColumn getTagColumn() { return columns.getItem(INDEX_TAG); }

            @Override
            public String getDictForm(Word word) { return columns.getItem(INDEX_COL_1).name; }

            @Override
            public int getTranslationsColumnIndex(Language lang) {
                return 0;
            }
        };

        String[] words2 = new String[] {"ги́бкий", "ги́бкого", "ги́бкую", "ги́бкою", "Some English words 2"};
        HashMap<String, String> values2 = new HashMap<>();

        assertEquals("гбг", Language.russian.removeVowelsSimplifyConsonants("ги́бкий"));
        assertEquals("гбгг", Language.russian.removeVowelsSimplifyConsonants("ги́бкого"));
        assertEquals("гбг", Language.russian.removeVowelsSimplifyConsonants("ги́бкую"));
        assertEquals("гбг", Language.russian.removeVowelsSimplifyConsonants("ги́бкою"));

        databaseTagGenerator = new DatabaseTagGenerator(table2.getColumnNamesForMatching(), columnNames, 4);
        readLineIntoTable(emptyWrapper, columnNames, words2, table2, values2, databaseTagGenerator);
        assertEquals("ги́бкий", values2.get("column_1"));
        assertEquals("ги́бкого", values2.get("column_2"));
        assertEquals("ги́бкую", values2.get("column_3"));
        assertEquals("ги́бкою", values2.get("column_4"));
        assertEquals("гбг", values2.get(table2.getTagColumn().name));


        // Words 2
        DatabaseTable table3 = new DatabaseTable("table_3", columns, Language.russian) {
            @Override
            public int[] wordFormIndicesForMatching() { return new int[] {INDEX_COL_1, INDEX_COL_2, INDEX_COL_3, INDEX_COL_4}; }

            @Override
            public DatabaseColumn getTagColumn() { return columns.getItem(INDEX_TAG); }

            @Override
            public String getDictForm(Word word) { return columns.getItem(INDEX_COL_1).name; }

            @Override
            public int getTranslationsColumnIndex(Language lang) {
                return 0;
            }
        };


        String[] words3 = new String[] {"кни́га", "кни́гу", "книг", "кни́гах", "Some English words 3"};
        HashMap<String, String> values3 = new HashMap<>();

        assertEquals("гнг", Language.russian.removeVowelsSimplifyConsonants("кни́га"));
        assertEquals("гнг", Language.russian.removeVowelsSimplifyConsonants("кни́гу"));
        assertEquals("гнг", Language.russian.removeVowelsSimplifyConsonants("книг"));
        assertEquals("гнгх", Language.russian.removeVowelsSimplifyConsonants("кни́гах"));

        databaseTagGenerator = new DatabaseTagGenerator(table3.getColumnNamesForMatching(), columnNames, 4);
        readLineIntoTable(emptyWrapper, columnNames, words3, table3, values3, databaseTagGenerator);
        assertEquals("кни́га", values3.get("column_1"));
        assertEquals("кни́гу", values3.get("column_2"));
        assertEquals("книг", values3.get("column_3"));
        assertEquals("кни́гах", values3.get("column_4"));
        assertEquals("гнг", values3.get(table3.getTagColumn().name));
    }

    @Test
    public void databaseOperations_addingFromExternalColumns() {
        final String[] columnNames = new String[] {"column_1", "column_2", "column_3", "column_4", "column_5"};
    }

}
