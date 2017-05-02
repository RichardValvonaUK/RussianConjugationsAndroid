package richardvalvona.uk.android.russianconjugations.myapplication;

import android.content.Context;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.widget.ViewFlipper;

/**
 * Created by richard on 08/11/16.
 */

public class ResultsFlipper extends ViewFlipper {
    private OnTouchListener touchEvent;

    public ResultsFlipper(Context context) {
        super(context);
    }

    public ResultsFlipper(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    @Override
    public boolean onInterceptTouchEvent(MotionEvent ev) {
        return touchEvent.onTouch(this, ev);
    }

    public void setTouchEvent(OnTouchListener touchEvent) {
        this.touchEvent = touchEvent;
    }
}
