package com.example.firstandroid.presentation.update_bathroom

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.firstandroid.build_config.BuildConfig
import com.example.firstandroid.data.repository.BathroomRepository
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.launch
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class UpdateBathroomViewModel() : ViewModel() {

    var state by mutableStateOf(UpdateBathroomState())
        private set
    private val _name = MutableStateFlow("")
    val title = MutableStateFlow(state.title)
    private val newTitle = title
    private lateinit var bathroomRepository: BathroomRepository

    private val retrofit = Retrofit.Builder()
        .baseUrl(BuildConfig.BASE_URL)
        .addConverterFactory(GsonConverterFactory.create())
        .build()

    fun updateId(id: Long) {
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

    fun onStart() {
        bathroomRepository = retrofit.create(BathroomRepository::class.java)
    }

    fun updateBathroom(state: UpdateBathroomState) {
        viewModelScope.launch {
            bathroomRepository.updateBathroomData(state)
        }
    }
}