package com.ganargatul.football_apps.db

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
class ClubFavoriteItems(val ID_: Int?, val CLUB_ID_: Int?,  val CLUB_NAME_ : String?, val CLUB_BADGE_ : String?) :
    Parcelable {
    companion object{
        const val TABLE_FAVORITE: String = "FAVORITE_CLUB"
        const val ID: String = "ID_"
        const val CLUB_ID: String = "CLUB_ID"
        const val CLUB_NAME : String = "CLUB_NAME"
        const val CLUB_BADGE : String = "CLUB_BADGE"
    }
}