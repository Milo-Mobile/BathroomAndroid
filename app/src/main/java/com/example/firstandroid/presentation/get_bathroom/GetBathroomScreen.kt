package com.example.firstandroid.presentation.get_bathroom

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
import androidx.compose.runtime.DisposableEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableLongStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.unit.dp
import androidx.compose.ui.Modifier
import com.example.firstandroid.presentation.add_bathroom.AddBathroomDialog
import com.example.firstandroid.presentation.add_bathroom.NewBathroomViewModel
import com.example.firstandroid.presentation.update_bathroom.UpdateBathroomDialog
import com.example.firstandroid.presentation.update_bathroom.UpdateBathroomViewModel
import java.math.BigDecimal

@Composable
fun GetBathroomDialog(
    onDismissRequest: () -> Unit,
    viewModel: GetBathroomViewModel,

    ) {

    val updateBathroomViewModel = UpdateBathroomViewModel()

    val newBathroomViewModel = NewBathroomViewModel()

    val state = viewModel.state
    val bathroomList = state.getBathroomList

    var isAddBathroomDialogOpen by remember { mutableStateOf(false) }
    var isUpdateBathroomDialogOpen by remember { mutableStateOf(false) }
    var selectedBathroomId by remember { mutableLongStateOf(0L) }
    var selectedBathroomTitle by remember { mutableStateOf("") }
    var selectedBathroomLocation by remember { mutableStateOf("") }
    var selectedBathroomCapacity by remember { mutableIntStateOf(0) }
    var selectedBathroomFree by remember { mutableStateOf(false) }
    var selectedBathroomCost by remember { mutableStateOf(BigDecimal.ZERO) }
    var selectedBathroomHours by remember { mutableStateOf("") }


    DisposableEffect(key1 = viewModel) {
        viewModel.onStart()
        onDispose { onDismissRequest()}
    }
    Column(
        modifier = Modifier.fillMaxSize(),
    ) {
        if (bathroomList.isNotEmpty()) {
            LazyColumn(
                modifier = Modifier.weight(1f)
            ) {
                items(bathroomList) { bathroom ->
                    Row(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(16.dp),
                        verticalAlignment = Alignment.CenterVertically,
                        horizontalArrangement = Arrangement.SpaceBetween
                    ) {
                        Column {
                            Text(text = "Bathroom Title: ${bathroom.title}")
                            Text(text = "Location: ${bathroom.location}")
                            Text(text = "Capacity: ${bathroom.capacity}")
                            Text(text = "Free: ${bathroom.free}")
                            Text(text = "Cost: ${bathroom.cost}")
                            Text(text = "Hours: ${bathroom.hours}")
                        }
                        Button(
                            onClick = {
                                selectedBathroomId = bathroom.id
                                selectedBathroomTitle = bathroom.title!!
                                selectedBathroomLocation = bathroom.location!!
                                selectedBathroomCapacity = bathroom.capacity!!
                                selectedBathroomFree = bathroom.free!!
                                selectedBathroomCost = bathroom.cost
                                selectedBathroomHours = bathroom.hours!!
                                isUpdateBathroomDialogOpen = true
                            },
                            modifier = Modifier.wrapContentWidth()
                        ) {
                            Text("Update")
                        }
                    }
                }
            }
            if (isUpdateBathroomDialogOpen) {
                updateBathroomViewModel.state.title = selectedBathroomTitle
                updateBathroomViewModel.state.location = selectedBathroomLocation
                updateBathroomViewModel.state.cost = selectedBathroomCost
                updateBathroomViewModel.state.free = selectedBathroomFree
                updateBathroomViewModel.state.capacity = selectedBathroomCapacity
                updateBathroomViewModel.state.hours = selectedBathroomHours
                updateBathroomViewModel.state.id = selectedBathroomId
                UpdateBathroomDialog(
                    onDismissRequest = { isUpdateBathroomDialogOpen = false},
                    viewModel = updateBathroomViewModel
                )
            }
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

        if(isAddBathroomDialogOpen){
            AddBathroomDialog(
                onDismissRequest = {isAddBathroomDialogOpen = false },
                viewModel = newBathroomViewModel)
        }
    }
}
