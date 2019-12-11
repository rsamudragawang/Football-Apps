package com.ganargatul.football_apps.model

import com.google.gson.annotations.SerializedName

data class TeamResponse (
    @SerializedName("teams")
    val list: List<TeamItems>?
)