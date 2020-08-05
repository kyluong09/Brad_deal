package com.midtronics.project.brads_deal.data.repository

import com.midtronics.project.brads_deal.data.model.Deal

interface FeedRepository {
    suspend fun getDeals(): Deal
}