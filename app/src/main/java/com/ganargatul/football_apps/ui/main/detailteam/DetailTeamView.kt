package com.ganargatul.football_apps.ui.main.detailteam

import com.ganargatul.football_apps.model.DetailTeamsItems
import com.ganargatul.football_apps.model.DetailTeamsResponse
import com.ganargatul.football_apps.test.repository.detailteam.DetailTeamRepositoryCallback

interface DetailTeamView  : DetailTeamRepositoryCallback<DetailTeamsResponse> {
    fun showLoading()
    fun hideLoading()
    fun showTeamList(data: List<DetailTeamsItems>)
}