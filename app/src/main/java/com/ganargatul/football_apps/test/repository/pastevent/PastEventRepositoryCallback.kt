package com.ganargatul.football_apps.test.repository.pastevent

import com.ganargatul.football_apps.model.PastEventItems
interface PastEventRepositoryCallback <T> {
    fun onDataLoaded(data: List<PastEventItems>)
    fun onDataError()
}