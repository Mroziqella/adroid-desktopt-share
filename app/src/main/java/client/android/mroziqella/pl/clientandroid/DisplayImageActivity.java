package client.android.mroziqella.pl.clientandroid;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.AsyncTask;
import android.os.Handler;
import android.os.SystemClock;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Base64;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.view.Window;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import java.io.BufferedInputStream;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.URL;
import java.net.URLConnection;

public class DisplayImageActivity extends Activity {
    private ImageView imageView;
    private Button button;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_display_image);
        imageView = (ImageView) findViewById(R.id.imageView);
        button = (Button) findViewById(R.id.connect);
        imageView.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                new WebServiceHandlerPOST((int) event.getX(),(int)event.getY())
                        .execute("http://192.168.43.69:8080/rest/coord/"+MainActivity.getRoomName().getText());
                return false;
            }
        });


    }
    Handler handler = new Handler();
    Runnable timedTask = new Runnable(){

        @Override
        public void run() {
            new RestImageGET(imageView).execute("http://192.168.43.69:8080/image/picture/"+MainActivity.getRoomName().getText());
            handler.postDelayed(timedTask, 1000);

        }};

    public void polacz(View view) {
        handler.post(timedTask);
        button.setVisibility(View.GONE);

    }


    @Override
    protected void onDestroy() {
        super.onDestroy();
        timedTask=null;
        Toast.makeText(getApplicationContext(),"coooo?",Toast.LENGTH_SHORT).show();
    }


}
