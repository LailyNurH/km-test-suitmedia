package com.night.suitmedia_test.network

import androidx.paging.*
import com.night.suitmedia_test.network.model.Data
import com.night.suitmedia_test.utils.Constants.PAGE_INDEX

class UserPagingSource(private val apiService: ApiService) : PagingSource<Int, Data>() {
    override fun getRefreshKey(state: PagingState<Int, Data>): Int? {
        return state.anchorPosition?.let { anchorPosition ->
            val anchorPage = state.closestPageToPosition(anchorPosition)
            anchorPage?.prevKey?.plus(1) ?: anchorPage?.nextKey?.minus(1)
        }
    }

    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, Data> {
        return try {
            val position = params.key ?: PAGE_INDEX
            val responseData = apiService.getUsers(position)
            val listUsers = responseData.data

            LoadResult.Page(
                data = listUsers,
                prevKey = if (position == PAGE_INDEX) null else position - 1,
                nextKey = if (listUsers.isEmpty()) null else position + 1
            )
        } catch (exception: Exception) {
            return LoadResult.Error(exception)
        }
    }
}