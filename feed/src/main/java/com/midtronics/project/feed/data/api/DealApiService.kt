package com.midtronics.project.feed.data.api

import com.midtronics.project.feed.data.model.Deal
import com.midtronics.project.forecastmvvm.network.ConnectivityInterceptor
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET

const val BASE_URL = "http://content-static.bradsdeals.com/api/v1/"

interface DealApiService{

    companion object{
        operator fun invoke(connectivityInterceptor: ConnectivityInterceptor): DealApiService{
            // Adding connectivity interceptor to check for network before fetching
            val okHttpClient = OkHttpClient.Builder()
                .addInterceptor(connectivityInterceptor)
                .build()

            return Retrofit.Builder()
                .baseUrl(BASE_URL)
                .client(okHttpClient)
                .addConverterFactory(GsonConverterFactory.create())
                .build()
                .create(DealApiService::class.java)
        }
    }

    @GET("deals.json")
    suspend fun getDeals(): Deal
}