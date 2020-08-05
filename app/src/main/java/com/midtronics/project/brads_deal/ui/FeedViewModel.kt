package com.midtronics.project.brads_deal.ui

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.midtronics.project.brads_deal.data.model.Deal
import com.midtronics.project.brads_deal.data.repository.FeedRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import java.io.IOException

class FeedViewModel(val feedRepository: FeedRepository): ViewModel() {
    val TAG = FeedViewModel::class.java.canonicalName

    private val _fetchedDeals = MutableLiveData<Deal>()
    val fetchedDeals : LiveData<Deal>
        get() = _fetchedDeals



    fun getDeals() {
        Log.d(TAG,"Preparing deals for view")

        viewModelScope.launch(Dispatchers.IO){
            try{
                val deals = feedRepository.getDeals()
                deals?.also {
                    _fetchedDeals.postValue(it)
                }
            }catch(e: IOException){

            }
        }
    }
}
