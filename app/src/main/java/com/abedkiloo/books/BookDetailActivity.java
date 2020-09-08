package com.abedkiloo.books;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;

import com.abedkiloo.books.databinding.ActivityNoteDetailBinding;

public class BookDetailActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_note_detail);
         Book book = getIntent().getParcelableExtra("Book");
        ActivityNoteDetailBinding binding= DataBindingUtil.setContentView(this,
                 R.layout.activity_note_detail);
        binding.setBook(book);
    }
}
