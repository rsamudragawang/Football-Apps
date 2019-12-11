package com.ganargatul.football_apps.test.repository.searchteam

import com.ganargatul.football_apps.connection.RetroConfig
import com.ganargatul.football_apps.model.SearchEventResponse
import com.ganargatul.football_apps.model.TeamResponse
import retrofit2.Call
import retrofit2.Response

class SearchTeamRepository {
    fun getNextMatch(id: String, callback: SearchTeamRepositoryCallback<TeamResponse?>) {
        RetroConfig().services.searchTeam(id).enqueue(object: retrofit2.Callback<TeamResponse> {
            override fun onFailure(call: Call<TeamResponse>, t: Throwable) {
//                Log.d("onFailure", t.message)
            }

            override fun onResponse(
                call: Call<TeamResponse>,
                response: Response<TeamResponse>
            ) {
//                    Log.d("presenter", response.code().toString())

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