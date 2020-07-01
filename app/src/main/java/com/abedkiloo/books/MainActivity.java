package com.abedkiloo.books;

import android.os.Bundle;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        URL url = null;
        TextView textViewResponse = findViewById(R.id.tvResponse);
        try {
            url = ApiUtil.books_url_builder("cooking");
            String jsonResult = ApiUtil.getJson(url);
            textViewResponse.setText(jsonResult);
        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
