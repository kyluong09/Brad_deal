package com.midtronics.project.brads_deal.data.repository

import android.util.Log
import com.midtronics.project.brads_deal.data.api.DealApiService
import com.midtronics.project.brads_deal.data.model.Deal

class FeedRepositoryImpl (val dealApiService: DealApiService) : FeedRepository {
    val TAG = FeedRepositoryImpl::class.java.canonicalName

    override suspend fun getDeals(): Deal {
        Log.d(TAG,"Getting deals")
        return dealApiService.getDeals()
    }
}