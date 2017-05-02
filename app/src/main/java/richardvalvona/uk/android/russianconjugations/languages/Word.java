package richardvalvona.uk.android.russianconjugations.languages;

import java.util.HashMap;

import richardvalvona.uk.android.russianconjugations.database.DatabaseTable;

/**
 * Created by richard on 01/11/16.
 */

public class Word {

    public final DatabaseTable table;
    public final String[] allForms;

    public Word(DatabaseTable table, String[] allForms) {
        this.table = table;
        this.allForms = allForms;
    }

    public String getWordForm(int index) {
        return getWordForm(index, false);
    }

    public String getWordForm(int index, boolean mergeAccent) {

        String formToReturn;

        if (index>=0 && index<allForms.length) {
            formToReturn = allForms[index];
        }
        else {
            formToReturn = "";
        }

        if (mergeAccent) {
            formToReturn = mergeAccent(formToReturn);
        }

        return formToReturn;
    }



    public static String mergeAccent(String word) {

        if (word == null) return null;

        final int wordLen = word.length();

        StringBuilder sb = new StringBuilder(wordLen);

        for (int i=0; i<wordLen; i++) {
            char nextChar = word.charAt(i);

            if (nextChar == '`' && i<wordLen-1) {
                i++;
                sb.append(word.charAt(i));
                sb.append('\u0301');
            }
            else {
                sb.append(nextChar);
            }
        }

        return sb.toString();
    }
}
