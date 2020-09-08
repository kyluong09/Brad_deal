package com.midtronics.project.feed.data.usecases

import com.midtronics.project.feed.data.model.Deal
import com.midtronics.project.feed.data.repository.FeedRepository
import com.midtronics.project.feed.response.Result

class FeedUserCaseImpl(val feedRepository: FeedRepository):
    FeedUseCase {

    override suspend fun getDeals(): Result<Deal> {
        return try{
            Result.Success(feedRepository.getDeals())
        }catch (e : Exception){
            Result.Error(e)
        }
    }
}