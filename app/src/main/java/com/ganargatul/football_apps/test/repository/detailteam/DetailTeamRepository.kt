package com.ganargatul.football_apps.test.repository.detailteam

import com.ganargatul.football_apps.connection.RetroConfig
import com.ganargatul.football_apps.model.DetailTeamsResponse
import com.ganargatul.football_apps.model.PastEventReponse
import retrofit2.Call
import retrofit2.Response

class DetailTeamRepository {
    fun getNextMatch(id: String, callback: DetailTeamRepositoryCallback<DetailTeamsResponse?>) {
        RetroConfig().services.getDetailTeam(id).enqueue(object: retrofit2.Callback<DetailTeamsResponse> {
            override fun onFailure(call: Call<DetailTeamsResponse>, t: Throwable) {
                TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
            }


            override fun onResponse(
                call: Call<DetailTeamsResponse>,
                response: Response<DetailTeamsResponse>
            ) {
//                    Log.d("presenter", response.code().toString())

                response.let {
                    if (it.isSuccessful) {
                        it.body().let {
                            it?.events?.let { it1 -> callback.onDataLoaded(it1) }
                        }
                    } else {
                        callback.onDataError()
                    }
                }
            }
        })
    }
}