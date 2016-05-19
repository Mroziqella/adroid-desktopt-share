package client.android.mroziqella.pl.clientandroid;

import android.app.Activity;
import android.os.AsyncTask;
import android.util.Log;

import org.json.JSONObject;

import java.io.BufferedInputStream;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLConnection;


public class WebServiceHandlerPOST extends AsyncTask<String, Void, String> {
    @Override
    protected void onPostExecute(String s) {
        super.onPostExecute(s);
    }


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
            HttpURLConnection connection = (HttpURLConnection) url
                    .openConnection();
            connection.setReadTimeout(10000 /* milliseconds */);
            connection.setConnectTimeout(15000 /* milliseconds */);

            // zezwolenie na wysyłanie danych
            connection.setDoOutput(true);
            // ustawienie typu wysyłanych danych
            connection.setRequestProperty("Content-Type",
                    "application/json");
            // ustawienie metody
            connection.setRequestMethod("POST");

            // stworzenie obiektu do wysłania
            JSONObject data = new JSONObject();
            data.put("x",5);
            data.put("y",10);

            // wysłanie obiektu
            BufferedWriter writer = new BufferedWriter(
                    new OutputStreamWriter(connection.getOutputStream(),
                            "UTF-8"));
            writer.write(data.toString());
            writer.close();

            //////////////////////////////////////////
            // na tym etapie obiekt został wysłany
            // i dostaliśmy odpowiedź serwera
            //////////////////////////////////////////

            // sprawdzenie kodu odpowiedzi, 200 = OK
            if (connection.getResponseCode() != 200) {
                throw new Exception("Bad Request");
            }

            // pobranie odpowiedzi serwera
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

