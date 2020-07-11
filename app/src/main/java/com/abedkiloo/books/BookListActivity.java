package com.abedkiloo.books;

import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;

public class BookListActivity extends AppCompatActivity {
    private ProgressBar progressBar;
    private RecyclerView booksRecyclerView;
    private BookAdapter bookAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.book_lists);
        progressBar = findViewById(R.id.progressBar);
        booksRecyclerView = findViewById(R.id.books_recycler);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getApplicationContext(),LinearLayoutManager.VERTICAL,false);
        booksRecyclerView.setLayoutManager(linearLayoutManager);
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
            TextView textViewError = findViewById(R.id.textViewError);
            progressBar.setVisibility(View.INVISIBLE);
            if (s == null) {
                textViewError.setVisibility(View.VISIBLE);
                booksRecyclerView.setVisibility(View.INVISIBLE);
            } else {
                textViewError.setVisibility(View.INVISIBLE);
                booksRecyclerView.setVisibility(View.VISIBLE);
            }


            ArrayList<Book> bookArrayList = ApiUtil.getBookFromJson(s);

            bookAdapter = new BookAdapter(bookArrayList);
            booksRecyclerView.setAdapter(bookAdapter);


        }

        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            progressBar.setVisibility(View.VISIBLE);


        }
    }

}
