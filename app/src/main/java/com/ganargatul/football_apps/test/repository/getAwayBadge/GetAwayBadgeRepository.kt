package com.ganargatul.football_apps.test.repository.getAwayBadge

import android.util.Log.d
import com.ganargatul.football_apps.connection.RetroConfig
import com.ganargatul.football_apps.model.DetailTeamsResponse
import retrofit2.Call
import retrofit2.Response
import java.lang.Exception

class GetAwayBadgeRepository {
    fun getNextMatch(id: String, callback: GetAwayBadgeRepositoryCallback<DetailTeamsResponse?>) {
        RetroConfig().services.getDetailTeam(id).enqueue(object: retrofit2.Callback<DetailTeamsResponse> {
            override fun onFailure(call: Call<DetailTeamsResponse>, t: Throwable) {
                TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
            }
            override fun onResponse(
                call: Call<DetailTeamsResponse>,
                response: Response<DetailTeamsResponse>
            ) {
                try {
                    response.let {
                        if (it.isSuccessful) {
                            it.body().let {
                                it?.events?.let { it1 -> callback.onBadgeAway(it1) }
                            }
                        }
                    }
                }catch (e: Exception){
                    d("e", e.printStackTrace().toString())
                }

            }
        })
    }
}