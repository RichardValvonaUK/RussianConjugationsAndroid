package richardvalvona.uk.android.russianconjugations.myapplication;

import richardvalvona.uk.android.russianconjugations.database.NounsTable;
import richardvalvona.uk.android.russianconjugations.languages.Language;
import richardvalvona.uk.android.russianconjugations.languages.WordWithInfo;

/**
 * Created by richard on 03/11/16.
 */

public class ResultsListNounEntry extends AbstractResultsListEntry {

    protected ResultsListNounEntry(WordWithInfo wordWithInfo) {
        super(wordWithInfo);
    }

    public String getNomForm(boolean getPlural) {

        int indexToUse = getPlural ? NounsTable.COLUMN_INDEX_P_N : NounsTable.COLUMN_INDEX_S_N;

        return wordWithInfo.word.allForms[indexToUse];
    }

    public String getGenderStr() {
        return wordWithInfo.word.allForms[NounsTable.COLUMN_INDEX_GENDER];
    }

    public boolean isIndeclinable() {

        int numberOfFormsUsed = 0;

        for (int i=NounsTable.COLUMN_INDEX_S_N; i<= NounsTable.COLUMN_INDEX_P_P; i++) {
            String nextForm = wordWithInfo.word.allForms[i];

            if (!nextForm.equals("")) {
                numberOfFormsUsed++;
            }
        }

        return numberOfFormsUsed <= 1;
    }
}
