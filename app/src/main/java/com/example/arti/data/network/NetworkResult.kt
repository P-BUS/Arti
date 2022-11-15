package com.example.arti.data.network

sealed class NetworkResult<T> {
    data class Success<T>(val data: T, val code: Int = 200) : NetworkResult<T>()
    data class Error<T>(val throwable: Throwable) : NetworkResult<T>()
    data class Loading<T>(val isLoading: Boolean) : NetworkResult<T>()
}