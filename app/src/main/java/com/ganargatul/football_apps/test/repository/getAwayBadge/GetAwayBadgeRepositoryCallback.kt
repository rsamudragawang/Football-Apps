package com.ganargatul.football_apps.test.repository.getAwayBadge


import com.ganargatul.football_apps.model.DetailTeamsItems

interface GetAwayBadgeRepositoryCallback<T> {
    fun onBadgeAway(data: List<DetailTeamsItems>)
}