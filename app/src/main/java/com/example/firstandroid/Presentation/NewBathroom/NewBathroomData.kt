package com.example.firstandroid.Presentation.NewBathroom

import java.math.BigDecimal

//declare data
data class NewBathroomData(
    val title: String = "",
    val location: String = "",
    val capacity: Int = 0,
    var free: Boolean = false,
    val cost: BigDecimal = BigDecimal.ZERO,
    val hours: String = ""
)


