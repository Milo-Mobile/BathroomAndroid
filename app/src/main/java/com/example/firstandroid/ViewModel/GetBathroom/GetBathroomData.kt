package com.example.firstandroid.ViewModel.GetBathroom

import androidx.compose.runtime.MutableState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import com.example.firstandroid.BuildConfig.BuildConfig
import com.example.firstandroid.ViewModel.NewBathroom.NewBathroomData
import com.example.firstandroid.data.networking.ApiService
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.math.BigDecimal

class allBathrooms : ViewModel(){
    var state by mutableStateOf(allBathrooms())
        private set

    suspend fun getBathrooms() {
        val apiService: ApiService
        val retrofit = Retrofit.Builder()
            .baseUrl(BuildConfig.BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
        apiService = retrofit.create(ApiService::class.java)
        apiService.getBathroomData()
    }
    fun getAllBathroomsInfo(){
        
    }
}

data class BathroomInfo(
    val id: Long?,
    val title: String?,
    val location: String?,
    val capacity: Int?,
    val free: Boolean?,
    val cost: BigDecimal?,
    val hours: String?,
)
fun getBathroom(){

}
