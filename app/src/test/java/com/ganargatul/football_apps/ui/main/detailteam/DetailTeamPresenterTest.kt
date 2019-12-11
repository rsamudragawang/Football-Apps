package com.ganargatul.football_apps.ui.main.detailteam

import com.ganargatul.football_apps.model.*
import com.ganargatul.football_apps.test.repository.detailleague.DetailLeagueRepositoryCallback
import com.ganargatul.football_apps.test.repository.detailteam.DetailTeamRepository
import com.ganargatul.football_apps.test.repository.detailteam.DetailTeamRepositoryCallback
import com.nhaarman.mockito_kotlin.argumentCaptor
import com.nhaarman.mockito_kotlin.eq
import com.nhaarman.mockito_kotlin.verify
import org.junit.Test

import org.junit.Assert.*
import org.junit.Before
import org.mockito.Mock
import org.mockito.Mockito
import org.mockito.MockitoAnnotations

class DetailTeamPresenterTest {
    @Mock
    private lateinit var view: DetailTeamView

    @Mock
    private lateinit var detailTeamRepository: DetailTeamRepository

    private lateinit var presenter: DetailTeamPresenter

    @Before
    fun setUp() {
        MockitoAnnotations.initMocks(this)
        presenter = DetailTeamPresenter(view,detailTeamRepository)
    }
    @Test
    fun loaddata() {
        var teams: List<DetailTeamsItems> = mutableListOf()
        val league = "133604"
        presenter.loaddata(league)
        argumentCaptor<DetailTeamRepositoryCallback<DetailTeamsResponse?>>().apply {

            verify(detailTeamRepository).getNextMatch(eq(league), capture())
            firstValue.onDataLoaded(teams)
        }
        Mockito.verify(view).showLoading()
        Mockito.verify(view).showTeamList(teams)
        Mockito.verify(view).hideLoading()
    }
}