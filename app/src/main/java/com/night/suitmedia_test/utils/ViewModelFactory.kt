package com.night.suitmedia_test.utils

import android.content.Context
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.night.suitmedia_test.di.Injection
import com.night.suitmedia_test.viewmodel.ThirdFragmentViewModel

class ViewModelFactory(private val context: Context): ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(ThirdFragmentViewModel::class.java)){
            @Suppress("UNCHECKED_CAST")
            return ThirdFragmentViewModel(Injection.provideRepository(context)) as T
        }
        throw IllegalArgumentException("Unknow ViewModel Class")
    }
}