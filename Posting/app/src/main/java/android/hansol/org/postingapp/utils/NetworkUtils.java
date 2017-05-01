package android.hansol.org.postingapp.utils;

import android.net.Uri;
import android.util.Log;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.ConnectException;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.Scanner;

/**
 * These utilities will be used to communicate with the network.
 */
public class NetworkUtils {
    final static String POSTING_BASE_URL =
            "http://192.168.43.94:5000/posts";
    final static String[] hasBodyMethods = {"PUT", "POST"};

    /**
     * Builds the URL used to query Posting.
     *
     * @return The URL to use to query the Posting.
     */
    public static URL buildUrl(String id) {
        Uri builtUri = Uri.parse(POSTING_BASE_URL).buildUpon().appendPath(id)
                .build();

        URL url = null;
        try {
            url = new URL(builtUri.toString());
        } catch (MalformedURLException e) {
            e.printStackTrace();
        }
        Log.d("buildUrl(id)", url.toString());

        return url;
    }

    public static URL buildUrl() {
        Uri builtUri = Uri.parse(POSTING_BASE_URL).buildUpon()
                .build();

        URL url = null;
        try {
            url = new URL(builtUri.toString());
        } catch (MalformedURLException e) {
            e.printStackTrace();
        }
        Log.d("buildUrl()", url.toString());

        return url;
    }

    public static String getResponseFromHttpUrl(URL url) throws IOException, ConnectException {
        HttpURLConnection urlConnection = (HttpURLConnection) url.openConnection();
        String bodyResult = "";

        try {
            InputStream in = urlConnection.getInputStream();

            Scanner scanner = new Scanner(in);

            while (scanner.hasNext()) {
                bodyResult += scanner.next();
            }


        } finally {
            urlConnection.disconnect();
        }

        if (!bodyResult.equals("")) {
            return bodyResult;
        }
        return  null;
    }

    public static String postResponseFromHttpUrl(URL url, String httpBody) throws IOException, ConnectException {
        String bodyResult = "";
        HttpURLConnection urlConnection = (HttpURLConnection) url.openConnection();
        urlConnection.setRequestMethod("POST");
        urlConnection.setRequestProperty("Content-Type", "application/json; charset=UTF-8");

        try {
            OutputStream os = urlConnection.getOutputStream();
            os.write(httpBody.toString().getBytes("UTF-8"));
            os.close();

            InputStream in = urlConnection.getInputStream();

            Scanner scanner = new Scanner(in);

            while (scanner.hasNext()) {
                bodyResult += scanner.next();
            }


        } finally {
            urlConnection.disconnect();
        }
        
        if (!bodyResult.equals("")) {
            return bodyResult;
        } else {
            return null;
        }
    }

}