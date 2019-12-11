package com.ganargatul.football_apps.test.repository.detailleague

import com.ganargatul.football_apps.connection.RetroConfig
import com.ganargatul.football_apps.model.LeagueResponse
import retrofit2.Call
import retrofit2.Response

class DetailLeagueRepository {
    fun getNextMatch(id: String, callback: DetailLeagueRepositoryCallback<LeagueResponse?>) {
        RetroConfig().services.getDetailLeague(id).enqueue(object: retrofit2.Callback<LeagueResponse> {
            override fun onFailure(call: Call<LeagueResponse>, t: Throwable) {
                TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
            }


            override fun onResponse(
                call: Call<LeagueResponse>,
                response: Response<LeagueResponse>
            ) {
//                    Log.d("presenter", response.code().toString())

                response.let {
                    if (it.isSuccessful) {
                        it.body().let {
                            it?.leagues.let { it1 -> callback.onDataLoaded(it1) }
                        }
                    } else {
                        callback.onDataError()
                    }
                }
            }
        })
    }
}