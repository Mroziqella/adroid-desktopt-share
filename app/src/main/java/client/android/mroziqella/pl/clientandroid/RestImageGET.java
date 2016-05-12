package client.android.mroziqella.pl.clientandroid;

import android.app.Activity;
import android.app.ProgressDialog;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.SystemClock;
import android.util.Base64;
import android.widget.ImageView;
import android.widget.Toast;

/**
 * Created by Kamil on 12/05/2016.
 */
public class RestImageGET extends WebServiceHandler {
    private Activity activity;
    private  Bitmap decodedByte;
    private ProgressDialog dialog;
    private ImageView imageView;

    public RestImageGET(ImageView imageView) {

        this.imageView =imageView;
    }

    public RestImageGET() {
    }

    @Override
    protected void onPreExecute() {

    }

    @Override
    protected void onPostExecute(String result) {
        try{
        byte[] decodedString = Base64.decode(result,Base64.DEFAULT);
        decodedByte = BitmapFactory.decodeByteArray(decodedString, 0, decodedString.length);
        imageView.setImageBitmap(decodedByte);
        }catch (java.lang.IllegalArgumentException e){};

    }
    


}
