package com.example.firstandroid.data.model

import java.math.BigDecimal

data class BathroomData(
    val id: Long?,
    val title: String?,
    val location: String?,
    val capacity: Int?,
    val free: Boolean?,
    val cost: BigDecimal?,
    val hours: String?,
    val createdAt: String?,
    val modifiedAt: String?,
)
data class GetBathroomResponse(
    val data: List<BathroomData>,
    )
data class AddBathroomResponse(
    val message: String
)
data class UpdateBathroomResponse(
    val message: String
)


