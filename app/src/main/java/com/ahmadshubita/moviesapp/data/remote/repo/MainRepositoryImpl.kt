package com.ahmadshubita.moviesapp.data.remote.repo

import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import com.ahmadshubita.moviesapp.data.models.Movie
import com.ahmadshubita.moviesapp.data.models.MoviesResponse
import com.ahmadshubita.moviesapp.data.models.People
import com.ahmadshubita.moviesapp.data.models.Tv
import com.ahmadshubita.moviesapp.data.paging.AllItemsPagingSource
import com.ahmadshubita.moviesapp.data.paging.PeoplePagingSource
import com.ahmadshubita.moviesapp.data.paging.TvPagingSource
import com.ahmadshubita.moviesapp.data.remote.core.MainServices
import com.ahmadshubita.moviesapp.data.remote.utils.wrapApiCall
import com.ahmadshubita.moviesapp.ui.movies.details.model.DetailsItem
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
            mainServices.getUpcomingMovies(language, page)
        }
    }

    override suspend fun getPopularMovies(language: String, page: Int): MoviesResponse {
        return wrapApiCall {
            mainServices.getPopularMovies(language, page)
        }
    }

    override suspend fun getNowPlayingMoviesPaging(
        language: String, page: Int
    ): Flow<PagingData<Movie>> {
        return Pager(config = PagingConfig(pageSize = PAGE_SIZE),
            pagingSourceFactory = { AllItemsPagingSource(mainServices, language, false) }).flow
    }

    override suspend fun getPopularMoviesPaging(
        language: String, page: Int
    ): Flow<PagingData<Movie>> {
        return Pager(config = PagingConfig(pageSize = PAGE_SIZE),
            pagingSourceFactory = { AllItemsPagingSource(mainServices, language, true) }).flow
    }

    override suspend fun getTvTopRated(language: String, page: Int): Flow<PagingData<Tv>> {
        return Pager(config = PagingConfig(pageSize = PAGE_SIZE),
            pagingSourceFactory = { TvPagingSource(mainServices, language) }).flow
    }

    override suspend fun getPeople(language: String, page: Int): Flow<PagingData<People>> {
        return Pager(config = PagingConfig(pageSize = PAGE_SIZE),
            pagingSourceFactory = { PeoplePagingSource(mainServices, language) }).flow
    }

    override suspend fun getMovieById(movieId: Int): DetailsItem {
        return DetailsItem.from(wrapApiCall {
            mainServices.getMovieById(movieId)
        })
    }

    override suspend fun getTvSeriesById(tvId: Int): DetailsItem {
        return DetailsItem.from(wrapApiCall {
            mainServices.getTvById(tvId)
        })
    }

    companion object {
        const val PAGE_SIZE = 20
    }
}