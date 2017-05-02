package richardvalvona.uk.android.russianconjugations.languages;

import richardvalvona.uk.android.russianconjugations.LightWord;

/**
 * Created by richard on 14/04/17.
 */

public class RussianWordMatcherFromEnglish extends WordMatcher {

    public int getMatchness(Word wordToCompareAgainst, LightWord inputWord, char[] priorityStartChars) {
        int matchType = MATCH_TYPE_NO_MATCH;

        String wordToSearch = inputWord.wordWithoutPronunciation;

        int wordFormsIndex = wordToCompareAgainst.table.getTranslationsColumnIndex(Language.english);

        String engDescStr = wordToCompareAgainst.allForms[wordFormsIndex].toLowerCase();

        // Empty words are ignored.
        if (!(engDescStr == null || engDescStr.length() == 0)) {

            Object[] containedEnglishMatchRaw = Language.matchWithBeginningAndEnd(engDescStr, "[[" + wordToSearch, "]]");

            if (containedEnglishMatchRaw != null) {

                int containedEnglishMatchPos = (int) containedEnglishMatchRaw[0];
                String containedEnglishMatch = (String) containedEnglishMatchRaw[1];

                if (containedEnglishMatch != null) {
                    int matchLen = containedEnglishMatch.length();

                    int lettersDifference = matchLen - 4 - wordToSearch.length();

                    if (lettersDifference == 0) {
                        if (containedEnglishMatchPos <= 1) {
                            matchType = MATCH_TYPE_EXACT;
                        }
                        else {
                            matchType = MATCH_TYPE_EXACT + (containedEnglishMatchPos / 10) + 1;
                            if (matchType >= MATCH_TYPE_VERY_VERY_CLOSE) matchType = MATCH_TYPE_VERY_VERY_CLOSE - 1;
                        }
                    } else {
                        matchType = MATCH_TYPE_VERY_VERY_CLOSE + lettersDifference - 1;
                        if (matchType >= MATCH_TYPE_NO_MATCH) matchType = MATCH_TYPE_NO_MATCH - 1;
                    }
                }
            }
        }

        return matchType;
    }
}
