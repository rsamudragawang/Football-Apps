package com.ganargatul.football_apps.ui.main.detailnextevent

import com.ganargatul.football_apps.model.DetailTeamsItems
import com.ganargatul.football_apps.model.DetailTeamsResponse
import com.ganargatul.football_apps.test.repository.getAwayBadge.GetAwayBadgeRepositoryCallback

interface GetAwayBadgeView  : GetAwayBadgeRepositoryCallback<DetailTeamsResponse> {
    fun show(data: List<DetailTeamsItems>)
}