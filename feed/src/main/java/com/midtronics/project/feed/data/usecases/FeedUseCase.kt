package com.midtronics.project.feed.data.usecases

import com.midtronics.project.feed.data.model.Deal
import com.midtronics.project.feed.response.Result

interface FeedUseCase {
    suspend fun getDeals(): Result<Deal>
}