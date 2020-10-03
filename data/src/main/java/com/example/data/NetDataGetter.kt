package com.example.data

import com.example.data.retrofit.RetrofitProvider
import com.example.domain.entity.MoviesResponse
import kotlinx.coroutines.*
import retrofit2.Call
import kotlin.coroutines.resume
import kotlin.coroutines.resumeWithException


class NetDataGetter {
    val api: Api? = null

    suspend fun getMoviesCall(): MoviesResponse? {
        val api = RetrofitProvider().api
        val response = api.getMovieList().asBody()
        return response
    }
}

suspend inline fun <reified T : Any> Call<T>.asBody(
    retryAttempts: Int = 1,
    delayPeriod: Long = 0
): T {
    return suspendCancellableCoroutine { continuation ->
        CoroutineScope(continuation.context + Dispatchers.IO).launch {
            var currentAttempt = 1

            this@asBody.executeSync(object : ExecuteCallback<T> {

                override fun onSuccess(body: T) {
                    if (!continuation.isCancelled) {
                        continuation.resume(body)
                    }
                }

                override fun onFail(e: Exception) {
                    when {
                        currentAttempt < retryAttempts -> {
                            runBlocking { delay(delayPeriod) }
                            currentAttempt++
                            this@asBody.clone().executeSync(this)
                        }

                        isActive && !continuation.isCancelled -> continuation.resumeWithException(e)
                    }
                }
            })
        }

        continuation.invokeOnCancellation {
            runCatching { this@asBody.cancel() }
        }
    }
}

inline fun <reified T : Any> Call<T>.executeSync(executeCallback: ExecuteCallback<T>) {
    try {
        val response = this.execute()
        if (response.isSuccessful) {
            if (T::class == Unit::class) {
                executeCallback.onSuccess(Unit as T)
            } else {
                response.body()?.let { executeCallback.onSuccess(it) }
                    ?: throw NullPointerException("Loss response body")
            }
        } else {
            throw RuntimeException("Response is not success")
        }
    } catch (e: Exception) {
        executeCallback.onFail(e)
    }
}

interface ExecuteCallback<T> {
    fun onSuccess(body: T)
    fun onFail(e: Exception)
}