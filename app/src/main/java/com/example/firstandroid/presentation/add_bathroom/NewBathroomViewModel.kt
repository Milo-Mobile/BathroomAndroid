package com.example.firstandroid.presentation.add_bathroom


import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.firstandroid.build_config.BuildConfig
import com.example.firstandroid.data.repository.BathroomRepository
import kotlinx.coroutines.launch
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class NewBathroomViewModel() : ViewModel() {
    var state by mutableStateOf(NewBathroomState())
        private set

    private lateinit var bathroomRepository: BathroomRepository

    private val retrofit = Retrofit.Builder()
        .baseUrl(BuildConfig.BASE_URL)
        .addConverterFactory(GsonConverterFactory.create())
        .build()

    fun newTitle(title: String) {
        state = state.copy(title = title)
    }

    fun newLocation(location: String) {
        state = state.copy(location = location)
    }

    fun newCapacity(capacity: String) {
        capacity.toIntOrNull()?.let {
            state = state.copy(capacity = it)
        }
    }

    fun isChecked(free: Boolean) {
        state = state.copy(free = free)
    }

    fun newCost(cost: String) {
        cost.toBigDecimalOrNull()?.let {
            state = state.copy(cost = it)
        }
    }

    fun newHours(hours: String) {
        state = state.copy(hours = hours)
    }

    fun onStart() {
        bathroomRepository = retrofit.create(BathroomRepository::class.java)
        viewModelScope.launch {
            addBathroom(state)
        }
    }
    private suspend fun addBathroom(state: NewBathroomState) {
        bathroomRepository.addBathroomData(this@NewBathroomViewModel.state)
    }
}