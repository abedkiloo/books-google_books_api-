package com.abedkiloo.books;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class BookAdapter extends RecyclerView.Adapter<BookAdapter.BookViewHolder> {
    static ArrayList<Book> books = new ArrayList<>();

    public BookAdapter(ArrayList<Book> books) {
        this.books = books;
    }

    @NonNull
    @Override
    public BookViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        Context context = parent.getContext();
        View view = LayoutInflater.from(context).inflate(R.layout.book_item_design, parent, false);
        return new BookViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull BookViewHolder holder, int position) {
        Book book = books.get(position);
        holder.bind(book);
    }

    @Override
    public int getItemCount() {
        return books.size();
    }


    static class BookViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        TextView titleTV, authorTV, publisherTv, publishDateTv;

        public BookViewHolder(@NonNull View itemView) {
            super(itemView);
            titleTV = itemView.findViewById(R.id.title_tv);
            authorTV = itemView.findViewById(R.id.author_tv);
            publishDateTv = itemView.findViewById(R.id.publish_date_tv);
            publisherTv = itemView.findViewById(R.id.publisher_tv);
            itemView.setOnClickListener(this);
        }

        void bind(Book book) {

            titleTV.setText(book.title);
            String authors = "";

            authorTV.setText(authors);
            publishDateTv.setText(book.publishedDate);
            publisherTv.setText(book.publisher);

        }

        @Override
        public void onClick(View v) {
            int pos = getAdapterPosition();
            Book selectedBook = books.get(pos);
            Intent intent = new Intent(v.getContext(), BookDetailActivity.class);
            intent.putExtra("Book", selectedBook);
            v.getContext().startActivity(intent);
        }
    }
}
