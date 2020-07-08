package com.abedkiloo.books;

import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;

public class MainActivity extends AppCompatActivity {
    private static ProgressBar progressBar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        progressBar = findViewById(R.id.progressBar);
        URL url = null;
        try {
            url = ApiUtil.books_url_builder("cooking");
            new BookQueryTask().execute(url);
        } catch (MalformedURLException e) {
            e.printStackTrace();
        }
    }

    public class BookQueryTask extends AsyncTask<URL, Void, String> {
        @Override
        protected String doInBackground(URL... urls) {
            URL searchURL = urls[0];
            String result = null;
            try {
                result = ApiUtil.getJson(searchURL);
            } catch (IOException e) {
                Log.e("ERROR_JSON_REQUEST", e.getMessage());
            }
            return result;
        }

        @Override
        protected void onPostExecute(String s) {
            TextView textViewResponse = findViewById(R.id.tvResponse);
            TextView textViewError = findViewById(R.id.textViewError);
            progressBar.setVisibility(View.INVISIBLE);
            if (s == null) {
                textViewError.setVisibility(View.VISIBLE);
                textViewResponse.setVisibility(View.INVISIBLE);
            } else {
                textViewError.setVisibility(View.INVISIBLE);
                textViewResponse.setVisibility(View.VISIBLE);
            }
            textViewResponse.setText(s);

        }

        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            progressBar.setVisibility(View.VISIBLE);

        }
    }

}
