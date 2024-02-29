package com.example.firstandroid

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.lifecycle.lifecycleScope
import com.example.firstandroid.presentation.get_bathroom.GetBathroomDialog
import com.example.firstandroid.presentation.get_bathroom.GetBathroomViewModel
import kotlinx.coroutines.launch

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val getBathroomViewModel: GetBathroomViewModel by viewModels()

        lifecycleScope.launch {
            try {
                setContent {
                    GetBathroomDialog(
                        onDismissRequest = { onBackPressed() },
                        viewModel = getBathroomViewModel
                    )
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