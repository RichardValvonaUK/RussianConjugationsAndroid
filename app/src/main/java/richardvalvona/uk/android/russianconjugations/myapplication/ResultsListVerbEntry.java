package richardvalvona.uk.android.russianconjugations.myapplication;

import java.util.ArrayList;
import java.util.regex.Pattern;

import richardvalvona.uk.android.russianconjugations.database.AdjectivesTable;
import richardvalvona.uk.android.russianconjugations.database.NounsTable;
import richardvalvona.uk.android.russianconjugations.database.VerbsTable;
import richardvalvona.uk.android.russianconjugations.languages.Language;
import richardvalvona.uk.android.russianconjugations.languages.WordWithInfo;

/**
 * Created by richard on 03/11/16.
 */

public class ResultsListVerbEntry extends AbstractResultsListEntry {

    protected ResultsListVerbEntry(WordWithInfo wordWithInfo) {
        super(wordWithInfo);
    }

    public String getInfinitive() {
        return wordWithInfo.word.allForms[VerbsTable.COLUMN_INDEX_INFINITIVE];
    }

    public String getAspect() {
        return wordWithInfo.word.allForms[VerbsTable.COLUMN_INDEX_ASPECT];
    }

    public String[] getPartners() {
        String partnersJoined = wordWithInfo.word.allForms[VerbsTable.COLUMN_INDEX_PARTNERS];
        String[] stringPartnersWithAspect = partnersJoined.split(Pattern.quote("|"));

        ArrayList<String> partnersOfOppositeAspect = new ArrayList<>(stringPartnersWithAspect.length / 2);

        for (int i=0; i<stringPartnersWithAspect.length; i+=2) {
            String nextPartner = stringPartnersWithAspect[i];

            // If the number of Strings is odd (it shouldn't be) then the last item is ignored.
            if (i != stringPartnersWithAspect.length - 1) {
                String nextAspect = stringPartnersWithAspect[i+1];

                if (!nextAspect.equals(getAspect())) {
                    partnersOfOppositeAspect.add(nextPartner);
                }
            }
        }

        return partnersOfOppositeAspect.toArray(new String[partnersOfOppositeAspect.size()]);
    }
}
