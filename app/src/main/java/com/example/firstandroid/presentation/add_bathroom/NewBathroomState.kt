package com.example.firstandroid.presentation.add_bathroom

import java.math.BigDecimal

//declare data
data class NewBathroomState(
    val title: String = "",
    val location: String = "",
    val capacity: Int = 0,
    var free: Boolean = false,
    val cost: BigDecimal = BigDecimal.ZERO,
    val hours: String = ""
)


