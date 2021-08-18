package com.example.mypokemonapp.network

import com.example.mypokemonapp.model.UploadResponse
import okhttp3.MultipartBody
import okhttp3.RequestBody
import retrofit2.Call
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.Multipart
import retrofit2.http.POST
import retrofit2.http.Part

interface UploadApi {

    @Multipart
    @POST("upload")
    fun uploadImage(
        @Part image: MultipartBody.Part,
        @Part("desc") desc: RequestBody
    ): Call<UploadResponse>

    companion object {
        operator fun invoke():UploadApi {
            return Retrofit.Builder()
                .baseUrl("https://darot-image-upload-service.herokuapp.com/api/v1/")
                .addConverterFactory(GsonConverterFactory.create())
                .build()
                .create(UploadApi::class.java)

        }
    }
}