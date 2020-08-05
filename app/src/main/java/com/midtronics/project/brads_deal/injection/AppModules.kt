package com.midtronics.project.brads_deal.injection

import com.midtronics.project.brads_deal.ui.FeedViewModel
import com.midtronics.project.brads_deal.data.api.DealApiService
import com.midtronics.project.brads_deal.data.repository.FeedRepository
import com.midtronics.project.brads_deal.data.repository.FeedRepositoryImpl
import com.midtronics.project.forecastmvvm.network.ConnectivityInterceptor
import com.midtronics.project.forecastmvvm.network.ConnectivityInterceptorImpl
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

var appModules = module{

    // Interceptor
    single<ConnectivityInterceptor> { ConnectivityInterceptorImpl(get())  }

    // API Service
    single { DealApiService(get()) }


    // Repository
    single<FeedRepository> {FeedRepositoryImpl(get())}


    // ViewModel
    viewModel { FeedViewModel(get()) }




}