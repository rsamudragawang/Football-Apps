package com.ganargatul.football_apps.test.repository.standingleague

import com.ganargatul.football_apps.connection.RetroConfig
import com.ganargatul.football_apps.model.StandingResponse
import retrofit2.Call
import retrofit2.Response

class StandingLeagueRepository {
    fun standingLeague(
        id: String,
        callback: StandingLeagueRepositoryCallback<StandingResponse?>
    ){
        RetroConfig().services.getStanding(id).enqueue(object : retrofit2.Callback<StandingResponse>{
            override fun onFailure(call: Call<StandingResponse>, t: Throwable) {
                callback.onDataError()
            }

            override fun onResponse(
                call: Call<StandingResponse>,
                response: Response<StandingResponse>
            ) {
                response.let {
                    if (it.isSuccessful) {
                        it.body().let {
                            it?.list?.let { it1 -> callback.onDataLoaded(it1) }
                        }
                    } else {
                        callback.onDataError()
                    }
                }
            }
        })
    }
}