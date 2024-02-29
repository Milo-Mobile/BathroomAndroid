package com.example.firstandroid.presentation.update_bathroom

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
import androidx.compose.runtime.DisposableEffect
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp

@Composable
fun UpdateBathroomDialog(
    onDismissRequest: () -> Unit,
    viewModel: UpdateBathroomViewModel,
) {

    val state = viewModel.state
    var title by remember { mutableStateOf(state.title) }
    var location by remember { mutableStateOf(state.location) }
    var capacity by remember { mutableStateOf(state.capacity.toString()) }
    var free by remember { mutableStateOf(state.free) }
    var cost by remember { mutableStateOf(state.cost.toString()) }
    var hours by remember { mutableStateOf(state.hours) }
//
    LaunchedEffect(key1 = viewModel){
        viewModel.onStart()
    }
    AlertDialog(
        onDismissRequest = { onDismissRequest() },
        text = {
            Column {
                TextField(
                    value = title.toString(),
                    onValueChange = { newTitle ->
                        title = newTitle
                        state.title = newTitle
                        viewModel.updateTitle(title.toString())
                    },
                    label = { Text("Title") }
                )
                Spacer(modifier = Modifier.height(16.dp))
                TextField(
                    value = location.toString(),
                    onValueChange = { newLocation ->
                        location = newLocation
                        state.location = newLocation
                        viewModel.updateLocation(location.toString())
                    },
                    label = { Text("Location") }
                )
                Spacer(modifier = Modifier.height(16.dp))
                TextField(
                    value = capacity,
                    onValueChange = { newCapacity ->
                        if (newCapacity.isNotEmpty()) {
                            try {
                                capacity = newCapacity
                                state.capacity = newCapacity.toInt()
                                viewModel.updateCapacity(capacity)
                            } catch (e: NumberFormatException) {
                                e.printStackTrace()
                            }
                        } else {
                            capacity = ""
                        }
                    },
                    label = { Text("Capacity") }
                )
                Spacer(modifier = Modifier.height(16.dp))
                Row(verticalAlignment = Alignment.CenterVertically) {
                    Text("Free")
                    Checkbox(
                        checked = free!!,
                        onCheckedChange = { isChecked ->
                            free = isChecked
                            state.free = isChecked
                            viewModel.updateFree(free!!)
                        }
                    )
                }
                Spacer(modifier = Modifier.height(16.dp))
                TextField(
                    value = cost,
                    onValueChange = { newCost ->
                        cost = newCost
                        state.cost = newCost.toBigDecimal()
                        viewModel.updateCost(cost)
                    },
                    label = { Text("Cost") }
                )
                Spacer(modifier = Modifier.height(16.dp))
                TextField(
                    value = hours.toString(),
                    onValueChange = { newHours ->
                        hours = newHours
                        state.hours = newHours
                        viewModel.updateHours(hours.toString())
                    },
                    label = { Text("Hours") }
                )

            }
        },
        confirmButton = {
            Button(
                onClick = {
                    state.id = state.id
                    viewModel.updateBathroom(state)
                    onDismissRequest()
                }
            ) {
                Text("Confirm")
            }
        },
        dismissButton = {
            Button(
                onClick = {
                    onDismissRequest()
                },
            ) {
                Text("Cancel")
            }
        }
    )
}
