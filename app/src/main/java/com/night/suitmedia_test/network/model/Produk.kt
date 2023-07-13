package com.night.suitmedia_test.network.model

import com.google.gson.annotations.SerializedName

data class Produk(
    @SerializedName("productName")
    val productName: String,
    @SerializedName("image")
    val image: Int,
)
