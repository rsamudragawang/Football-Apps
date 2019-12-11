package com.ganargatul.football_apps.db

import android.content.Context
import android.database.sqlite.SQLiteDatabase
import org.jetbrains.anko.db.*

class DbHelper(ctx : Context) : ManagedSQLiteOpenHelper(ctx,"FavEvent.db",null,1) {
    companion object {
        private var Instance: DbHelper? = null
        fun getInstance(ctx: Context): DbHelper {
            if (Instance == null) {
                Instance = DbHelper(ctx.applicationContext)
            }
            return Instance as DbHelper
        }
    }

    override fun onCreate(db: SQLiteDatabase?) {
        db?.createTable(
            MatchFavoriteItems.TABLE_FAVORITE, true,
            MatchFavoriteItems.ID to INTEGER + PRIMARY_KEY + AUTOINCREMENT,
            MatchFavoriteItems.EVENT_ID to INTEGER + UNIQUE,
            MatchFavoriteItems.TEAM_HOME_ID to INTEGER + UNIQUE,
            MatchFavoriteItems.TEAM_AWAY_ID to INTEGER + UNIQUE,
            MatchFavoriteItems.EVENT_NAME to TEXT
        )
        db?.createTable(
            ClubFavoriteItems.TABLE_FAVORITE,true,
            ClubFavoriteItems.ID to INTEGER + PRIMARY_KEY + AUTOINCREMENT,
            ClubFavoriteItems.CLUB_ID to INTEGER,
            ClubFavoriteItems.CLUB_NAME to TEXT,
            ClubFavoriteItems.CLUB_BADGE to TEXT
            )
    }

    override fun onUpgrade(db: SQLiteDatabase?, p1: Int, p2: Int) {
        db?.dropTable(MatchFavoriteItems.TABLE_FAVORITE, true)
        db?.dropTable(ClubFavoriteItems.TABLE_FAVORITE,true)
    }
}
val Context.database: DbHelper get() = DbHelper.getInstance(applicationContext)
