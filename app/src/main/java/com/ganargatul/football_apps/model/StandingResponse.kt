package com.ganargatul.football_apps.model

import com.google.gson.annotations.SerializedName

data class StandingResponse(
    @SerializedName("table")
    val list: List<StandingItems>?
)