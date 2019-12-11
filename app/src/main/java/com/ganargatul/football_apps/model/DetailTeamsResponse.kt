package com.ganargatul.football_apps.model

import com.google.gson.annotations.SerializedName

data class DetailTeamsResponse (
    @SerializedName("teams")
    val events: List<DetailTeamsItems>?
)