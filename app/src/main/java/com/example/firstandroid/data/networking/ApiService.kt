package com.example.firstandroid.data.networking
import com.example.firstandroid.data.model.BathroomDataApi
import com.example.firstandroid.ViewModel.NewBathroom.NewBathroomData
import com.example.firstandroid.data.model.AddBathroomResponse
import retrofit2.Call
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST


interface ApiService {
    @GET("bathroom-info/bathrooms")
    suspend fun getBathroomData(): retrofit2.Response<BathroomDataApi>

    @POST("bathroom-info/add")
    suspend fun addBathroomData(
        @Body requestBody: NewBathroomData
    ): Response<AddBathroomResponse>
}
