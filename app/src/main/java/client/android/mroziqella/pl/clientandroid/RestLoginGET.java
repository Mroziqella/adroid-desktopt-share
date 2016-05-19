package client.android.mroziqella.pl.clientandroid;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Intent;
import android.util.Log;
import android.widget.TextView;
import android.widget.Toast;

/**
 * Created by Kamil on 12/05/2016.
 */
public class RestLoginGET extends WebServiceHandler {

   private Activity activity;
    private ProgressDialog dialog;

    public RestLoginGET(Activity activity) {
        this.activity = activity;
        dialog = new ProgressDialog(activity);
    }

    @Override
    protected void onPreExecute() {
        // wyświetlamy okienko dialogowe każące czekać
        dialog.setMessage("Czekaj...");
        dialog.show();
    }
    @Override
    protected void onPostExecute(String result) {
        // chowamy okno dialogowe
        dialog.dismiss();

        try {
            ((TextView) activity.findViewById(R.id.textView)).setText("id: "
                    + result);
            if(result.equals("false")){
                throw new Exception();
            }
            Intent intent = new Intent(activity, ZoomInZoomOut.class);
            activity.startActivity(intent);

        } catch (Exception e) {
            // obsłuż wyjątek
            Toast.makeText(activity.getApplicationContext(), "Błąd logowania sprawdź wprowadzone dane", Toast.LENGTH_LONG).show();
            Log.d(MainActivity.class.getSimpleName(), e.toString());
        }
    }
}
