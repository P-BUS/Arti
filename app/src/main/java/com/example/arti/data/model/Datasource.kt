package com.example.arti.data.model

import com.example.arti.R
import com.example.arti.data.OpenLibraryBook

object Datasource {

    fun loadBooks(): ArrayList<OpenLibraryBook> {
        return arrayListOf(
            OpenLibraryBook(
                bookNameId = R.string.book_name_1,
                bookImageId = R.drawable.image1,
                bookAuthorId = R.string.book_author_1,
                bookPrice = 200),
            OpenLibraryBook(
                bookNameId = R.string.book_name_2,
                bookImageId = R.drawable.image2,
                bookAuthorId = R.string.book_author_2,
                bookPrice = 250),
            OpenLibraryBook(
                bookNameId = R.string.book_name_3,
                bookImageId = R.drawable.image3,
                bookAuthorId = R.string.book_author_3,
                bookPrice = 200),
            OpenLibraryBook(
                bookNameId = R.string.book_name_4,
                bookImageId = R.drawable.image4,
                bookAuthorId = R.string.book_author_4,
                bookPrice = 300),
            OpenLibraryBook(
                bookNameId = R.string.book_name_5,
                bookImageId = R.drawable.image5,
                bookAuthorId = R.string.book_author_4,
                bookPrice = 400),
            OpenLibraryBook(
                bookNameId = R.string.book_name_6,
                bookImageId = R.drawable.image6,
                bookAuthorId = R.string.book_author_4,
                bookPrice = 300),
            OpenLibraryBook(
                bookNameId = R.string.book_name_7,
                bookImageId = R.drawable.image7,
                bookAuthorId = R.string.book_author_1,
                bookPrice = 200),
            OpenLibraryBook(
                bookNameId = R.string.book_name_8,
                bookImageId = R.drawable.image8,
                bookAuthorId = R.string.book_author_1,
                bookPrice = 200),
            OpenLibraryBook(
                bookNameId = R.string.book_name_9,
                bookImageId = R.drawable.image9,
                bookAuthorId = R.string.book_author_5,
                bookPrice = 100),
            OpenLibraryBook(
                bookNameId = R.string.book_name_10,
                bookImageId = R.drawable.image10,
                bookAuthorId = R.string.book_author_6,
                bookPrice = 200),
        )
    }
}