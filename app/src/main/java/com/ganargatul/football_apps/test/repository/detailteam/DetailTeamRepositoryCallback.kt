package com.ganargatul.football_apps.test.repository.detailteam

import com.ganargatul.football_apps.model.DetailTeamsItems



interface DetailTeamRepositoryCallback<T> {
    fun onDataLoaded(data: List<DetailTeamsItems>)
    fun onDataError()
}