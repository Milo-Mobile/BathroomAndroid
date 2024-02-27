package com.example.firstandroid.Presentation.UpdateBathroom

import java.math.BigDecimal

data class UpdateBathroomData(
    var id: Long = 0L,
    var title: String = "",
    var location: String = "",
    var capacity: Int = 0,
    var free: Boolean = false,
    var cost: BigDecimal = BigDecimal.ZERO,
    var hours: String = ""
)

