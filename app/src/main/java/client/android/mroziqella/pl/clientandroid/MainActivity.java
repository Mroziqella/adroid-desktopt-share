package client.android.mroziqella.pl.clientandroid;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Intent;
import android.net.Uri;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.appindexing.Action;
import com.google.android.gms.appindexing.AppIndex;
import com.google.android.gms.common.api.GoogleApiClient;

import org.json.JSONObject;

import java.io.BufferedInputStream;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.URL;
import java.net.URLConnection;


// REST implementacja = http://damianchodorek.com/2015/08/09/kurs-android-async-task-web-service-rest-11/

public class MainActivity extends Activity {

    /**
     * ATTENTION: This was auto-generated to implement the App Indexing API.
     * See https://g.co/AppIndexing/AndroidStudio for more information.
     */
    private static EditText roomName;
    private EditText roomPassowrd;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        roomName = (EditText) findViewById(R.id.roomName);
        roomPassowrd= (EditText) findViewById(R.id.passwordRoom);


    }
    public void connect(View view){
        RestLoginGET restLoginGET = new RestLoginGET(this);
        restLoginGET.execute("http://192.168.43.69:8080/rest/login/"+roomName.getText()+"?password="+roomPassowrd.getText());
    }

    public static EditText getRoomName() {
        return roomName;
    }
}


