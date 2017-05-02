package richardvalvona.uk.android.russianconjugations.myapplication;

import richardvalvona.uk.android.russianconjugations.database.DatabaseTable;
import richardvalvona.uk.android.russianconjugations.languages.WordWithInfo;

/**
 * Created by richard on 03/11/16.
 */

public abstract class AbstractResultsListEntry {

    public final WordWithInfo wordWithInfo;

    protected AbstractResultsListEntry(WordWithInfo wordWithInfo) {
        this.wordWithInfo = wordWithInfo;
    }
}
