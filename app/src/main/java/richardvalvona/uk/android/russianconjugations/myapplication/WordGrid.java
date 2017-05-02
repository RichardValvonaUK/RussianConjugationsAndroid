package richardvalvona.uk.android.russianconjugations.myapplication;

import android.content.Context;
import android.util.AttributeSet;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TableLayout;
import android.widget.TableRow;

/**
 * Created by richard on 09/11/16.
 */

public class WordGrid extends TableLayout {

    private float[] columnWeights;
    private TableRow[] allRows;

    public WordGrid(Context context) {
        super(context);
    }

    public WordGrid(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public void setNumberOfRows(int numberOfRows) {
        removeAllViews();
        allRows = new TableRow[numberOfRows];

        for (int i=0; i<numberOfRows; i++) {
            allRows[i] = new TableRow(getContext());
            addView(allRows[i]);
        }
    }

    public void setColumnWeights(float[] columnWeights) {
        this.columnWeights = columnWeights;
    }

    public void addViewToRow(View v, int rowIndex) {
        TableRow row = allRows[rowIndex];

        if (columnWeights != null) {
            int numberOfItems = row.getChildCount();
            float weight = columnWeights[numberOfItems];
            TableRow.LayoutParams layoutParams = new TableRow.LayoutParams();
            layoutParams.weight = weight;
            layoutParams.width = LayoutParams.WRAP_CONTENT;
            layoutParams.height = LayoutParams.WRAP_CONTENT;
            layoutParams.setMargins(10,3,20,3);
            v.setLayoutParams(layoutParams);
        }

        row.addView(v);
    }
}
