package com.example.firstandroid.presentation.get_bathroom

import java.math.BigDecimal

data class GetBathroomState(
    val id: Long = 0L,
    val title: String? = "",
    val location: String? = "",
    val capacity: Int? = 0,
    val free: Boolean? = true,
    val cost: BigDecimal? = BigDecimal.ZERO,
    val hours: String? = "",
    val createdAt: String? = "",
    val modifiedAt: String? = "",
)
data class GetBathroomDataList(
    val getBathroomList: List<GetBathroomState> = emptyList()
)


