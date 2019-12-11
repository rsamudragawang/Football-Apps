package com.ganargatul.football_apps.test.repository.detailnextevent


import com.ganargatul.football_apps.model.DetailTeamsItems

interface DetailNextEventRepositoryCallback<T> {
    fun onDataLoaded(data: List<DetailTeamsItems>)
    fun onDataError()
}