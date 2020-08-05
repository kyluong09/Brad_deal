package com.midtronics.project.brads_deal.data.model


import com.google.gson.annotations.SerializedName

data class SpecificDeal(
    val brands: List<String>,
    @SerializedName("coupon_code")
    val couponCode: String,
    val description: String,
    @SerializedName("description_preview")
    val descriptionPreview: String,
    @SerializedName("expires_at")
    val expiresAt: String,
    val headline: String,
    val id: Int,
    val image: String = ""
)