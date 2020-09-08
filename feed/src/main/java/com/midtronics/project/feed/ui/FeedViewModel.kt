package com.midtronics.project.feed.ui

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.midtronics.project.feed.data.usecases.FeedUseCase
import com.midtronics.project.feed.response.Result
import com.midtronics.project.feed.data.model.Deal
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class FeedViewModel(val feedUseCase: FeedUseCase): ViewModel() {
    val TAG = FeedViewModel::class.java.canonicalName

    private val _fetchedDeals = MutableLiveData<Deal?>()
    val fetchedDeals : LiveData<Deal?>
        get() = _fetchedDeals


    fun getDeals() {
        Log.d(TAG, "Preparing deals for view")

        viewModelScope.launch(Dispatchers.IO) {
           feedUseCase.getDeals().also {
                when (it) {
                    is Result.Success -> {
                        Log.d(TAG, "Successfully fetched deals: ${it.data}")
                        _fetchedDeals.postValue(it.data)
                    }
                    is Result.Error -> {
                        Log.e(TAG, "Failed to fetch deal", it.exception)
                        _fetchedDeals.postValue(null)
                    }
                }
            }

        }
    }
}
