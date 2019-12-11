package com.ganargatul.football_apps.ui.main.pastevent

import com.ganargatul.football_apps.model.PastEventItems
import com.ganargatul.football_apps.model.PastEventReponse
import com.ganargatul.football_apps.test.repository.pastevent.PastEventRepositoryCallback

interface PastEventView : PastEventRepositoryCallback<PastEventReponse> {
    fun showLoading()
    fun hideLoading()
    fun showTeamList(data: List<PastEventItems>)
}