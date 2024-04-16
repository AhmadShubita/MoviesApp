package com.ahmadshubita.moviesapp.data.paging

import com.ahmadshubita.moviesapp.data.models.People
import com.ahmadshubita.moviesapp.data.remote.core.MainServices
import com.ahmadshubita.moviesapp.data.remote.utils.wrapApiCall
import javax.inject.Inject

class PeoplePagingSource @Inject constructor(
        mainServices: MainServices,
        private val language: String,
) : BasePagingSource<People>(mainServices) {
    override suspend fun loadData(page: Int): List<People> {
        return wrapApiCall {
            mainServices.getPeople(
                    language = language,
                    page = page
            )
        }.results
    }

    override suspend fun getTotalPages(offset: Int): Int {
        return wrapApiCall {
            mainServices.getPeople(
                    language = language,
                    page = offset
            )
        }.totalResults
    }
}
