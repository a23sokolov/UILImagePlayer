package nsu.ccfit.ru.uilimageplayer.AsyncTask;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.os.AsyncTask;
import android.view.Gravity;
import android.widget.Toast;

import java.util.ArrayList;

import nsu.ccfit.ru.uilimageplayer.Fragment.Utils.InterfaceForUpdateGridView;
import nsu.ccfit.ru.uilimageplayer.ModelData.UrlImages;

/**
 * Created by Android on 16.03.2015.
 */
public class AsyncTaskGetUrlForImage extends AsyncTask<Void, Void, Void> {
        private ProgressDialog progressDialog;
        Context context;
        InterfaceForUpdateGridView forUpdateList;
        UrlImages urlImages;
        public AsyncTaskGetUrlForImage(InterfaceForUpdateGridView interfaceForUpdateGridView, Context context) {
            this.context = context;
            forUpdateList = interfaceForUpdateGridView;
            progressDialog = new ProgressDialog(context);
            urlImages = UrlImages.newInstanceSingleton(context);
        }

        protected void onPreExecute() {
            progressDialog.setMessage("Downloading your data...");
            progressDialog.show();
            progressDialog.setOnCancelListener(new DialogInterface.OnCancelListener() {
                public void onCancel(DialogInterface arg0) {
                    AsyncTaskGetUrlForImage.this.cancel(true);
                }
            });
        }

        @Override
        protected Void doInBackground(Void... params) {
            ArrayList<String> list = new FetchUrls().getArrayListFromUrl();
            urlImages.setNewUrls(list);
            return null;
        }

    @Override
    protected void onPostExecute(Void params) {
        if(!urlImages.isReadyToUse())
        {
            this.progressDialog.dismiss();
            Toast toast = Toast.makeText(progressDialog.getContext(), "Internet is not available.\nTurn ON internet and come again.", Toast.LENGTH_LONG);
            toast.setGravity(Gravity.CENTER,0,0);
            toast.show();
            return;
        }
        forUpdateList.setAdapter();
        this.progressDialog.dismiss();
    }
}
