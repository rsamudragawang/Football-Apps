package com.ganargatul.football_apps.model

import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.android.parcel.Parcelize

@Parcelize
data class NextEventsItems (
    @SerializedName("idEvent")
    val idEvent: Int?,
    @SerializedName("strEvent")
    val strEvent:String?,
    @SerializedName("dateEvent")
    val dateEvent: String?,
    @SerializedName("strTime")
    val strTime: String?,
    @SerializedName("idHomeTeam")
    val idHomeTeam: String?,
    @SerializedName("idAwayTeam")
    val idAwayTeam:String?

) : Parcelable