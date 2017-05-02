package richardvalvona.uk.android.russianconjugations.myapplication;

import richardvalvona.uk.android.russianconjugations.database.AdjectivesTable;
import richardvalvona.uk.android.russianconjugations.database.NounsTable;
import richardvalvona.uk.android.russianconjugations.languages.Language;
import richardvalvona.uk.android.russianconjugations.languages.WordWithInfo;

/**
 * Created by richard on 03/11/16.
 */

public class ResultsListAdjectiveEntry extends AbstractResultsListEntry {

    protected ResultsListAdjectiveEntry(WordWithInfo wordWithInfo) {
        super(wordWithInfo);
    }

    public String getNomMasc() {
        return wordWithInfo.word.allForms[AdjectivesTable.COLUMN_INDEX_NOM_M];
    }
}
