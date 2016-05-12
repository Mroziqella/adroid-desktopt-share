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
    private EditText roomName;
    private EditText roomPassowrd;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        roomName = (EditText) findViewById(R.id.roomName);
        roomPassowrd= (EditText) findViewById(R.id.passwordRoom);


    }
    public void connect(View view){
        WebServiceHandler webServiceHandler = new WebServiceHandler();
        webServiceHandler.execute("http://192.168.43.69:8080/rest/login/"+roomName.getText()+"?password="+roomPassowrd.getText());


    }

    private class WebServiceHandler extends AsyncTask<String, Void, String> {
        // okienko dialogowe, które każe użytkownikowi czekać
        private ProgressDialog dialog = new ProgressDialog(MainActivity.this);

        // metoda wykonywana jest zaraz przed główną operacją (doInBackground())
        // mamy w niej dostęp do elementów UI
        @Override
        protected void onPreExecute() {
            // wyświetlamy okienko dialogowe każące czekać
            dialog.setMessage("Czekaj...");
            dialog.show();
        }

        // główna operacja, która wykona się w osobnym wątku
        // nie ma w niej dostępu do elementów UI
        @Override
        protected String doInBackground(String... urls) {

            try {
                // zakładamy, że jest tylko jeden URL
                URL url = new URL(urls[0]);
                URLConnection connection = url.openConnection();

                // pobranie danych do InputStream
                InputStream in = new BufferedInputStream(
                        connection.getInputStream());

                // konwersja InputStream na String
                // wynik będzie przekazany do metody onPostExecute()
                return streamToString(in);

            } catch (Exception e) {
                // obsłuż wyjątek
                Log.d(MainActivity.class.getSimpleName(), e.toString());
                return null;
            }

        }

        // metoda wykonuje się po zakończeniu metody głównej,
        // której wynik będzie przekazany;
        // w tej metodzie mamy dostęp do UI
        @Override
        protected void onPostExecute(String result) {

            // chowamy okno dialogowe
            dialog.dismiss();

            try {
                // reprezentacja obiektu JSON w Javie
               // JSONObject json = new JSONObject(result);

                // pobranie pól obiektu JSON i wyświetlenie ich na ekranie
//                ((TextView) findViewById(R.id.textView)).setText("id: "
//                        + json.optString("id"));


                ((TextView) findViewById(R.id.textView)).setText("id: "
                        + result);
                if(result.equals("false\n")){
                    throw new Exception();
                }
                Intent intent = new Intent(MainActivity.this, DisplayImageActivity.class);
                startActivity(intent);

            } catch (Exception e) {
                // obsłuż wyjątek
                Toast.makeText(getApplicationContext(), "Błąd logowania sprawdź wprowadzone dane", Toast.LENGTH_LONG).show();
                Log.d(MainActivity.class.getSimpleName(), e.toString());
            }
        }


    }

    // konwersja z InputStream do String
    public static String streamToString(InputStream is) {

        BufferedReader reader = new BufferedReader(new InputStreamReader(is));
        StringBuilder stringBuilder = new StringBuilder();
        String line = null;

        try {

            while ((line = reader.readLine()) != null) {
                stringBuilder.append(line + "\n");
            }

            reader.close();

        } catch (IOException e) {
            // obsłuż wyjątek
            Log.d(MainActivity.class.getSimpleName(), e.toString());
        }

        return stringBuilder.toString();
    }


}


