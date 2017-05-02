package richardvalvona.uk.android.russianconjugations.myapplication;

import android.content.Context;
import android.graphics.Typeface;
import android.support.annotation.NonNull;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.CheckBox;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import org.w3c.dom.Text;

import java.util.ArrayList;

import richardvalvona.uk.android.russianconjugations.database.NounsTable;
import richardvalvona.uk.android.russianconjugations.database.VerbsTable;
import richardvalvona.uk.android.russianconjugations.languages.Language;
import richardvalvona.uk.android.russianconjugations.languages.Word;

/**
 * Created by richard on 03/11/16.
 */

public class ResultsListAdapter extends ArrayAdapter<AbstractResultsListEntry> {

    private final ArrayList<AbstractResultsListEntry> resultsList;

    public ResultsListAdapter(Context context, ArrayList<AbstractResultsListEntry> resultsList) {
        super(context, 0, resultsList);
        this.resultsList = resultsList;
    }

    @NonNull
    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        if (convertView == null) {
            convertView = (LinearLayout) LayoutInflater.from(getContext()).inflate(R.layout.word_results_item, parent, false);
        }

        final AbstractResultsListEntry resultsListEntry = resultsList.get(position);

        /* When the user selected a result from the word list, it closes the left drawer and displays
        the conjugations in full.
         */
        convertView.findViewById(R.id.result_info).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                MainActivity activity = (MainActivity) getContext();
                activity.displayResult(resultsListEntry.wordWithInfo);
            }
        });

        // First setting matched word (if applicable)
        int matchedWordFormIndex = resultsListEntry.wordWithInfo.getMatchedWordFormIndex();
        TextView matchedWordText = (TextView) convertView.findViewById(R.id.matchedWordText);

        TextView mainWordText = (TextView) convertView.findViewById(R.id.mainWordText);

        if (matchedWordFormIndex == -1) {
            matchedWordText.setVisibility(View.GONE);
            mainWordText.setTypeface(Typeface.DEFAULT_BOLD);
        }
        else {
            matchedWordText.setVisibility(View.VISIBLE);
            matchedWordText.setText(resultsListEntry.wordWithInfo.word.getWordForm(
                    matchedWordFormIndex
            ));
            mainWordText.setTypeface(Typeface.DEFAULT);
        }

        // If the word is a noun
        if (resultsListEntry instanceof ResultsListNounEntry) {
            final ResultsListNounEntry entry = (ResultsListNounEntry) resultsListEntry;

            mainWordText.setText(entry.getNomForm(false) + ", "  + entry.getGenderStr());

            TextView wordCategoryText = (TextView) convertView.findViewById(R.id.categoryText);
            wordCategoryText.setText("noun");

            // Setting the translations
            TextView translationsText = (TextView) convertView.findViewById(R.id.translationsText);
            translationsText.setText(Language.cleanEnglishDescriptions(
                    entry.wordWithInfo.word.getWordForm(entry.wordWithInfo.word.table.getTranslationsColumnIndex(Language.english))
            ));
        }
        // If the word is an adjective
        else if (resultsListEntry instanceof ResultsListAdjectiveEntry) {
            final ResultsListAdjectiveEntry entry = (ResultsListAdjectiveEntry) resultsListEntry;

            // First set dictionary info
            mainWordText.setText(entry.getNomMasc());

            TextView wordCategoryText = (TextView) convertView.findViewById(R.id.categoryText);
            wordCategoryText.setText("adjective");

            // Setting the translations
            TextView translationsText = (TextView) convertView.findViewById(R.id.translationsText);
            translationsText.setText(Language.cleanEnglishDescriptions(
                    entry.wordWithInfo.word.getWordForm(entry.wordWithInfo.word.table.getTranslationsColumnIndex(Language.english))
            ));
        }
        // If the word is an adjective
        else if (resultsListEntry instanceof ResultsListVerbEntry) {
            final ResultsListVerbEntry entry = (ResultsListVerbEntry) resultsListEntry;

            // First set dictionary info
            String aspectRaw = entry.getAspect();

            if (aspectRaw.equals(VerbsTable.ASPECT_IMPERFECTIVE)) {
                mainWordText.setText(entry.getInfinitive() + ", im");
            }
            else if (aspectRaw.equals(VerbsTable.ASPECT_PERFECTIVE)) {
                mainWordText.setText(entry.getInfinitive() + ", pf");
            }
            else if (aspectRaw.equals(VerbsTable.ASPECT_BOTH)) {
                mainWordText.setText(entry.getInfinitive() + ", im/pf");
            }
            else {
                mainWordText.setText(entry.getInfinitive());
            }

            TextView wordCategoryText = (TextView) convertView.findViewById(R.id.categoryText);
            wordCategoryText.setText("verb");

            // Setting the translations
            TextView translationsText = (TextView) convertView.findViewById(R.id.translationsText);
            translationsText.setText(Language.cleanEnglishDescriptions(
                    entry.wordWithInfo.word.getWordForm(entry.wordWithInfo.word.table.getTranslationsColumnIndex(Language.english))
            ));
        }


        return convertView;
    }
}
