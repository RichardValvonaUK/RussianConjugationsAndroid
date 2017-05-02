package richardvalvona.uk.android.russianconjugations.myapplication;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.DialogInterface;

/**
 * Created by richard on 30/10/16.
 */

public class MyProgressDialog extends ProgressDialog {

    public MyProgressDialog(Context context, String title, String message) {
        super(context);

        setProgressStyle(STYLE_HORIZONTAL);

        setButton(BUTTON_NEGATIVE, "Cancel", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {

            }
        });
        setTitle(title);
        setMessage(message);

        setMax(1000);
    }
}
