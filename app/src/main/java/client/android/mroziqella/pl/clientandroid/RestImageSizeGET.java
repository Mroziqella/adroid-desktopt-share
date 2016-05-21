package client.android.mroziqella.pl.clientandroid;

import android.app.Activity;
import android.app.ProgressDialog;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.util.Base64;
import android.util.Log;
import android.widget.ImageView;

import org.json.JSONException;
import org.json.JSONObject;

/**
 * Created by Kamil on 12/05/2016.
 */
public class RestImageSizeGET extends WebServiceHandler {
    private Activity activity;
    private ImageView imageView;

    public RestImageSizeGET(ImageView imageView) {

        this.imageView = imageView;
    }

    public RestImageSizeGET() {
    }

    @Override
    protected void onPreExecute() {

    }

    @Override
    protected void onPostExecute(String result) {
        try {
            JSONObject jsonObj = new JSONObject(result);
            imageView.getLayoutParams().height = (int) jsonObj.get("x");
            imageView.getLayoutParams().width = (int) jsonObj.get("y");
            Log.d("------------>"," "+ jsonObj.get("x")+ jsonObj.get("y"));
        } catch (JSONException e) {
            e.printStackTrace();
        }

    }


}
