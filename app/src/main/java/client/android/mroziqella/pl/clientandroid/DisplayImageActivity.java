package client.android.mroziqella.pl.clientandroid;

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
import android.view.View;
import android.widget.Button;
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

public class DisplayImageActivity extends AppCompatActivity {
    private ImageView imageView;
    private Button button;
    RestImageGET restImageGET;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_display_image);
        imageView = (ImageView) findViewById(R.id.imageView);
        button = (Button) findViewById(R.id.connect);




    }
    Handler handler = new Handler();
    Runnable timedTask = new Runnable(){

        @Override
        public void run() {
            new RestImageGET(imageView).execute("http://192.168.43.69:8080/image/picture/Adam");
            handler.postDelayed(timedTask, 1000);
        }};

    public void polacz(View view) {



        handler.post(timedTask);







//        byte[] decodedString = Base64.decode("iVBORw0KGgoAAAANSUhEUgAAAKAAAABZCAIAAAA2MLirAAAAAXNSR0IArs4c6QAAAARnQU1BAACxjwv8YQUAAAAJcEhZcwAAEnQAABJ0Ad5mH3gAAAHtSURBVHhe7dExDgIBEMNA/v/po3HKSIgGsvKUbv16dJqDj3PwcQ4+zsHHOfg4Bx/n4OMcfJyDj/t08Ev/ikOFg+dxqHDwPA4VDp7HocLB8zhUfDmYql/gQVALB+/hQVALB+/hQVALB+/hQVALB+/hQVALB+/hQVALB+/hQVALB+/hQVALB+/hQVALB+/hQVALB+/hQVALB+/hQVALB+/hQVALB+/hQVALB+/hQVALB+/hQVALB+/hQVALB+/hQVALB+/hQVALB+/hQVALB+/hQVALB+/hQVALB+/hQVALB+/hQVALB+/hQVALB+/hQVALB+/hQVALB+/hQVALB+/hQVALB+/hQVALB+/hQVALB+/hQVALB+/hQVALB+/hQVALB+/hQVALB+/hQVALB+/hQVALB+/hQVALB+/hQVALB+/hQVALB+/hQVALB+/hQVALB+/hQVALB+/hQVALB+/hQVALB+/hQVALB+/hQVALB+/hQVALB+/hQVALB+/hQVALB+/hQVALB+/hQVALB+/hQVALB+/hQVALB+/hQVALB+/hQVALB+/hQVALB+/hQVALB+/hQVCLLwfrf3CocPA8DhUOnsehwsHzOFQ4eB6Hik8Ha5SDj3PwcQ4+zsHHOfg4Bx/n4OMcfJyDT3ueNy+iYHphpeKjAAAAAElFTkSuQmCC",Base64.DEFAULT);
//        Bitmap decodedByte = BitmapFactory.decodeByteArray(decodedString, 0, decodedString.length);
//        imageView.setImageBitmap(decodedByte);
        //Toast.makeText(this,"WCZYTANO!!!!!",Toast.LENGTH_LONG);
    }


}
