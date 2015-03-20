package nsu.ccfit.ru.uilimageplayer.AsyncTask;

import android.util.Log;

import org.apache.http.client.ClientProtocolException;
import org.json.JSONArray;
import org.json.JSONException;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;

/**
 * Created by Android on 17.03.2015.
 */
public class FetchUrls {

    public ArrayList<String> getArrayListFromUrl()
    {
        String url_select = "https://gist.githubusercontent.com/lnevermore/46f704deaece03ccd230/raw/68b44464532d32b0aeac1c813c33e0c7399d4652/jsondump";
        String result;
        ArrayList<String> arrayList = null;
        BufferedReader reader;
        HttpURLConnection urlConnection;
        try {

            URL url = new URL(url_select);
            urlConnection = (HttpURLConnection) url.openConnection();
            urlConnection.setRequestMethod("GET");
            urlConnection.connect();

            InputStream inputStream = urlConnection.getInputStream();
            StringBuffer buffer = new StringBuffer();

            reader = new BufferedReader(new InputStreamReader(inputStream));

            String line;
            while ((line = reader.readLine()) != null) {
                buffer.append(line);
            }
            result = buffer.toString();
            if(result.equals(""))
                return null;
            JSONArray jArray = new JSONArray(result);
            arrayList = new ArrayList<String>();
            for (int i = 0; i < jArray.length(); i++) {
                String jsonObject = jArray.getString(i);
                arrayList.add(jsonObject);
            }
        } catch (UnsupportedEncodingException e1) {
            Log.e("UnsupportedEncodingException", e1.toString());
            e1.printStackTrace();
        } catch (ClientProtocolException e2) {
            Log.e("ClientProtocolException", e2.toString());
            e2.printStackTrace();
        } catch (IllegalStateException e3) {
            Log.e("IllegalStateException", e3.toString());
            e3.printStackTrace();
        } catch (IOException e4) {
            Log.e("IOException", e4.toString());
            e4.printStackTrace();
        } catch (JSONException e) {
            e.printStackTrace();
            return null;
        }
        return arrayList;
    }
}
