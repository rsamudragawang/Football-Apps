package com.ganargatul.football_apps.test.repository.detailpastevent

import com.ganargatul.football_apps.connection.RetroConfig
import com.ganargatul.football_apps.model.PastEventReponse
import retrofit2.Call
import retrofit2.Response

class DetailPastEventRepository {
    fun getNextMatch(id: String, callback: DetailPastEventRepositoryCallback<PastEventReponse?>) {
        RetroConfig().services.getDetailEvent(id).enqueue(object: retrofit2.Callback<PastEventReponse> {
            override fun onFailure(call: Call<PastEventReponse>, t: Throwable) {
                TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
            }


            override fun onResponse(
                call: Call<PastEventReponse>,
                response: Response<PastEventReponse>
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