package com.example.firstandroid.ViewModel.UpdateBathroom

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
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.example.firstandroid.ViewModel.NewBathroom.NewBathroomViewModel
import kotlinx.coroutines.launch

@Composable
fun UpdateBathroomDialog(
    onDismissRequest: () -> Unit,
    viewModel: UpdateBathroomViewModel,
) {
    val state = viewModel.state
    val scope = rememberCoroutineScope()
    //remember is a function that allows you to create
    // and retain a value across recompositions.
    AlertDialog(
        onDismissRequest = { onDismissRequest() },
        text = {
            Column {
                TextField(
                    value = state.title,
                    onValueChange = { newTitle ->
                        viewModel.updateTitle(newTitle)
                    },
                    label = { Text("Title") }
                )
                Spacer(modifier = Modifier.height(16.dp))
                TextField(
                    value = state.location,
                    onValueChange = { newLocation ->
                        viewModel.updateLocation(newLocation)
                    },
                    label = { Text("Location") }
                )
                Spacer(modifier = Modifier.height(16.dp))
                TextField(
                    value = state.capacity.toString(),
                    onValueChange = { newCapacity ->
                        viewModel.updateCapacity(newCapacity)
                    },
                    label = { Text("Capacity") }
                )
                Spacer(modifier = Modifier.height(16.dp))
                Row(verticalAlignment = Alignment.CenterVertically) {
                    Text("Free")
                    Checkbox(
                        checked = state.free,
                        onCheckedChange = { isChecked ->
                            viewModel.updateFree(isChecked)
                        }
                    )
                }
                Spacer(modifier = Modifier.height(16.dp))
                TextField(
                    value = state.cost.toString(),
                    onValueChange = { newCost ->
                        viewModel.updateCost(newCost)
                    },
                    label = { Text("Cost") }
                )
                Spacer(modifier = Modifier.height(16.dp))
                TextField(
                    value = state.hours,
                    onValueChange = { newHours ->
                        viewModel.updateHours(newHours)
                    },
                    label = { Text("Hours") }
                )
            }
        },
        confirmButton = {
            Button(
                onClick = {
                    scope.launch {
                        viewModel.updateBathroom(state)
                    }
                }
            ) {
                Text("Add")
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
