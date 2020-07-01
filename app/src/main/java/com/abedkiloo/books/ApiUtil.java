package com.abedkiloo.books;

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

    private ApiUtil() {
    }

    public static URL books_url_builder(String param) throws MalformedURLException {
        String full_url = BASE_API_URL + "q=" + param;
        URL url = null;

        return url = new URL(full_url);

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
