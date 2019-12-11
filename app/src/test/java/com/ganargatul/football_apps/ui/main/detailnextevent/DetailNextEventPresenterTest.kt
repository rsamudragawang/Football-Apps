package com.ganargatul.football_apps.ui.main.detailnextevent

import com.ganargatul.football_apps.model.DetailTeamsItems
import com.ganargatul.football_apps.model.DetailTeamsResponse
import com.ganargatul.football_apps.test.repository.detailnextevent.DetailNextEventRepository
import com.ganargatul.football_apps.test.repository.detailnextevent.DetailNextEventRepositoryCallback
import com.nhaarman.mockito_kotlin.argumentCaptor
import com.nhaarman.mockito_kotlin.capture
import com.nhaarman.mockito_kotlin.eq
import com.nhaarman.mockito_kotlin.verify
import org.junit.Test

import org.junit.Assert.*
import org.junit.Before
import org.mockito.Mock
import org.mockito.Mockito
import org.mockito.MockitoAnnotations

class DetailNextEventPresenterTest {

    @Mock
    private lateinit var view: DetailNextEventView

    @Mock
    private lateinit var detailNextEventRepository: DetailNextEventRepository

    private lateinit var presenter: DetailNextEventPresenter
    @Before
    fun setUp() {
        MockitoAnnotations.initMocks(this)
        presenter = DetailNextEventPresenter(view,detailNextEventRepository)
    }
    @Test
    fun loaddata() {
        var teams: List<DetailTeamsItems> = mutableListOf()
        val league = "602277"
        presenter.loaddata(league)
        argumentCaptor<DetailNextEventRepositoryCallback<DetailTeamsResponse?>>().apply {

            verify(detailNextEventRepository).getNextMatch(eq(league), capture())
            firstValue.onDataLoaded(teams)
        }
        Mockito.verify(view).showLoading()
        Mockito.verify(view).showTeamList(teams)
        Mockito.verify(view).hideLoading()
    }
}