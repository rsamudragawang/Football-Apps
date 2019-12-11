package com.ganargatul.football_apps.model

import com.ganargatul.football_apps.model.LeagueDetailsItem
import com.google.gson.annotations.SerializedName

data class LeagueResponse (
    @SerializedName("leagues")
    val leagues: List<LeagueDetailsItem>?
)