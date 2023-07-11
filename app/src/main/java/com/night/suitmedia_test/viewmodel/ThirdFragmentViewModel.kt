package com.night.suitmedia_test.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.PagingData
import androidx.paging.cachedIn
import com.night.suitmedia_test.network.UserRepository
import com.night.suitmedia_test.network.model.Data
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

class ThirdFragmentViewModel(private val userRepository: UserRepository): ViewModel() {
    val listUser: LiveData<PagingData<Data>> =
        userRepository.getUsers().cachedIn(viewModelScope)
}
