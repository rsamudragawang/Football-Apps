package com.ganargatul.football_apps.ui.main.searchteam

import com.ganargatul.football_apps.model.PastEventItems
import com.ganargatul.football_apps.model.SearchEventResponse
import com.ganargatul.football_apps.model.TeamItems
import com.ganargatul.football_apps.model.TeamResponse
import com.ganargatul.football_apps.test.repository.searchevent.SearchEventRepositoryCallback
import com.ganargatul.football_apps.test.repository.searchteam.SearchTeamRepository
import com.ganargatul.football_apps.test.repository.searchteam.SearchTeamRepositoryCallback
import com.nhaarman.mockito_kotlin.argumentCaptor
import com.nhaarman.mockito_kotlin.eq
import com.nhaarman.mockito_kotlin.verify
import org.junit.Test

import org.junit.Assert.*
import org.junit.Before
import org.mockito.Mock
import org.mockito.Mockito
import org.mockito.MockitoAnnotations

class SearchTeamPresenterTest {
    @Mock
    private lateinit var view: SearchTeamView

    @Mock
    private lateinit var searchTeamRepository: SearchTeamRepository

    private lateinit var presenter: SearchTeamPresenter
    @Before
    fun setUp() {
        MockitoAnnotations.initMocks(this)
        presenter = SearchTeamPresenter(view,searchTeamRepository)
    }
    @Test
    fun loadData() {
        var teams: List<TeamItems> = mutableListOf()
        val league = "Arsenal"
        presenter.loadData(league)
        argumentCaptor<SearchTeamRepositoryCallback<TeamResponse?>>().apply {

            verify(searchTeamRepository).getNextMatch(eq(league), capture())
            firstValue.onDataLoaded(teams)
        }
        Mockito.verify(view).showLoading()
        Mockito.verify(view).showTeamList(teams)
        Mockito.verify(view).hideLoading()
    }
}