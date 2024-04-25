package com.ahmadshubita.moviesapp.data.paging

import com.ahmadshubita.moviesapp.data.models.Tv
import com.ahmadshubita.moviesapp.data.remote.core.MainServices
import com.ahmadshubita.moviesapp.data.remote.utils.wrapApiCall
import javax.inject.Inject

class TvPagingSource @Inject constructor(
        mainServices: MainServices,
        private val language: String,
) : BasePagingSource<Tv>(mainServices) {
    override suspend fun loadData(offset: Int): List<Tv> {
        return wrapApiCall {
            mainServices.getTvTopRated(
                    language = language,
                    page = offset
            )
        }.results
    }

    override suspend fun getTotalPages(offset: Int): Int {
        return wrapApiCall {
            mainServices.getTvTopRated(
                    language = language,
                    page = offset
            )
        }.totalPages
    }
}
