package com.ahmadshubita.moviesapp.data.paging

import androidx.paging.PagingSource
import androidx.paging.PagingState
import com.ahmadshubita.moviesapp.data.remote.core.MainServices


abstract class BasePagingSource<value : Any>(
    protected val mainServices: MainServices
) : PagingSource<Int, value>() {
    protected abstract suspend fun loadData(page: Int): List<value>?
    protected abstract suspend fun getTotalPages(page: Int): Int?
    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, value> {
        return try {
            val page = params.key ?: STARTING_PAGE_INDEX
            val data = loadData(page) ?: emptyList()
            val totalPages = getTotalPages(page) ?: STARTING_PAGE_INDEX
            LoadResult.Page(
                data = data,
                prevKey = if (page == 1) null else page - 1,
                nextKey = if (page > totalPages) null else page + 1,
            )
        } catch (e: Exception) {
            LoadResult.Error(e)
        }
    }

    override fun getRefreshKey(state: PagingState<Int, value>): Int? {
        return null
    }

    companion object {
        private const val STARTING_PAGE_INDEX = 1
    }
}

