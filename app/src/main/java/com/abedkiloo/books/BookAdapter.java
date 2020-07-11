package com.abedkiloo.books;

import android.view.View;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

public class BookAdapter  {
    public class BookVIewHolder extends RecyclerView.ViewHolder {
        TextView titleTV, authorTV, publisherTv, publishDateTv;

        public BookVIewHolder(@NonNull View itemView) {
            super(itemView);
            titleTV = itemView.findViewById(R.id.title_tv);
            authorTV = itemView.findViewById(R.id.author_tv);
            publishDateTv = itemView.findViewById(R.id.publish_date_tv);
            publisherTv = itemView.findViewById(R.id.publisher_tv);
        }

        public void bind(Book book) {
            titleTV.setText(book.title);
            String authors = "";
            int i = 0;
            for (String author : book.authors) {
                authors += author;
                i++;
                if (i < book.authors.length) {
                    authors += " ,";
                }
                authorTV.setText(authors);
                publishDateTv.setText(book.publishedDate);
                publisherTv.setText(book.publisher);
            }
        }
    }
}
