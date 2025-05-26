package com.example.foodorderapp.data.model

import com.google.gson.annotations.SerializedName

data class SepetYemekModel(
    @SerializedName("sepet_yemek_id")
    val sepetYemekId: Int,
    
    @SerializedName("yemek_adi")
    val yemekAdi: String,
    
    @SerializedName("yemek_resim_adi")
    val yemekResimAdi: String,
    
    @SerializedName("yemek_fiyat")
    val yemekFiyat: Int,
    
    @SerializedName("yemek_siparis_adet")
    val yemekSiparisAdet: Int,
    
    @SerializedName("kullanici_adi")
    val kullaniciAdi: String
)

data class SepetResponse(
    @SerializedName("sepet_yemekler")
    val sepetYemekler: List<SepetYemekModel>?,
    
    @SerializedName("success")
    val success: Int
)
