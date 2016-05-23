package client.android.mroziqella.pl.clientandroid;

import android.app.Activity;
import android.app.Application;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.util.Base64;
import android.util.Log;
import android.widget.ImageView;
import android.widget.Toast;

import org.json.JSONException;
import org.json.JSONObject;

/**
 * Created by Kamil on 12/05/2016.
 */
public class RestImageSizeGET extends WebServiceHandler {
    private static Integer x=null,y=null;
    private ImageView imageView;
    private Activity activity;

    public RestImageSizeGET(ImageView imageView,Activity activity) {

        this.activity=activity;
        this.imageView = imageView;
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
            if(x==null&&y==null){
                x = (int) jsonObj.get("x");
                y = (int) jsonObj.get("y");
            }
            else if(x!=(int)jsonObj.get("x")&&y!=(int)jsonObj.get("y")) {
                x = (int) jsonObj.get("x");
                y = (int) jsonObj.get("y");
                activity.recreate();
                Toast.makeText(activity,"Zmieniono rozdzielczosc polacz sie ponownie z serwerem",Toast.LENGTH_SHORT).show();
//                Intent intent = new Intent(activity, DisplayImageActivity.class);
//                activity.startActivity(intent);

               // activity.finish();

            }

            Log.d("------------>"," "+ jsonObj.get("x")+ jsonObj.get("y"));
        } catch (JSONException e) {
            e.printStackTrace();
        }

    }


}
