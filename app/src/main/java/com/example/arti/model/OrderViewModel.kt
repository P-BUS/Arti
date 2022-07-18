package com.example.arti.model

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Transformations
import androidx.lifecycle.ViewModel
import com.example.arti.data.Book
import com.example.arti.data.Datasource
import java.text.NumberFormat

private const val DISCOUNT: Double = 0.1


class OrderViewModel : ViewModel() {

    private var _booksData: ArrayList<Book> = ArrayList()
    val booksData: ArrayList<Book> = _booksData

    private val _currentBook = MutableLiveData<Book>()
    val currentBook: LiveData<Book> = _currentBook

    private val _currentBookPrice = MutableLiveData<Int>()
    val currentBookPrice: LiveData<Int> = _currentBookPrice


    init {
        // Initialize the books data.
        _booksData = Datasource.loadBooks()
        _currentBook.value = _booksData[0]
    }

    // Updates current book LiveData property
    fun updateCurrentBook(book: Book) {
        _currentBook.value = book
    }

    // Updates price LiveData property
    fun updateCurrentPrice(price: Int) {
        _currentBookPrice.value = price
    }

    // Makes discount when "Buy now" button is pushed
    fun makeDiscount(bookPrice: Int) {
        val discount = bookPrice * DISCOUNT
        _currentBookPrice.value = (bookPrice.toDouble() - discount).toInt()
    }

    // Resets price of book when Cancel button is pushed
    fun resetValues() {
        _currentBookPrice.value = 0
    }

    fun setPriceFormat(price:LiveData<Int>) {
        Transformations.map(price) {
            NumberFormat.getCurrencyInstance().format(it) }
    }
}
