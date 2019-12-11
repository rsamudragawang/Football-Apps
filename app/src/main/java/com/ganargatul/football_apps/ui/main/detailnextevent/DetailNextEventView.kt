package com.ganargatul.football_apps.ui.main.detailnextevent

import com.ganargatul.football_apps.model.DetailTeamsItems
import com.ganargatul.football_apps.model.DetailTeamsResponse
import com.ganargatul.football_apps.test.repository.detailnextevent.DetailNextEventRepositoryCallback

interface DetailNextEventView  : DetailNextEventRepositoryCallback<DetailTeamsResponse> {
    fun showLoading()
    fun hideLoading()
    fun showTeamList(data: List<DetailTeamsItems>)
}