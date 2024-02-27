package com.example.firstandroid.data.networking

import com.example.firstandroid.Presentation.NewBathroom.NewBathroomData
import com.example.firstandroid.Presentation.UpdateBathroom.UpdateBathroomData
import com.example.firstandroid.Presentation.GetBathroom.AddBathroomResponse
import com.example.firstandroid.Presentation.GetBathroom.GetBathroomResponse
import com.example.firstandroid.Presentation.GetBathroom.UpdateBathroomResponse
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
