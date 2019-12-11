package com.ganargatul.football_apps.ui.main.pastevent

import com.ganargatul.football_apps.model.PastEventItems
import com.ganargatul.football_apps.model.PastEventReponse
import com.ganargatul.football_apps.test.repository.pastevent.PastEventRepository
import com.ganargatul.football_apps.test.repository.pastevent.PastEventRepositoryCallback
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

class PastEventPresenterTest {

    @Mock
    private lateinit var view: PastEventView

    @Mock
    private lateinit var PastEventRepository: PastEventRepository

    private lateinit var presenter: PastEventPresenter

    @Before
    fun setUp() {
        MockitoAnnotations.initMocks(this)
        presenter = PastEventPresenter(view, PastEventRepository)
    }
    @Test
    fun loaddata() {
        var teams: List<PastEventItems> = mutableListOf()
        val league = "4328"
        presenter.loaddata(league)
        argumentCaptor<PastEventRepositoryCallback<PastEventReponse?>>().apply {

            verify(PastEventRepository).getNextMatch(eq(league), capture())
            firstValue.onDataLoaded(teams)
        }
        Mockito.verify(view).showLoading()
        Mockito.verify(view).showTeamList(teams)
        Mockito.verify(view).hideLoading()
    }
}