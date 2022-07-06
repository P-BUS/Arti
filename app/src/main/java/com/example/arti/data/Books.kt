package com.example.arti.data

import androidx.annotation.DrawableRes
import androidx.annotation.StringRes

data class Book(
    @StringRes val bookNameId: Int,
    @DrawableRes val bookImageId: Int,
    @StringRes val bookAuthorId: Int,
    @StringRes val bookPrice: Int,
)