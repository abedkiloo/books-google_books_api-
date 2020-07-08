package com.abedkiloo.books;

import android.net.Uri;
import android.util.Log;

import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.Scanner;

public class ApiUtil {
    public static final String BASE_API_URL =
            "https://www.googleapis.com/books/v1/volumes";
    private static final String QUERY_PARAM = "q";
    private static String API_KEY = "AIzaSyD8VartiyNJ_hRcWUh4bXKWRWamOMNxCt8";
    private static String KEY = "key";

    private ApiUtil() {
    }

    public static URL books_url_builder(String title) throws MalformedURLException {
        Uri uri = Uri.parse(BASE_API_URL).buildUpon()
                .appendQueryParameter(QUERY_PARAM, title)
                .appendQueryParameter(KEY, API_KEY)
                .build();
        URL url = null;


        url = new URL(uri.toString());


        return url;
    }

    /* connect to the api     */
    public static String getJson(URL url) throws IOException {
        HttpURLConnection httpURLConnection = (HttpURLConnection) url.openConnection();

        InputStream inputStream;
        try {
            inputStream = httpURLConnection.getInputStream();
            Scanner scanner = new Scanner(inputStream);
            scanner.useDelimiter("\\A");
            boolean hasData = scanner.hasNext();
            if (hasData)
                return scanner.next();
            else
                return null;
        } catch (Exception e) {
            Log.e("ERROR READING DATA", e.getMessage());
            return null;
        }
        // close the connection
        finally {
            httpURLConnection.disconnect();
        }
    }
}
