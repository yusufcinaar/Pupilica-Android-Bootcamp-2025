package com.example.foodorderapp.data.model

import com.google.gson.annotations.SerializedName

data class YemekModel(
    @SerializedName("yemek_id")
    val yemekId: String,
    
    @SerializedName("yemek_adi")
    val yemekAdi: String,
    
    @SerializedName("yemek_resim_adi")
    val yemekResimAdi: String,
    
    @SerializedName("yemek_fiyat")
    val yemekFiyat: String
)

data class YemekResponse(
    @SerializedName("yemekler")
    val yemekler: List<YemekModel>,
    
    @SerializedName("success")
    val success: Int
)
