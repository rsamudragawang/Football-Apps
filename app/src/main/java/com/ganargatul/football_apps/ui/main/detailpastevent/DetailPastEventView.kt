package com.ganargatul.football_apps.ui.main.detailpastevent

import com.ganargatul.football_apps.model.PastEventItems
import com.ganargatul.football_apps.model.PastEventReponse
import com.ganargatul.football_apps.test.repository.detailpastevent.DetailPastEventRepositoryCallback
interface DetailPastEventView  : DetailPastEventRepositoryCallback<PastEventReponse> {
    fun showLoading()
    fun hideLoading()
    fun showTeamList(data: List<PastEventItems>)
}