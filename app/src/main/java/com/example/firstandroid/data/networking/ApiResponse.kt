package com.example.firstandroid.data.networking

import com.example.firstandroid.presentation.get_bathroom.GetBathroomState

data class GetBathroomResponse(
    val data: List<GetBathroomState>,
)

data class AddBathroomResponse(
    val message: String
)

data class UpdateBathroomResponse(
    val message: String
)