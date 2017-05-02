package richardvalvona.uk.android.russianconjugations.languages;

import android.util.Log;

import java.util.ArrayList;

import richardvalvona.uk.android.russianconjugations.LightWord;

/**
 * Created by richard on 01/11/16.
 */

public class RussianWordMatcher extends WordMatcher {

    /**
     *
     * @param wordToCompareAgainst A potential word match to be compared against the input word
     * @param inputWord The word created based on the user's input.
     * @return
     */
    public int getMatchness(Word wordToCompareAgainst, LightWord inputWord, char[] priorityStartChars) {
        int matchType = MATCH_TYPE_NO_MATCH;
        final int wordWithoutVowelsSimplifiedConsonantsLength = inputWord.wordWithoutVowelsSimplifiedConsonants.length();

        int[] indicesForMatching = wordToCompareAgainst.table.wordFormIndicesForMatching();

        lastWordFormsIndex = -1;

        for (int indexForIndices=0; indexForIndices<indicesForMatching.length; indexForIndices++) {

            int wordFormsIndex = indicesForMatching[indexForIndices];

            String nextWordStr = wordToCompareAgainst.allForms[wordFormsIndex];

            // Empty words are ignored.
            if (nextWordStr == null || nextWordStr.length() == 0) continue;

            Language language = wordToCompareAgainst.table.lang;
            final LightWord nextLWord = new LightWord(language, nextWordStr);

            // If the input word matches a word in the word set exactly then it's an exact match
            if (inputWord.wordWithoutPronunciation.equals(nextLWord.wordWithoutPronunciation)) {
                matchType = MATCH_TYPE_EXACT; lastWordFormsIndex = wordFormsIndex;
                break;
            }
            else {
                // The next word is now simplified both consonant-wise and vowel-wise.
                if (nextLWord.wordWithoutVowelsSimplifiedConsonants.equals(inputWord.wordWithoutVowelsSimplifiedConsonants)) {
                    int lettersDifference = numberOfLettersDifference(nextLWord.wordWithoutPronunciation, inputWord.wordWithoutPronunciation);

                    int potentialBetterMatchType;

                    switch (lettersDifference) {
                        case 0:
                            if (language.getLetterPattern(inputWord.wordWithoutPronunciation).equals(language.getLetterPattern(nextLWord.wordWithoutPronunciation)) ) {
                                potentialBetterMatchType = MATCH_TYPE_VERY_VERY_CLOSE;
                            }
                            else {
                                potentialBetterMatchType = MATCH_TYPE_VERY_CLOSE;
                            }
                            break;
                        case 1:
                                potentialBetterMatchType = MATCH_TYPE_CLOSE; break;
                        case 2: potentialBetterMatchType = MATCH_TYPE_MEDIUM; break;
                        default: potentialBetterMatchType = MATCH_TYPE_DISTANT; break;
                    }

                    if (potentialBetterMatchType < matchType) { matchType = potentialBetterMatchType; lastWordFormsIndex = wordFormsIndex; }
                }
            }
        }

        return matchType;
    }
}
