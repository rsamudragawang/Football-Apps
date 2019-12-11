package com.ganargatul.football_apps.model

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
data class LeagueItems (val name: String?,
                        val description: String?,
                        val image: Int?,val id:Int?) : Parcelable