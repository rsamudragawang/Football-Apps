package com.ganargatul.football_apps.ui.main.nextevent

import com.ganargatul.football_apps.model.NextEventResponse
import com.ganargatul.football_apps.model.NextEventsItems
import com.ganargatul.football_apps.test.repository.nextevent.NextEventRepositoryCallback

interface NextEventView : NextEventRepositoryCallback<NextEventResponse> {
    fun showLoading()
    fun hideLoading()
    fun showTeamList(data : List<NextEventsItems>)
}