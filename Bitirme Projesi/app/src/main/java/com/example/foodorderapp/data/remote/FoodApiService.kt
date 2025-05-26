package com.example.foodorderapp.data.remote

import com.example.foodorderapp.data.model.YemekResponse
import com.example.foodorderapp.data.model.SepetResponse
import retrofit2.http.*

interface FoodApiService {
    @GET("tumYemekleriGetir.php")
    suspend fun getAllFoods(): YemekResponse

    @POST("sepeteYemekEkle.php")
    @FormUrlEncoded
    suspend fun addToCart(
        @Field("yemek_adi") yemekAdi: String,
        @Field("yemek_resim_adi") yemekResimAdi: String,
        @Field("yemek_fiyat") yemekFiyat: Int,
        @Field("yemek_siparis_adet") yemekSiparisAdet: Int,
        @Field("kullanici_adi") kullaniciAdi: String
    ): retrofit2.Response<Unit>

    @POST("sepettekiYemekleriGetir.php")
    @FormUrlEncoded
    suspend fun getCartItems(
        @Field("kullanici_adi") kullaniciAdi: String
    ): SepetResponse

    @POST("sepettenYemekSil.php")
    @FormUrlEncoded
    suspend fun removeFromCart(
        @Field("sepet_yemek_id") sepetYemekId: Int,
        @Field("kullanici_adi") kullaniciAdi: String
    ): retrofit2.Response<Unit>
}
