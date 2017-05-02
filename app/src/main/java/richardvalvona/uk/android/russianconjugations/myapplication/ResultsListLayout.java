package richardvalvona.uk.android.russianconjugations.myapplication;

import android.app.Activity;
import android.content.Context;
import android.util.AttributeSet;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.ListView;

/**
 * Created by richard on 07/11/16.
 */

public class ResultsListLayout extends LinearLayout {

    private AbstractResultsListEntry resultsListEntry;

    public ResultsListLayout(Context context) {
        super(context);
        init();
    }

    public ResultsListLayout(Context context, AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    public ResultsListLayout(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init();
    }

    private void init() {
//        setOnClickListener(new OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                Activity a = (Activity) getContext();
//                ListView resultsList = (ListView) a.findViewById(R.id.searchResultsList);
//                LinearLayout resultInDetailBox = (LinearLayout) a.findViewById(R.id.resultInDetail);
//
////                resultsList.setVisibility(GONE);
////                resultInDetailBox.setVisibility(VISIBLE);
//            }
//        });
    }

    public void setResultsListEntry(AbstractResultsListEntry resultsListEntry) {
        this.resultsListEntry = resultsListEntry;
    }
}
