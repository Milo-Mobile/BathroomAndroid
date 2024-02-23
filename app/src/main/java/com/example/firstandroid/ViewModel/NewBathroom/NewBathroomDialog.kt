package com.example.firstandroid.ViewModel.NewBathroom

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Button
import androidx.compose.material3.Checkbox
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.example.firstandroid.data.model.AddBathroomResponse
import com.example.firstandroid.data.networking.ApiService


//This annotation tells the Compose compiler that the functions is meant for UI construction.
@Composable
fun AddBathroomDialog(
    onDismiss: () -> Unit,
    viewModel: NewBathroomViewModel,
    apiService: ApiService
    ) {
    val state = viewModel.state

    //remember is a function that allows you to create
    // and retain a value across recompositions.
    AlertDialog(
        onDismissRequest = onDismiss,
        title = { Text(text = "Add New Item") },
        text = {
            Column {
                TextField(
                    value = state.title,
                    onValueChange = {newTitle ->
                        viewModel.updateTitle(newTitle)
                    },
                    label = { Text("Title") }
                )
                Spacer(modifier = Modifier.height(16.dp))
                TextField(
                    value = state.location,
                    onValueChange = {newLocation ->
                        viewModel.updateLocation(newLocation)
                    },
                    label = { Text("Location") }
                )
                Spacer(modifier = Modifier.height(16.dp))
                TextField(
                    value = state.capacity.toString(),
                    onValueChange = {newCapacity ->
                                    viewModel.updateCapacity(newCapacity)
                    },
                    label = { Text("Capacity") }
                )
                Spacer(modifier = Modifier.height(16.dp))
                Row(verticalAlignment = Alignment.CenterVertically) {
                    Text("Free")
                    Checkbox(
                        checked = state.free,
                        onCheckedChange = {
                            isChecked ->
                            viewModel.updateFree(isChecked)
                        }
                    )
                }
                Spacer(modifier = Modifier.height(16.dp))
                TextField(
                    value = state.cost.toString(),
                    onValueChange = {
                        newCost -> viewModel.updateCost(newCost)
                    },
                    label = { Text("Cost") }
                )
                Spacer(modifier = Modifier.height(16.dp))
                TextField(
                    value = state.hours,
                    onValueChange = {
                        newHours -> viewModel.updateHours(newHours)
                    },
                    label = { Text("Hours") }
                )
            }
        },
        confirmButton = {
            Button(
                onClick = {
                    viewModel.addBathroom()
                }
            ) {
                Text("Add")
            }
        },
        dismissButton = {
            Button(
                onClick = { onDismiss() }
            ) {
                Text("Cancel")
            }
        }
    )
}