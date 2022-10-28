package com.example.arti.data.network

import kotlinx.coroutines.flow.FlowCollector
import retrofit2.Response

class BaseApiResponse {

    suspend fun <T> safeApiCall(apiCall: suspend () -> Response<T>): NetworkResult<T> {
        return try {
            val response = apiCall()
            if (response.isSuccessful) {
                val body = response.body()
                body?.let {
                    return NetworkResult.Success(body)
                }
            }
            error("${response.code()} ${response.message()}")
        } catch (e: Exception) {
            NetworkResult.Error(e)
        }
    }

    suspend fun <T> wrapperSafeApiCallWithLoading(
        flowCollector: FlowCollector<NetworkResult<T>>,
        apiCall: suspend () -> Response<T>
    ) {
        flowCollector.emit(NetworkResult.Loading(true))
        flowCollector.emit(safeApiCall { apiCall() })
        flowCollector.emit(NetworkResult.Loading(false))
    }

    private fun <T> error(errorMessage: String): NetworkResult<T> =
        NetworkResult.Error(Throwable("Api call failed $errorMessage"))

    suspend fun <U> safeApiCallServiceResponse(apiCall: suspend () -> Response<ServiceResponse<U>>): NetworkResult<U> {
        return try {
            val upstream = apiCall()
            if (upstream.isSuccessful) {
                val response = upstream.body()
                if (response != null) {
                    if (response.isSuccess) {
                        if (upstream.body()?.data != null)
                            NetworkResult.Success(upstream.body()!!.data!!)
                        else
                            NetworkResult.Error(NoContentException())
                    } else {
                        if (response.errors != null && response.errors!!.isNotEmpty())
                            NetworkResult.Error(DecathlonException(response.errors))
                        else
                            NetworkResult.Error(DecathlonException())
                    }
                } else {
                    NetworkResult.Error(Throwable("response null"))
                }
            } else {
                error("${upstream.code()} ${upstream.message()}")
            }
        } catch (e: Exception) {
            NetworkResult.Error(e)
        }
    }
}