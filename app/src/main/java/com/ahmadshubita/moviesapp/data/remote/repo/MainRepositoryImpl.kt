package com.ahmadshubita.moviesapp.data.remote.repo

import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import com.ahmadshubita.moviesapp.data.models.MoviesResponse
import com.ahmadshubita.moviesapp.data.models.PeopleResponse
import com.ahmadshubita.moviesapp.data.models.Tv
import com.ahmadshubita.moviesapp.data.models.TvResponse
import com.ahmadshubita.moviesapp.data.paging.TvPagingSource
import com.ahmadshubita.moviesapp.data.remote.core.MainServices
import com.ahmadshubita.moviesapp.data.remote.utils.wrapApiCall
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class MainRepositoryImpl @Inject constructor(
    private val mainServices: MainServices
) : MainRepository {
    override suspend fun getTopRatedMovies(language: String, page: Int): MoviesResponse {
        return wrapApiCall {
            mainServices.getTopRatedMovies(language, page)
        }
    }

    override suspend fun getNowPlayingMovies(language: String, page: Int): MoviesResponse {
        return wrapApiCall {
            mainServices.getNowPlayingMovies(language, page)
        }
    }

    override suspend fun getPopularMovies(language: String, page: Int): MoviesResponse {
        return wrapApiCall {
            mainServices.getPopularMovies(language, page)
        }
    }

    override suspend fun getTvTopRated(language: String, page: Int): Flow<PagingData<Tv>> {
        return Pager(
                config = PagingConfig(pageSize = PAGE_SIZE),
                pagingSourceFactory = { TvPagingSource(mainServices, language) }
        ).flow
    }

    override suspend fun getPeople(language: String, page: Int): PeopleResponse {
        return wrapApiCall {
            mainServices.getPeople(language, page)
        }
    }


    companion object {
        const val PAGE_SIZE = 20

    }

}