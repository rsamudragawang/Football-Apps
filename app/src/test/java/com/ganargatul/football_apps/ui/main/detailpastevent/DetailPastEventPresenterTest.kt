package com.ganargatul.football_apps.ui.main.detailpastevent

import com.ganargatul.football_apps.model.PastEventItems
import com.ganargatul.football_apps.model.PastEventReponse
import com.ganargatul.football_apps.test.repository.detailpastevent.DetailPastEventRepository
import com.ganargatul.football_apps.test.repository.detailpastevent.DetailPastEventRepositoryCallback
import com.nhaarman.mockito_kotlin.argumentCaptor
import com.nhaarman.mockito_kotlin.eq
import com.nhaarman.mockito_kotlin.verify
import org.junit.Test

import org.junit.Assert.*
import org.junit.Before
import org.mockito.Mock
import org.mockito.Mockito
import org.mockito.MockitoAnnotations

class DetailPastEventPresenterTest {

    @Mock
    private lateinit var view: DetailPastEventView

    @Mock
    private lateinit var detailPastEventRepository: DetailPastEventRepository

    private lateinit var presenter: DetailPastEventPresenter

    @Before
    fun setUp() {
        MockitoAnnotations.initMocks(this)
        presenter = DetailPastEventPresenter(view,detailPastEventRepository)
    }
    @Test
    fun loaddata() {
        var teams: List<PastEventItems> = mutableListOf()
        val league = "602277"
        presenter.loaddata(league)
        argumentCaptor<DetailPastEventRepositoryCallback<PastEventReponse?>>().apply {

            verify(detailPastEventRepository).getNextMatch(eq(league), capture())
            firstValue.onDataLoaded(teams)
        }
        Mockito.verify(view).showLoading()
        Mockito.verify(view).showTeamList(teams)
        Mockito.verify(view).hideLoading()
    }
}