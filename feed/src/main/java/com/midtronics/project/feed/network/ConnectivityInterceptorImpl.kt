package com.midtronics.project.forecastmvvm.network

import android.accounts.NetworkErrorException
import android.content.Context
import android.net.ConnectivityManager
import com.google.android.material.dialog.MaterialAlertDialogBuilder
import com.midtronics.project.brads_deal.R
import okhttp3.Interceptor
import okhttp3.Response
import java.io.IOException
import java.lang.Exception

class ConnectivityInterceptorImpl(val context: Context) : ConnectivityInterceptor {

    override fun intercept(chain: Interceptor.Chain): Response {
        if(!isNetworkAvailable()){
           throw IOException()
        }
        return chain.proceed(chain.request())
    }


    fun isNetworkAvailable(): Boolean{
        val connectManager = context.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
        val networkInfo = connectManager.activeNetworkInfo
        return networkInfo != null && networkInfo.isConnected
    }
}