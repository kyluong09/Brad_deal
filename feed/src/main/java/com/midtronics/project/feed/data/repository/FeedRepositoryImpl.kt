package com.midtronics.project.feed.data.repository

import android.util.Log
import com.midtronics.project.feed.data.api.DealApiService
import com.midtronics.project.feed.data.model.Deal

class FeedRepositoryImpl (val dealApiService: DealApiService) : FeedRepository {
    val TAG = FeedRepositoryImpl::class.java.canonicalName

    override suspend fun getDeals(): Deal {
        Log.d(TAG,"Getting deals")
        return dealApiService.getDeals()
    }
}