package com.example.arti.model

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Transformations
import androidx.lifecycle.ViewModel
import java.text.NumberFormat

private const val DISCOUNT: Double = 0.1

class OrderViewModel : ViewModel() {

    var currentBookNameId = 0
    var currentBookImageId = 0
    var currentBookAuthorId = 0
    var firstBookPrice = 0

    private val _currentBookPrice = MutableLiveData<Int>()
    val currentBookPrice: LiveData<Int> = _currentBookPrice

    fun setPriceFormat(price:LiveData<Int>) {
        Transformations.map(price) {
        NumberFormat.getCurrencyInstance().format(it) }
    }

    fun setPrice(bookPrice: Int) {
        _currentBookPrice.value = bookPrice
    }

    fun makeDiscount(bookPrice: Int) {
        val newPrice = bookPrice * DISCOUNT
        _currentBookPrice.value = (bookPrice.toDouble() - newPrice).toInt()
    }

    fun resetValues() {
        var currentBookNameId = 0
        var currentBookImageId = 0
        var currentBookAuthorId = 0
        var _currentBookPrice = 0
    }
}
