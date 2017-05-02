package richardvalvona.uk.android.russianconjugations.myapplication.richardvalvona.uk.android.russianconjugations.myapplication.richardvalvona.uk.android.russianconjugations.database;

import org.junit.Test;

import java.util.Arrays;
import java.util.HashSet;

import static org.junit.Assert.assertArrayEquals;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import richardvalvona.uk.android.russianconjugations.database.DatabaseTagGenerator;
import richardvalvona.uk.android.russianconjugations.languages.Language;
import richardvalvona.uk.android.russianconjugations.languages.Russian;

/**
 * Created by richard on 01/04/17.
 */

public class DatabaseTagGeneratorTest {

    @Test
    public void databaseTagGenerator_getCommonStartingLetters() {
        assertEquals("ab", DatabaseTagGenerator.getCommonStartingLetters("abc", "abl"));
        assertEquals("", DatabaseTagGenerator.getCommonStartingLetters("efgkl", "fppwd"));
        assertEquals("eroll", DatabaseTagGenerator.getCommonStartingLetters("eroll", "eroll"));
        assertEquals("", DatabaseTagGenerator.getCommonStartingLetters("", ""));
        assertEquals("eroll", DatabaseTagGenerator.getCommonStartingLetters("erollee", "eroll"));
        assertEquals("eroll", DatabaseTagGenerator.getCommonStartingLetters("eroll", "erollee"));
        assertEquals("eroll", DatabaseTagGenerator.getCommonStartingLetters("erolle", "erollf"));
        assertEquals("", DatabaseTagGenerator.getCommonStartingLetters("cake", "meal"));
    }


    @Test
    public void databaseTagGenerator_addEntryIfCanMatch() {

        HashSet<String> columnNamesForMatching = new HashSet<>();
        columnNamesForMatching.add("fruit_apples");
        columnNamesForMatching.add("fruit_bananas");
        columnNamesForMatching.add("fruit_pears");
        columnNamesForMatching.add("veg_carrots");
        columnNamesForMatching.add("veg_peas");
        columnNamesForMatching.add("veg_potatoes");

        String[] externalColumnNames = new String[6];
        int externalColumnNamesIndex = 0;
        final int INDEX_fruit_apples = externalColumnNamesIndex++;
        final int INDEX_fruit_bananas = externalColumnNamesIndex++;
        final int INDEX_veg_carrots = externalColumnNamesIndex++;
        final int INDEX_veg_peas = externalColumnNamesIndex++;
        final int INDEX_snacks_crisps = externalColumnNamesIndex++;
        final int INDEX_snacks_sweets = externalColumnNamesIndex++;

        externalColumnNames[INDEX_fruit_apples] = "fruit_apples";
        externalColumnNames[INDEX_fruit_bananas] = "fruit_bananas";
        externalColumnNames[INDEX_veg_carrots] = "veg_carrots";
        externalColumnNames[INDEX_veg_peas] = "veg_peas";
        externalColumnNames[INDEX_snacks_crisps] = "snacks_crisps";
        externalColumnNames[INDEX_snacks_sweets] = "snacks_sweets";

        DatabaseTagGenerator tagGenerator = new DatabaseTagGenerator(columnNamesForMatching, externalColumnNames, 4);
        assertEquals(true, tagGenerator.addEntryIfCanMatch(INDEX_fruit_apples, "piewithapples"));
        assertEquals(true, tagGenerator.addEntryIfCanMatch(INDEX_fruit_bananas, "piewithbananas"));
        assertEquals(true, tagGenerator.addEntryIfCanMatch(INDEX_veg_peas, "piewithoutcheese"));
        assertEquals(false, tagGenerator.addEntryIfCanMatch(INDEX_veg_peas, ""));
        assertEquals(false, tagGenerator.addEntryIfCanMatch(INDEX_veg_carrots, null));
        assertEquals(false, tagGenerator.addEntryIfCanMatch(INDEX_snacks_crisps, "crispsandwich"));

        assertEquals("piew", tagGenerator.getTag());
    }

}
