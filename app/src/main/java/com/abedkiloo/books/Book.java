package com.abedkiloo.books;

import android.os.Parcel;
import android.os.Parcelable;
import android.text.TextUtils;

public class Book implements Parcelable {


    public String id;
    public String title;
    public String subTitle;
    public String authors;
    public String publisher;
    public String publishedDate;

    public Book(String id, String title, String subTitle, String[] authors, String publisher, String publishedDate) {
        this.id = id;
        this.title = title;
        this.subTitle = subTitle;
        this.authors = TextUtils.join(", ", authors);
        this.publisher = publisher;
        this.publishedDate = publishedDate;
    }

    protected Book(Parcel parcel) {
        id = parcel.readString();
        title = parcel.readString();
        subTitle = parcel.readString();
        authors = parcel.readString();
        publisher = parcel.readString();
        publishedDate = parcel.readString();
    }

    public static final Creator<Book> CREATOR = new Creator<Book>() {
        @Override
        public Book createFromParcel(Parcel source) {
            return new Book(source);
        }

        @Override
        public Book[] newArray(int size) {
            return new Book[size];
        }
    };

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(id);
        dest.writeString(title);
        dest.writeString(subTitle);
        dest.writeString(authors);
        dest.writeString(publishedDate);
        dest.writeString(publisher);
    }
}
