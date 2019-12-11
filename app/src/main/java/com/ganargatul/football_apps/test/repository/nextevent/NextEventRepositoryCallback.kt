package com.ganargatul.football_apps.test.repository.nextevent

import com.ganargatul.football_apps.model.NextEventsItems


interface NextEventRepositoryCallback<T> {
    fun onDataLoaded(data: List<NextEventsItems>)
    fun onDataError()
}