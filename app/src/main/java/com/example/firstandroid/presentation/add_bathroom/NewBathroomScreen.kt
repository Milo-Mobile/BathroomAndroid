package com.example.firstandroid.presentation.add_bathroom

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
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp


@Composable
fun AddBathroomDialog(
    onDismissRequest: () -> Unit,
    viewModel: NewBathroomViewModel,
) {
    AlertDialog(
        onDismissRequest = { onDismissRequest() },
        text = {
            Column {
                TextField(
                    value = viewModel.state.title,
                    onValueChange = { newTitle ->
                        viewModel.newTitle(newTitle)
                    },
                    label = { Text("Title") }
                )
                Spacer(modifier = Modifier.height(16.dp))
                TextField(
                    value = viewModel.state.location,
                    onValueChange = { newLocation ->
                        viewModel.newLocation(newLocation)
                    },
                    label = { Text("Location") }
                )
                Spacer(modifier = Modifier.height(16.dp))
                TextField(
                    value = viewModel.state.capacity.toString(),
                    onValueChange = { newCapacity ->
                        viewModel.newCapacity(newCapacity)
                    },
                    label = { Text("Capacity") }
                )
                Spacer(modifier = Modifier.height(16.dp))
                Row(verticalAlignment = Alignment.CenterVertically) {
                    Text("Free")
                    Checkbox(
                        checked = viewModel.state.free,
                        onCheckedChange = { isChecked ->
                            viewModel.isChecked(isChecked)
                        }
                    )
                }
                Spacer(modifier = Modifier.height(16.dp))
                TextField(
                    value = viewModel.state.cost.toString(),
                    onValueChange = { newCost ->
                        viewModel.newCost(newCost)
                    },
                    label = { Text("Cost") }
                )
                Spacer(modifier = Modifier.height(16.dp))
                TextField(
                    value = viewModel.state.hours,
                    onValueChange = { newHours ->
                        viewModel.newHours(newHours)
                    },
                    label = { Text("Hours") }
                )
            }
        },
        confirmButton = {
            Button(
                onClick = {
                    viewModel.onStart()
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
