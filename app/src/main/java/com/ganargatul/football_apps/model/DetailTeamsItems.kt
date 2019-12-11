package com.ganargatul.football_apps.model

import com.google.gson.annotations.SerializedName

data class DetailTeamsItems (
    @SerializedName("strTeam")
    val strTeam : String?,
    @SerializedName("strDescriptionEN")
    val desc: String?,
    @SerializedName("strStadium")
    val stadium: String?,
    @SerializedName("strTeamBadge")
    val strTeamBadge:String?
)