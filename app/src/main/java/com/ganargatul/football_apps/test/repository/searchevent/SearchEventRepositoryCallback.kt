package com.ganargatul.football_apps.test.repository.searchevent

import com.ganargatul.football_apps.model.PastEventItems


interface SearchEventRepositoryCallback<T> {
    fun onDataLoaded(data: List<PastEventItems>)
    fun onDataError()
}