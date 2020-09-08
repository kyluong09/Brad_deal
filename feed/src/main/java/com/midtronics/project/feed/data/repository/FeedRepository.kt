package com.midtronics.project.feed.data.repository

import com.midtronics.project.feed.data.model.Deal

interface FeedRepository {
    suspend fun getDeals(): Deal
}