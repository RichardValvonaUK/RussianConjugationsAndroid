package richardvalvona.uk.android.russianconjugations.languages;

import java.util.ArrayList;

import richardvalvona.uk.android.russianconjugations.LightWord;

/**
 * Created by richard on 14/04/17.
 */

public abstract class WordMatcher {

    private static int matchTypesCount = 0;
    public final static int MATCH_TYPE_EXACT_WITH_PRONUNCIATION = matchTypesCount++;
    public final static int MATCH_TYPE_EXACT = matchTypesCount++;
    public final static int MATCH_TYPE_VERY_VERY_CLOSE = matchTypesCount+=5;
    public final static int MATCH_TYPE_VERY_CLOSE = matchTypesCount++;
    public final static int MATCH_TYPE_CLOSE = matchTypesCount++;
    public final static int MATCH_TYPE_MEDIUM = matchTypesCount++;
    public final static int MATCH_TYPE_DISTANT = matchTypesCount++;
    public final static int MATCH_TYPE_VERY_DISTANT = matchTypesCount++;
    public final static int MATCH_TYPE_VERY_VERY_DISTANT = matchTypesCount++;
    public final static int MATCH_TYPE_NO_MATCH = matchTypesCount++;

    protected int lastWordFormsIndex = -1;

    public abstract int getMatchness(Word wordToCompareAgainst, LightWord inputWord, char[] priorityStartChars);

    public static int numberOfLettersDifference(final String firstWord, final String secondWord) {

        final String word1, word2;

        // word2 must never be shorter than word1
        if (secondWord.length() < firstWord.length()) {
            word1 = secondWord;
            word2 = firstWord;
        }
        else {
            word1 = firstWord;
            word2 = secondWord;
        }

        int word1Length = word1.length();

        ArrayList<Character> letters = new ArrayList<>(word1Length);

        for (int i=0; i<word1Length; i++) {
            letters.add(word1.charAt(i));
        }

        int numberOfLettersDifference = 0;

        int word2Length = word2.length();

        for (int i=0; i<word2Length; i++) {
            boolean letterExisted = letters.remove((Character)word2.charAt(i));

            if (!letterExisted) {
                numberOfLettersDifference++;
            }
        }

        return numberOfLettersDifference;
    }

    public int getLastWordFormsIndex() {
        return lastWordFormsIndex;
    }
}
