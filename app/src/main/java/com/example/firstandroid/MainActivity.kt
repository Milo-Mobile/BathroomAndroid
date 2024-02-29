package com.example.firstandroid

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
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
import com.example.firstandroid.presentation.add_bathroom.NewBathroomViewModel
import com.example.firstandroid.presentation.update_bathroom.UpdateBathroomViewModel
import com.example.firstandroid.presentation.get_bathroom.GetBathroomData
import com.example.firstandroid.presentation.add_bathroom.AddBathroomDialog
import com.example.firstandroid.presentation.get_bathroom.GetBathroomDialog
import com.example.firstandroid.presentation.get_bathroom.GetBathroomViewModel
import kotlinx.coroutines.launch
import java.math.BigDecimal

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val getBathroomViewModel: GetBathroomViewModel by viewModels()

        lifecycleScope.launch {
            try {
                setContent {
                    var isGetBathroomDialogOpen by remember { mutableStateOf(true) }
                    Column(
                        modifier = Modifier.fillMaxSize(),
                    ) {
                        if(isGetBathroomDialogOpen){
                            GetBathroomDialog(
                                onDismissRequest = { onBackPressed() },
                                viewModel = getBathroomViewModel
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
    private fun ErrorScreen(message: String) {
        Text(text = message)
    }
}