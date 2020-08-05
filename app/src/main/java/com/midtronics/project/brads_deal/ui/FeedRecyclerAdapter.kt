package com.midtronics.project.brads_deal.ui

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.load.engine.DiskCacheStrategy
import com.midtronics.project.brads_deal.R
import com.midtronics.project.brads_deal.data.model.SpecificDeal
import kotlinx.android.synthetic.main.view_feed.view.*

class FeedRecyclerAdapter(val feedRecyclerListener:FeedRecyclerListener) : RecyclerView.Adapter<FeedRecyclerAdapter.ViewHolder>() {

    private var dealList = emptyList<SpecificDeal>()


    interface FeedRecyclerListener{
        fun onFeedClickListener(position: Int)
    }

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): FeedRecyclerAdapter.ViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.view_feed, parent, false)

        return ViewHolder(view,feedRecyclerListener)
    }

    override fun getItemCount(): Int {
        return dealList.size
    }

    override fun onBindViewHolder(holder: FeedRecyclerAdapter.ViewHolder, position: Int) {
        holder.bind(dealList[position])
    }


    class ViewHolder(val view: View, val feedRecyclerListener: FeedRecyclerListener) : RecyclerView.ViewHolder(view), View.OnClickListener {
        val dealName : TextView = view.text_deal_name
        val brandName : TextView = view.text_brand_name
        val expiredDate : TextView = view.text_expired_date
        val dealImage : ImageView = view.image_deal

        fun bind(specificDeal : SpecificDeal){
            if(!specificDeal.brands.isEmpty()){
                brandName.visibility = View.VISIBLE
                brandName.text = specificDeal.brands[0]
            }
            dealName.text = specificDeal.headline
            expiredDate.text = specificDeal.expiresAt
            Glide.with(view)
                .load(specificDeal.image)
                .diskCacheStrategy(DiskCacheStrategy.ALL)
                .fitCenter()
                .error(R.drawable.ic_error_black_24dp)
                .into(dealImage)

            view.setOnClickListener(this)

        }

        override fun onClick(p0: View?) {
            feedRecyclerListener.onFeedClickListener(adapterPosition)
        }
    }

    fun setDealList(specificDealList: List<SpecificDeal>) {
        this.dealList = specificDealList
        notifyDataSetChanged()
    }


}