package com.night.suitmedia_test.network

import androidx.lifecycle.LiveData
import androidx.paging.*
import com.night.suitmedia_test.network.model.Data
import javax.inject.Inject

class UserRepository @Inject constructor(
    private val api: ApiService
) {

    fun getUsers(): LiveData<PagingData<Data>> {
        return Pager (
            config = PagingConfig(
                pageSize = 2
            ),
            pagingSourceFactory = {
                UserPagingSource(api)
            }
        ).liveData
    }
}