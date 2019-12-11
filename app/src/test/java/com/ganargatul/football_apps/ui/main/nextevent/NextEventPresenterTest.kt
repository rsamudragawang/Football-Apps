package com.ganargatul.football_apps.ui.main.nextevent

import com.ganargatul.football_apps.model.NextEventResponse
import com.ganargatul.football_apps.model.NextEventsItems
import com.ganargatul.football_apps.test.repository.nextevent.NextEventRepository
import com.ganargatul.football_apps.test.repository.nextevent.NextEventRepositoryCallback
import com.nhaarman.mockito_kotlin.argumentCaptor
import com.nhaarman.mockito_kotlin.eq
import com.nhaarman.mockito_kotlin.verify
import org.junit.Test

import org.junit.Assert.*
import org.junit.Before
import org.mockito.Mock
import org.mockito.Mockito
import org.mockito.MockitoAnnotations

class NextEventPresenterTest {
    @Mock
    private lateinit var view: NextEventView

    @Mock
    private lateinit var NextEventRepository: NextEventRepository

    private lateinit var presenter: NextEventPresenter

    @Before
    fun setUp() {
        MockitoAnnotations.initMocks(this)
        presenter = NextEventPresenter(view,NextEventRepository)
    }

    @Test
    fun loadData() {
        var teams: List<NextEventsItems> = mutableListOf()
        val league = "4328"
        presenter.loadData(league)
        argumentCaptor<NextEventRepositoryCallback<NextEventResponse?>>().apply {

            verify(NextEventRepository).getNextMatch(eq(league), capture())
            firstValue.onDataLoaded(teams)
        }
        Mockito.verify(view).showLoading()
        Mockito.verify(view).showTeamList(teams)
        Mockito.verify(view).hideLoading()
    }
}