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

import java.io.BufferedInputStream;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.URL;
import java.net.URLConnection;


public class WebServiceHandler extends AsyncTask<String, Void, String> {

    private Activity activity;


    // okienko dialogowe, które każe użytkownikowi czekać


    // metoda wykonywana jest zaraz przed główną operacją (doInBackground())
    // mamy w niej dostęp do elementów UI


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

    // konwersja z InputStream do String
    public static String streamToString(InputStream is) {

        BufferedReader reader = new BufferedReader(new InputStreamReader(is));
        StringBuilder stringBuilder = new StringBuilder();
        String line = null;

        try {

            while ((line = reader.readLine()) != null) {
                stringBuilder.append(line);
            }

            reader.close();

        } catch (IOException e) {
            // obsłuż wyjątek
            Log.d(WebServiceHandler.class.getSimpleName(), e.toString());
        }

        return stringBuilder.toString();
    }


}

