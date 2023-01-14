package com.example.arti.data.network

import retrofit2.HttpException
import retrofit2.Response

/** With a sealed interface, subclasses don’t need to be placed in the same package,
 * which means you can use the class name as it is.
 */
sealed interface ApiResult<T : Any>

class ApiSuccess<T : Any>(val data: T) : ApiResult<T>
class ApiError<T : Any>(val code: Int, val message: String?) : ApiResult<T>
class ApiException<T : Any>(val e: Throwable) : ApiResult<T>
