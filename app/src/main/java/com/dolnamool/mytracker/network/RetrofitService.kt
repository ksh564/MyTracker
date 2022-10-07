package com.dolnamool.mytracker.network

import com.google.gson.GsonBuilder
import okhttp3.Interceptor
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit

interface RetrofitService {

    companion object {
        private var retrofitService: RetrofitService? = null
        fun getInstance(): RetrofitService {

            val httpInterceptor = HttpLoggingInterceptor().setLevel(HttpLoggingInterceptor.Level.BODY)

            val listener = Interceptor { chain -> val original = chain.request()
                .newBuilder()
                .header("Accept","application/json")
                .build()
                chain.proceed(original) }

            val okHttpClient = OkHttpClient.Builder().addInterceptor(listener)
                .connectTimeout(15, TimeUnit.SECONDS)
                .writeTimeout(15, TimeUnit.SECONDS)
                .readTimeout(30, TimeUnit.SECONDS)
                .addInterceptor(httpInterceptor)
                .build()

            val gson = GsonBuilder()
                .setDateFormat("yyyy-MM-dd HH:mm:ss")
                .create()

            if (retrofitService ==  null) {
                val retrofit = Retrofit.Builder()
                    .baseUrl("http://118.219.45.141:53000/")
                    .client(okHttpClient)
                    .addConverterFactory(GsonConverterFactory.create(gson))
                    .build()
                retrofitService = retrofit.create(RetrofitService::class.java)
            }
            return retrofitService!!
        }
    }
}