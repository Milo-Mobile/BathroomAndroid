package com.example.firstandroid

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentWidth
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableLongStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.lifecycle.lifecycleScope
import com.example.firstandroid.BuildConfig.BuildConfig
import com.example.firstandroid.Presentation.NewBathroom.NewBathroomViewModel
import com.example.firstandroid.Presentation.UpdateBathroom.UpdateBathroomData
import com.example.firstandroid.Presentation.UpdateBathroom.UpdateBathroomDialog
import com.example.firstandroid.Presentation.UpdateBathroom.UpdateBathroomViewModel
import com.example.firstandroid.Presentation.GetBathroom.BathroomData
import com.example.firstandroid.Presentation.NewBathroom.AddBathroomDialog
import com.example.firstandroid.data.networking.ApiService
import kotlinx.coroutines.launch
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.math.BigDecimal

class MainActivity : ComponentActivity() {
    private lateinit var apiService: ApiService
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val newBathroomViewModel: NewBathroomViewModel by viewModels()
        val updateBathroomViewModel: UpdateBathroomViewModel by viewModels()
        // Initialize Retrofit
        val retrofit = Retrofit.Builder()
            .baseUrl(BuildConfig.BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
        // Create an instance of the ApiService interface
        apiService = retrofit.create(ApiService::class.java)
        // Make the API call using coroutine lifecycleScope
        lifecycleScope.launch {
            try {
                val response = apiService.getBathroomData()
                setContent {
                    val listOfBathroomData = response.body()?.data
                    var isAddBathroomDialogOpen by remember { mutableStateOf(false) }
                    var isUpdateBathroomDialogOpen by remember { mutableStateOf(false) }
                    var selectedBathroomId by remember { mutableLongStateOf(0L) }
                    var selectedBathroomTitle by remember { mutableStateOf("") }
                    var selectedBathroomLocation by remember { mutableStateOf("") }
                    var selectedBathroomCapacity by remember { mutableIntStateOf(0) }
                    var selectedBathroomFree by remember { mutableStateOf(false) }
                    var selectedBathroomCost by remember { mutableStateOf(BigDecimal.ZERO) }
                    var selectedBathroomHours by remember { mutableStateOf("") }
                    Column(
                        modifier = Modifier.fillMaxSize(),
                    ) {
                        if (response.isSuccessful && !listOfBathroomData.isNullOrEmpty()) {
                            LazyColumn(
                                verticalArrangement = Arrangement.spacedBy(16.dp),
                                modifier = Modifier.weight(1f)
                            ) {
                                items(listOfBathroomData) { bathroom ->
                                    Row(
                                        modifier = Modifier
                                            .fillMaxWidth()
                                            .padding(16.dp),
                                        verticalAlignment = Alignment.CenterVertically,
                                        horizontalArrangement = Arrangement.SpaceBetween
                                    ) {
                                        DisplayBathroomData(bathroom)
                                        Button(
                                            onClick = {
                                                isUpdateBathroomDialogOpen = true
                                                selectedBathroomId = bathroom.id!!
                                                selectedBathroomTitle = bathroom.title!!
                                                selectedBathroomLocation = bathroom.location!!
                                                selectedBathroomCapacity = bathroom.capacity!!
                                                selectedBathroomFree = bathroom.free!!
                                                selectedBathroomCost = bathroom.cost!!
                                                selectedBathroomHours = bathroom.hours!!

                                            },
                                            modifier = Modifier.wrapContentWidth()
                                        ) {
                                            Text("Update")
                                        }
                                    }
                                }
                            }
                            if (isUpdateBathroomDialogOpen) {
                                UpdateBathroomDialog(
                                    onDismissRequest = { onBackPressed() },
                                    viewModel = updateBathroomViewModel,
                                    originalBathroomInfo = UpdateBathroomData(
                                        id = selectedBathroomId,
                                        title = selectedBathroomTitle,
                                        location = selectedBathroomLocation,
                                        capacity = selectedBathroomCapacity,
                                        free = selectedBathroomFree,
                                        cost = selectedBathroomCost,
                                        hours = selectedBathroomHours
                                    )
                                )
                            }
                        } else {
                            ErrorScreen("Error: ${response.code()}")
                        }
                        Button(
                            onClick = {
                                isAddBathroomDialogOpen = true
                            },
                            modifier = Modifier
                                .padding(start = 16.dp, end = 16.dp)
                                .align(Alignment.CenterHorizontally)
                        ) {
                            Text(text = "Add")
                        }
                        if (isAddBathroomDialogOpen) {
                            AddBathroomDialog(
                                onDismissRequest = { onBackPressed() },
                                viewModel = newBathroomViewModel
                            )
                        }
                    }
                }
            } catch (e: Exception) {
                setContent {
                    ErrorScreen("Exception: ${e.message}")
                }
            }
        }

    }


    @Composable
    private fun DisplayBathroomData(bathroomData: BathroomData) {
        Column {
            Text(text = "Bathroom Title: ${bathroomData.title}")
            Text(text = "Location: ${bathroomData.location}")
            Text(text = "Capacity: ${bathroomData.capacity}")
            Text(text = "Free: ${bathroomData.free}")
            Text(text = "Cost: ${bathroomData.cost}")
            Text(text = "Hours: ${bathroomData.hours}")
        }
    }

    @Composable
    private fun ErrorScreen(message: String) {
        Text(text = message)
    }
}