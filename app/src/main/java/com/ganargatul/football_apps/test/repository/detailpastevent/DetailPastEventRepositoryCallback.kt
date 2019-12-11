package com.ganargatul.football_apps.test.repository.detailpastevent

import com.ganargatul.football_apps.model.PastEventItems

interface DetailPastEventRepositoryCallback<T> {
    fun onDataLoaded(data: List<PastEventItems>)
    fun onDataError()
}