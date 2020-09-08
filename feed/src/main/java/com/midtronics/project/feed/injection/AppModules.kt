package com.midtronics.project.feed.injection

import com.midtronics.project.feed.data.usecases.FeedUseCase
import com.midtronics.project.feed.data.usecases.FeedUserCaseImpl
import com.midtronics.project.feed.ui.FeedViewModel
import com.midtronics.project.feed.data.api.DealApiService
import com.midtronics.project.feed.data.repository.FeedRepository
import com.midtronics.project.feed.data.repository.FeedRepositoryImpl
import com.midtronics.project.forecastmvvm.network.ConnectivityInterceptor
import com.midtronics.project.forecastmvvm.network.ConnectivityInterceptorImpl
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

var appModules = module{

    // Interceptor
    single<ConnectivityInterceptor> { ConnectivityInterceptorImpl(get())  }

    // API Service
    single { DealApiService(get()) }

    // UserCase

    single<FeedUseCase> {
        FeedUserCaseImpl(
            get()
        )
    }


    // Repository
    single<FeedRepository> {FeedRepositoryImpl(get())}


    // ViewModel
    viewModel { FeedViewModel(get()) }




}