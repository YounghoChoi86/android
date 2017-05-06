package study.shopping.org.shoppingmallapp.utils;

import android.net.Uri;
import android.support.annotation.Nullable;
import android.util.Log;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.ConnectException;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.Scanner;

/**
 * These utilities will be used to communicate with the network.
 */
public class NetworkUtils {
    final static String POSTING_BASE_URL =
            "http://52.79.54.179:8000/";
    final static String[] hasBodyMethods = {"PUT", "POST"};

    /**
     * Builds the URL used to query Posting.
     *
     * @return The URL to use to query the Posting.
     */
    public static URL buildUrl(@Nullable ArrayList<String> paths, @Nullable Integer id) {
        Uri.Builder uriBuilder = Uri.parse(POSTING_BASE_URL).buildUpon();

        if (paths != null) {
            Iterator<String> pathIter = paths.iterator();
            while (pathIter.hasNext()) {
                uriBuilder.appendPath(pathIter.next());
            }
        }
        if (id != null) {
            uriBuilder.appendPath(String.valueOf(id));
        }

        Uri builtUri = uriBuilder.build();

        URL url = null;
        try {
            url = new URL(builtUri.toString());
        } catch (MalformedURLException e) {
            e.printStackTrace();
        }
        Log.d("buildUrl()", url.toString());

        return url;
    }

    public static URL buildUrl(@Nullable String[] paths, @Nullable Integer id) {
        Uri.Builder uriBuilder = Uri.parse(POSTING_BASE_URL).buildUpon();

        if (paths != null) {
            for (String path : paths) {
                uriBuilder.appendPath(path);
            }
        }
        if (id != null) {
            uriBuilder.appendPath(String.valueOf(id));
        }

        Uri builtUri = uriBuilder.build();

        URL url = null;
        try {
            url = new URL(builtUri.toString());
        } catch (MalformedURLException e) {
            e.printStackTrace();
        }
        Log.d("buildUrl()", url.toString());

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