package richardvalvona.uk.android.russianconjugations.breceivers;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.os.Handler;
import android.widget.Toast;

import richardvalvona.uk.android.russianconjugations.myapplication.MainActivity;

/**
 * Created by richard on 19/04/17.
 */

public class InternetDetectorReceiver extends BroadcastReceiver {
    @Override
    public void onReceive(Context context, Intent intent) {
        final MainActivity mainActivity = MainActivity.getInstance();

        if (mainActivity != null) {
            ConnectivityManager conMgr = (ConnectivityManager) context.getSystemService (Context.CONNECTIVITY_SERVICE);

            if (conMgr.getActiveNetworkInfo() != null
                    && conMgr.getActiveNetworkInfo().isAvailable()
                    && conMgr.getActiveNetworkInfo().isConnected()) {

                    mainActivity.runOnUiThread(new Runnable() {
                        @Override
                        public void run() {
                            mainActivity.downloadDataFromInternetIfApplicable();
                        }
                    });
            }
            else {

                mainActivity.runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        mainActivity.pauseDataDownloadIfRunning();
                    }
                });
            }
        }
    }
}
