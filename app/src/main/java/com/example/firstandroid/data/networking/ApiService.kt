package com.example.firstandroid.data.networking

import com.example.firstandroid.ViewModel.NewBathroom.NewBathroomData
import com.example.firstandroid.ViewModel.UpdateBathroom.UpdateBathroomData
import com.example.firstandroid.data.model.AddBathroomResponse
import com.example.firstandroid.data.model.GetBathroomResponse
import com.example.firstandroid.data.model.UpdateBathroomResponse
import retrofit2.Call
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST


interface ApiService {
    @GET("bathroom-info/bathrooms")
    suspend fun getBathroomData(): retrofit2.Response<GetBathroomResponse>

    @POST("bathroom-info/add")
    suspend fun addBathroomData(
        @Body requestBody: NewBathroomData
    ): Response<AddBathroomResponse>

    @POST("bathroom-info/update")
    suspend fun updateBathroomData(
        @Body requestBody: UpdateBathroomData
    ): Response<UpdateBathroomResponse>
}
