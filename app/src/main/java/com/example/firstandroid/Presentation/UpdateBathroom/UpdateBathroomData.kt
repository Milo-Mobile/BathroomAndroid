package com.example.firstandroid.Presentation.UpdateBathroom

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import com.example.firstandroid.BuildConfig.BuildConfig
import com.example.firstandroid.data.networking.ApiService
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.math.BigDecimal


class UpdateBathroomViewModel() : ViewModel() {

    var state by mutableStateOf(UpdateBathroomData())
        private set

    fun updateId(id: Long){
        state = state.copy(id = id)
    }
    fun updateTitle(title: String) {
        state = state.copy(title = title)
    }

    fun updateLocation(location: String) {
        state = state.copy(location = location)
    }

    fun updateCapacity(capacity: String) {
        capacity.toIntOrNull()?.let { state = state.copy(capacity = it) }
    }

    fun updateFree(free: Boolean) {
        state = state.copy(free = free)
    }

    fun updateCost(cost: String) {
        cost.toBigDecimalOrNull()?.let {
            state = state.copy(cost = it)
        }
    }

    fun updateHours(hours: String) {
        state = state.copy(hours = hours)
    }

    suspend fun updateBathroom(state: UpdateBathroomData) {
        val apiService: ApiService
        val retrofit = Retrofit.Builder()
            .baseUrl(BuildConfig.BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
        apiService = retrofit.create(ApiService::class.java)
        apiService.updateBathroomData(state)
    }
}
data class UpdateBathroomData(
    var id: Long =0L,
    var title: String = "",
    var location: String = "",
    var capacity: Int = 0,
    var free: Boolean = false,
    var cost: BigDecimal = BigDecimal.ZERO,
    var hours: String = ""
)

