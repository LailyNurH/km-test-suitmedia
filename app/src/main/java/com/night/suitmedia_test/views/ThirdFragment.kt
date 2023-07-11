package com.night.suitmedia_test.views

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.activity.viewModels
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.LinearLayoutManager
import com.night.suitmedia_test.R
import com.night.suitmedia_test.adapter.LoadingState
import com.night.suitmedia_test.adapter.PagingAdapter
import com.night.suitmedia_test.adapter.PagingAdapter.Companion.SELECTED_NAME
import com.night.suitmedia_test.databinding.FragmentThirdBinding
import com.night.suitmedia_test.utils.ViewModelFactory
import com.night.suitmedia_test.viewmodel.ThirdFragmentViewModel
import dagger.hilt.android.AndroidEntryPoint



class ThirdFragment : Fragment() {
    private lateinit var binding : FragmentThirdBinding

    private val thirdViewModel: ThirdFragmentViewModel by viewModels {
        ViewModelFactory(requireContext())
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        binding = FragmentThirdBinding.inflate(layoutInflater,container,false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        activity?.title = "Third Screen"
        getUserData()


    }

    private fun getUserData() {
        val adapter = PagingAdapter()
        binding.recyclerView.adapter = adapter.withLoadStateFooter(
            footer = LoadingState()

        )
        binding.recyclerView.layoutManager = LinearLayoutManager(requireContext())
        thirdViewModel.listUser.observe(this){
            adapter.submitData(lifecycle, it)
        }
        binding.swipeContainer.setOnRefreshListener { adapter.refresh() }

    }

}