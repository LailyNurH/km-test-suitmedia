package com.night.suitmedia_test.views

import android.os.Bundle
import android.os.CountDownTimer
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.LinearLayout.HORIZONTAL
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.night.suitmedia_test.R
import com.night.suitmedia_test.adapter.PagingAdapter.Companion.SELECTED_NAME
import com.night.suitmedia_test.adapter.ProductAdapter
import com.night.suitmedia_test.views.FirstFragment.Companion.NAME
import com.night.suitmedia_test.databinding.FragmentSecondBinding
import com.night.suitmedia_test.network.model.Produk


class SecondFragment : Fragment() {
    private lateinit var binding: FragmentSecondBinding
    private lateinit var produkAdapter: ProductAdapter

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
        setupRv()
        countdownText()
    }

    private fun countdownText() {
        val countdownDurationMillis = 5000
        val countdownIntervalMillis = 100
        object : CountDownTimer(countdownDurationMillis.toLong(), countdownIntervalMillis.toLong()) {
            override fun onTick(millisUntilFinished: Long) {
                val secondsRemaining = (millisUntilFinished / 1000).toInt()
                binding.tvCountfoen.text = secondsRemaining.toString()
            }

            override fun onFinish() {
                binding.rvProduct.visibility = View.GONE
            }
        }.start()
    }


    private fun setupRv() {
        val layoutManager = LinearLayoutManager(requireContext(), LinearLayoutManager.HORIZONTAL, false)
        binding.rvProduct.layoutManager = layoutManager
        produkAdapter = ProductAdapter(productList)
        binding.rvProduct.adapter = produkAdapter


    }

    private val productList = listOf(
        Produk("Produk 1", R.drawable.product1),
        Produk("Produk 2", R.drawable.product2)
        )
}