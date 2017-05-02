package richardvalvona.uk.android.russianconjugations.database;

import android.provider.ContactsContract;

import java.util.HashSet;


/**
 * Created by richard on 31/03/17.
 */

public class DatabaseTagGenerator {

    private final int maxLetters;

    private String tag = null;

    private final boolean[] externalColumnsForMatchingBooleanArray;

    public DatabaseTagGenerator(HashSet<String> internalColumnNamesForMatching, String[] externalColumnNames, int maxLetters) {
        externalColumnsForMatchingBooleanArray = new boolean[externalColumnNames.length];

        for (int i=0; i<externalColumnNames.length; i++) {
            if (internalColumnNamesForMatching.contains(externalColumnNames[i].trim())) {
                externalColumnsForMatchingBooleanArray[i] = true;
            }
            else {
                externalColumnsForMatchingBooleanArray[i] = false;
            }
        }

        this.maxLetters = maxLetters < 0 ? Integer.MAX_VALUE : maxLetters;
    }

    public static String getCommonStartingLetters(String s1, String s2) {

        int shortestLength = Math.min(s1.length(), s2.length());

        StringBuilder sb = new StringBuilder(shortestLength);

        for (int i=0; i<shortestLength; i++) {
            char c1 = s1.charAt(i);
            char c2 = s2.charAt(i);

            if (c1 == c2) {
                sb.append(c1);
            }
            else {
                break;
            }
        }

        return sb.toString();
    }

    /**
     * Adds a word for generating tag if the this column is included for generating
     * tag.
     *
     * @param externalColumnIndex The column indice to check for adding entry
     * @param word The word to add
     */
    public boolean addEntryIfCanMatch(int externalColumnIndex, String word) {
        /* Only words containing non-white characters are allowed to be used
        as potential tags.
         */
        if (externalColumnsForMatchingBooleanArray[externalColumnIndex] && word != null) {
            final String wordTrimmed = word.trim();
            if (!wordTrimmed.equals("")) {

                // A shorter potential tag must replace a longer one
                final String potentialTag = wordTrimmed;

                if (tag == null) {
                    tag = potentialTag;
                }
                else {
                    tag = getCommonStartingLetters(tag, potentialTag);
                }

                return true;
            }
        }

        return false;
    }

    public String getTag() {

        if (tag == null) {
            return "";
        }
        else if (tag.length() > maxLetters) {
            return tag.substring(0,maxLetters);
        }
        else {
            return tag;
        }
    }

    public void clearTag() {
        tag = null;
    }
}
