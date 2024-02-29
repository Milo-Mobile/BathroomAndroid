package com.example.firstandroid.data.repository

import com.example.firstandroid.data.networking.AddBathroomResponse
import com.example.firstandroid.data.networking.GetBathroomResponse
import com.example.firstandroid.data.networking.UpdateBathroomResponse
import com.example.firstandroid.presentation.add_bathroom.NewBathroomState
import com.example.firstandroid.presentation.update_bathroom.UpdateBathroomState
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST

interface BathroomRepository {
    @GET("bathroom-info/bathrooms")
    suspend fun getAllBathroomData(): Response<GetBathroomResponse>

    @POST("bathroom-info/add")
    suspend fun addBathroomData(
        @Body requestBody: NewBathroomState
    ): Response<AddBathroomResponse>

    @POST("bathroom-info/update")
    suspend fun updateBathroomData(
        @Body requestBody: UpdateBathroomState
    ): Response<UpdateBathroomResponse>

}