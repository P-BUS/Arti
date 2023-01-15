package com.example.arti.data.network

import retrofit2.HttpException
import retrofit2.Response

sealed class ApiResult<T : Any> {
    class Success<T : Any>(val data: T) : ApiResult<T>()
    class Error<T : Any>(val code: Int, val message: String?) : ApiResult<T>()
    class Exception<T : Any>(val e: Throwable) : ApiResult<T>()
}
