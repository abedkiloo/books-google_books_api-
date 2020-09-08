package com.abedkiloo.books;

import android.net.Uri;
import android.util.Log;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
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

    public static ArrayList<Book> getBookFromJson(String jsonString) {
        final String ID = "id";
        final String TITLE = "title";
        final String SUB_TITLE = "subTitle";
        final String PUBLISHER = "publisher";
        final String AUTHORS = "authors";
        final String PUBLISHED_DATE = "publishedDate";
        final String ITEMS = "items";
        final String VOLUMES_INFO = "volumeInfo";
        ArrayList<Book> bookArrayList = new ArrayList<Book>();

        JSONObject jsonBooks = null;
        try {
            jsonBooks = new JSONObject(jsonString);

            JSONArray arrayBooks = jsonBooks.getJSONArray(ITEMS);
            int bookCount = arrayBooks.length();
            for (int i = 0; i < bookCount; i++) {
                JSONObject bookJSON = arrayBooks.getJSONObject(i);
                JSONObject volumeInfoJSON = bookJSON.getJSONObject(VOLUMES_INFO);
                 int authorCount = volumeInfoJSON.getJSONArray(AUTHORS).length();
                String[] authors = new String[authorCount];
                for (int j = 0; j < authorCount; j++) {
                    authors[j] = volumeInfoJSON.getJSONArray(AUTHORS).get(j).toString();
                }
                Book newBook = new Book(
                        bookJSON.getString(ID),
                        volumeInfoJSON.getString(TITLE),
                        (volumeInfoJSON.isNull(SUB_TITLE) ? "" : volumeInfoJSON.getString(SUB_TITLE)),
                        authors,
                        volumeInfoJSON.getString(PUBLISHER),
                        volumeInfoJSON.getString(PUBLISHED_DATE)

                );
                bookArrayList.add(newBook);
            }
            Log.e("BOOK_LISTED", bookArrayList.get(0).title);
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return bookArrayList;
    }
}
