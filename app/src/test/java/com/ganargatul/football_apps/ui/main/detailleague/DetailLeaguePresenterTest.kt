package com.ganargatul.football_apps.ui.main.detailleague

import com.ganargatul.football_apps.model.LeagueDetailsItem
import com.ganargatul.football_apps.model.LeagueResponse
import com.ganargatul.football_apps.test.repository.detailleague.DetailLeagueRepository
import com.ganargatul.football_apps.test.repository.detailleague.DetailLeagueRepositoryCallback
import com.nhaarman.mockito_kotlin.argumentCaptor
import com.nhaarman.mockito_kotlin.eq
import com.nhaarman.mockito_kotlin.verify
import org.junit.Test

import org.junit.Assert.*
import org.junit.Before
import org.mockito.Mock
import org.mockito.Mockito
import org.mockito.MockitoAnnotations

class DetailLeaguePresenterTest {

    @Mock
    private lateinit var view: DetailLeagueView

    @Mock
    private lateinit var detailLeagueRepository: DetailLeagueRepository

    private lateinit var presenter: DetailLeaguePresenter

    @Before
    fun setUp() {
        MockitoAnnotations.initMocks(this)
        presenter = DetailLeaguePresenter(view,detailLeagueRepository)
    }
    @Test
    fun loadData() {
        var teams: List<LeagueDetailsItem> = mutableListOf()
        val league = "4328"
        presenter.loadData(league)
        argumentCaptor<DetailLeagueRepositoryCallback<LeagueResponse?>>().apply {

            verify(detailLeagueRepository).getNextMatch(eq(league), capture())
            firstValue.onDataLoaded(teams)
        }
        Mockito.verify(view).showLoading()
        Mockito.verify(view).showTeamList(teams)
        Mockito.verify(view).hideLoading()
    }
}