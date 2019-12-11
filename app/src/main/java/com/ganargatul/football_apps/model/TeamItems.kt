package com.ganargatul.football_apps.model

import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.android.parcel.Parcelize

@Parcelize
data class TeamItems(
    @SerializedName("idTeam")
    val id : String?,
    @SerializedName("strTeam")
    val name: String?,
    @SerializedName("strTeamBadge")
    val logo : String?,
    @SerializedName("idLeague")
    val idLeague: String?
) : Parcelable