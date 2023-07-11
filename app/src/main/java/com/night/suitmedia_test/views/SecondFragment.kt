package com.night.suitmedia_test.views

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.findNavController
import com.night.suitmedia_test.R
import com.night.suitmedia_test.adapter.PagingAdapter
import com.night.suitmedia_test.adapter.PagingAdapter.Companion.SELECTED_NAME
import com.night.suitmedia_test.views.FirstFragment.Companion.NAME
import com.night.suitmedia_test.databinding.FragmentSecondBinding


class SecondFragment : Fragment() {
    private lateinit var binding: FragmentSecondBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        binding = FragmentSecondBinding.inflate(layoutInflater,container,false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        activity?.title = "Second Screen"
        with(binding) {
            if(SELECTED_NAME == ""){
                tvSelectedUser.text= "no user selected"
            }else{
                tvSelectedUser.text = SELECTED_NAME

            }
            tvName.text = NAME
            btnChooseUser.setOnClickListener {
                findNavController().navigate(R.id.action_secondFragment_to_thirdFragment)
            }
        }
    }


}