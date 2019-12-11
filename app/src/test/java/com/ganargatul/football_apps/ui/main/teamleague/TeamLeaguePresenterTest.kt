package com.ganargatul.football_apps.ui.main.teamleague

import com.ganargatul.football_apps.model.LeagueDetailsItem
import com.ganargatul.football_apps.model.LeagueResponse
import com.ganargatul.football_apps.model.TeamItems
import com.ganargatul.football_apps.model.TeamResponse
import com.ganargatul.football_apps.test.repository.detailleague.DetailLeagueRepositoryCallback
import com.ganargatul.football_apps.test.repository.teamleague.TeamLeagueRepository
import com.ganargatul.football_apps.test.repository.teamleague.TeamLeagueRepositoryCallback
import com.nhaarman.mockito_kotlin.argumentCaptor
import com.nhaarman.mockito_kotlin.eq
import com.nhaarman.mockito_kotlin.verify
import org.junit.Test

import org.junit.Assert.*
import org.junit.Before
import org.mockito.Mock
import org.mockito.Mockito
import org.mockito.MockitoAnnotations

class TeamLeaguePresenterTest {
    @Mock
    private lateinit var view: TeamLeagueView

    @Mock
    private lateinit var teamLeagueRepository: TeamLeagueRepository

    private lateinit var presenter: TeamLeaguePresenter


    @Before
    fun setUp() {
        MockitoAnnotations.initMocks(this)
        presenter = TeamLeaguePresenter(view,teamLeagueRepository)
    }
    @Test
    fun getStanding() {
        var teams: List<TeamItems> = mutableListOf()
        val league = "4328"
        presenter.getStanding(league)
        argumentCaptor<TeamLeagueRepositoryCallback<TeamResponse?>>().apply {

            verify(teamLeagueRepository).getTeam(eq(league), capture())
            firstValue.onDataLoaded(teams)
        }
        Mockito.verify(view).showLoading()
        Mockito.verify(view).showTeamList(teams)
        Mockito.verify(view).hideLoading()
    }
}