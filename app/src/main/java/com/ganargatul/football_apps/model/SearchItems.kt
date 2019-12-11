package com.ganargatul.football_apps.model

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
data class SearchItems(val id: Int, val query: String) : Parcelable