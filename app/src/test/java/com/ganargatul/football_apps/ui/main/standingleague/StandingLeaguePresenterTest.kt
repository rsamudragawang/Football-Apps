package com.ganargatul.football_apps.ui.main.standingleague

import com.ganargatul.football_apps.model.LeagueDetailsItem
import com.ganargatul.football_apps.model.LeagueResponse
import com.ganargatul.football_apps.model.StandingItems
import com.ganargatul.football_apps.model.StandingResponse
import com.ganargatul.football_apps.test.repository.detailleague.DetailLeagueRepositoryCallback
import com.ganargatul.football_apps.test.repository.standingleague.StandingLeagueRepository
import com.ganargatul.football_apps.test.repository.standingleague.StandingLeagueRepositoryCallback
import com.nhaarman.mockito_kotlin.argumentCaptor
import com.nhaarman.mockito_kotlin.eq
import com.nhaarman.mockito_kotlin.verify
import org.junit.Test

import org.junit.Assert.*
import org.junit.Before
import org.mockito.Mock
import org.mockito.Mockito
import org.mockito.MockitoAnnotations

class StandingLeaguePresenterTest {
    @Mock
    private lateinit var view: StandingLeagueView

    @Mock
    private lateinit var standingLeagueRepository: StandingLeagueRepository

    private lateinit var presenter: StandingLeaguePresenter
    @Before
    fun setUp() {
        MockitoAnnotations.initMocks(this)
        presenter = StandingLeaguePresenter(view,standingLeagueRepository)
    }
    @Test
    fun getStanding() {
        var teams: List<StandingItems> = mutableListOf()
        val league = "4328"
        presenter.getStanding(league)
        argumentCaptor<StandingLeagueRepositoryCallback<StandingResponse?>>().apply {

            verify(standingLeagueRepository).standingLeague(eq(league), capture())
            firstValue.onDataLoaded(teams)
        }
        Mockito.verify(view).showLoading()
        Mockito.verify(view).showTeamList(teams)
        Mockito.verify(view).hideLoading()
    }
}