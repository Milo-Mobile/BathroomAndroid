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
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import kotlinx.coroutines.launch

@Composable
fun UpdateBathroomDialog(
    onDismissRequest: () -> Unit,
    viewModel: UpdateBathroomViewModel,
    originalBathroomInfo: UpdateBathroomState,
) {

    val state = viewModel.state
    val scope = rememberCoroutineScope()
    var title by remember { mutableStateOf(originalBathroomInfo.title) }
    var location by remember { mutableStateOf(originalBathroomInfo.location) }
    var capacity by remember { mutableStateOf(originalBathroomInfo.capacity.toString()) }
    var free by remember { mutableStateOf(originalBathroomInfo.free) }
    var cost by remember { mutableStateOf(originalBathroomInfo.cost.toString()) }
    var hours by remember { mutableStateOf(originalBathroomInfo.hours) }
    var isUpdateBathroomDialogOpen by remember { mutableStateOf(true) }
    if (isUpdateBathroomDialogOpen) {
        AlertDialog(
            onDismissRequest = { onDismissRequest() },
            text = {
                Column {
                    TextField(
                        value = title,
                        onValueChange = { newTitle ->
                            title = newTitle
                            viewModel.updateTitle(title)
                        },
                        label = { Text("Title") }
                    )
                    Spacer(modifier = Modifier.height(16.dp))
                    TextField(
                        value = location,
                        onValueChange = { newLocation ->
                            location = newLocation
                            viewModel.updateLocation(location)
                        },
                        label = { Text("Location") }
                    )
                    Spacer(modifier = Modifier.height(16.dp))
                    TextField(
                        value = capacity,
                        onValueChange = { newCapacity ->
                            capacity = newCapacity
                            viewModel.updateCapacity(capacity)
                        },
                        label = { Text("Capacity") }
                    )
                    Spacer(modifier = Modifier.height(16.dp))
                    Row(verticalAlignment = Alignment.CenterVertically) {
                        Text("Free")
                        Checkbox(
                            checked = free,
                            onCheckedChange = { isChecked ->
                                free = isChecked
                                viewModel.updateFree(free)
                            }
                        )
                    }
                    Spacer(modifier = Modifier.height(16.dp))
                    TextField(
                        value = cost,
                        onValueChange = { newCost ->
                            cost = newCost
                            viewModel.updateCost(cost)
                        },
                        label = { Text("Cost") }
                    )
                    Spacer(modifier = Modifier.height(16.dp))
                    TextField(
                        value = hours,
                        onValueChange = { newHours ->
                            hours = newHours
                            viewModel.updateHours(hours)
                        },
                        label = { Text("Hours") }
                    )

                }
            },
            confirmButton = {
                Button(
                    onClick = {
                        scope.launch {
                            state.id = originalBathroomInfo.id
                            viewModel.updateBathroom(state)
                            onDismissRequest()
                        }
                    }
                ) {
                    Text("Update")
                }
            },
            dismissButton = {
                Button(
                    onClick = {
                        isUpdateBathroomDialogOpen = false
                        onDismissRequest()
                    },
                ) {
                    Text("Cancel")
                }
            }
        )
    }
}
