package com.example.davidharrischaschallenge.repository.remote

import com.example.davidharrischaschallenge.repository.remote.Constants.BASE_URL
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit

object ApiEndPoint {

    //here are our endpoints that will help specify where the data needs to come from
    //using timeouts to make sure we connect properly at a reasonable amount of time for the user
    private const val CONNECTION_TIMEOUT: Long = 100

    private val interceptor: HttpLoggingInterceptor
        get() = HttpLoggingInterceptor().apply{
            level = HttpLoggingInterceptor.Level.BODY
        }

    private val okHttpClient: OkHttpClient
        get() = OkHttpClient.Builder()
            .connectTimeout(CONNECTION_TIMEOUT, TimeUnit.SECONDS)
            .readTimeout(CONNECTION_TIMEOUT, TimeUnit.SECONDS)
            .writeTimeout(CONNECTION_TIMEOUT, TimeUnit.SECONDS)
            .addInterceptor(interceptor)
            .build()

    //retrofit for sending and receiving and creating http request
    val retrofitInstance: Retrofit
        get() = Retrofit.Builder()
            .baseUrl(BASE_URL)
            .client(okHttpClient)
            .addConverterFactory(GsonConverterFactory.create())
            .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
            .build()

}