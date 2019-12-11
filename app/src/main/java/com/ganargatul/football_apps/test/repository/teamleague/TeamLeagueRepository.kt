package com.ganargatul.football_apps.test.repository.teamleague

import com.ganargatul.football_apps.connection.RetroConfig
import com.ganargatul.football_apps.model.TeamResponse
import retrofit2.Call
import retrofit2.Response

class TeamLeagueRepository {
    fun getTeam(id : String,Callback : TeamLeagueRepositoryCallback<TeamResponse?>){
        RetroConfig().services.getTeam(id).enqueue(object : retrofit2.Callback<TeamResponse>{
            override fun onFailure(call: Call<TeamResponse>, t: Throwable) {
                Callback.onDataError()
            }

            override fun onResponse(call: Call<TeamResponse>, response: Response<TeamResponse>) {
                response.let {
                    if (it.isSuccessful) {
                        it.body().let {
                            it?.list?.let { it1 -> Callback.onDataLoaded(it1) }
                        }
                    } else {
                        Callback.onDataError()
                    }
                }

            }
        })
    }
}