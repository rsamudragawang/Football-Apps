package com.ganargatul.football_apps.model

import com.ganargatul.football_apps.model.PastEventItems
import com.google.gson.annotations.SerializedName

data class SearchEventResponse(
    @SerializedName("event")
    val event: List<PastEventItems>? = null
)