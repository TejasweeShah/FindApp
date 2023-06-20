package com.codewithteju.findapp.data.remote.dto


import com.codewithteju.findapp.domain.model.Advertisement
import com.google.gson.annotations.SerializedName

data class Item(
    @SerializedName("ad-type")
    val adType: String,
    val description: String,
    val favourite: Favourite,
    val id: String,
    val image: Image,
    val location: String,
    val price: Price?,
    val score: Double,
    val shippingOption: ShippingOption?,
    val type: String,
    val url: String,
    val version: String
)

fun Item.toAdvertisement() : Advertisement {
    return Advertisement(
        id = id,
        title = description,
        location = location,
        price = price?.value ?: 0 ,
        imageUrl = image.url
    )
}
