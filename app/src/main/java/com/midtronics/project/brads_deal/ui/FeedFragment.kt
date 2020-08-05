package com.midtronics.project.brads_deal.ui

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.Observer
import com.google.android.material.dialog.MaterialAlertDialogBuilder
import com.midtronics.project.brads_deal.R
import com.midtronics.project.brads_deal.data.model.Deal
import kotlinx.android.synthetic.main.first_fragment.*
import org.koin.androidx.viewmodel.ext.android.viewModel


class FeedFragment : Fragment(), FeedRecyclerAdapter.FeedRecyclerListener {

    val TAG = FeedFragment::class.java.canonicalName
    // ViewModel
    val feedViewModel: FeedViewModel by viewModel()
    // Recycler Adapter
    lateinit var recyclerAdapter: FeedRecyclerAdapter
    // Deal
    lateinit var deals: Deal

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.first_fragment, container, false)
    }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setUpRecyclerView()


        feedViewModel.fetchedDeals.observe(this, Observer {
            Log.d(TAG,"Received deals")
            if(!it.deals.isNullOrEmpty()){
                // Cache object for later onclick use
                deals = it
                process_bar.visibility = View.GONE
                recycler_view_feed.visibility = View.VISIBLE
                recyclerAdapter.setDealList(it.deals)
            }
        })


    }


    override fun onResume() {
        super.onResume()
        // Fetch deals
        feedViewModel.getDeals()
    }

    private fun setUpRecyclerView(){
        Log.d(TAG,"Setting up recyclerview")
        recyclerAdapter = FeedRecyclerAdapter(this)
        recycler_view_feed.adapter = recyclerAdapter
    }

    override fun onFeedClickListener(position: Int) {
        Log.d(TAG,"Clicked on feed position $position")
        // Popup dialog that shows deal description
        deals?.apply {
            val deal = this.deals[position]
            val dialog = MaterialAlertDialogBuilder(context)
            dialog.setTitle(deal.headline)
                .setMessage(deal.description)
                .setNeutralButton(getString(R.string.done),{dialog, which ->
                    dialog.dismiss()
                })
                .show()

        }

    }

}
