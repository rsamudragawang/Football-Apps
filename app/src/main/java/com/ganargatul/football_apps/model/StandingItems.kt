package com.ganargatul.football_apps.model

import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.android.parcel.Parcelize

@Parcelize
data class StandingItems(
    @SerializedName("name")
    val teamName : String?,
    @SerializedName("played")
    val play: Int?,
    @SerializedName("goalsfor")
    val goal: Int?,
    @SerializedName("win")
    val win: Int?,
    @SerializedName("draw")
    val draw: Int?,
    @SerializedName("loss")
    val loss: Int?,
    @SerializedName("total")
    val point: Int?


) : Parcelable