package com.example.arti.data.network


import retrofit2.HttpException
import retrofit2.Response

suspend fun <T : Any> handleApiResponse(
    execute: suspend () -> Response<T>
): ApiResult<T> {
    return try {
        val response = execute()
        val body = response.body()
        if (response.isSuccessful && body != null) {
            ApiResult.Success(body)
        } else {
            ApiResult.Error(code = response.code(), message = response.message())
        }
    } catch (exception: HttpException) {
        ApiResult.Error(code = exception.code(), message = exception.message())
    } catch (exception: Throwable) {
        ApiResult.Exception(exception)
    }
}
