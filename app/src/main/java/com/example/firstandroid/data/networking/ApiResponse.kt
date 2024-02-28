package com.example.firstandroid.data.networking

import com.example.firstandroid.presentation.get_bathroom.GetBathroomData

data class GetBathroomResponse(
    val data: List<GetBathroomData>,
)

data class AddBathroomResponse(
    val message: String
)

data class UpdateBathroomResponse(
    val message: String
)