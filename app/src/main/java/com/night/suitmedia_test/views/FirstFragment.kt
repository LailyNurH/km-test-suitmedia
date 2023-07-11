package com.night.suitmedia_test.views

import android.app.AlertDialog
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.findNavController
import com.bumptech.glide.Glide
import com.night.suitmedia_test.R
import com.night.suitmedia_test.databinding.FragmentFirstBinding
import com.night.suitmedia_test.databinding.ResultDialogBinding
import dagger.hilt.android.AndroidEntryPoint


@AndroidEntryPoint
class FirstFragment : Fragment() {
private lateinit var binding: FragmentFirstBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        binding = FragmentFirstBinding.inflate(layoutInflater,container,false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        onClickListener()
    }

    private fun onClickListener() {
        with(binding) {
            btnCheck.setOnClickListener {
                val textPolindrome = etPolindrome.text.toString()
                if(etPolindrome.text.isNotEmpty()){
                    val result = checkPolindrome(textPolindrome)
                    showDialog(textPolindrome,result)
                }else{
                    etPolindrome.error = "field is required!!"
                }
            }
            btnNext.setOnClickListener {
                val nameUser = etName.text.toString()
                NAME = nameUser
                if(etName.text.isNotEmpty()){
                    findNavController().navigate(R.id.action_firstFragment_to_secondFragment)
                }else{
                    etName.error = "field is required"
                }

            }

        }
    }

    private fun showDialog(textPolindrome: String,isTrue:Boolean) {

        val resultDialog =
            ResultDialogBinding.inflate(LayoutInflater.from(requireContext()))
        val successChangeDialogBuilder =
            AlertDialog.Builder(requireContext(), R.style.RoundedCornerDialog)
                .setView(resultDialog.root)
        successChangeDialogBuilder.setCancelable(true)

        val showSuccessDialog = successChangeDialogBuilder.show()

        resultDialog.tvTextResult.text = textPolindrome
        if (isTrue){
            resultDialog.tvPolindrome.text = "Is Polindrome"
            Glide.with(requireContext())
                .asGif()
                .load(R.raw.succes)
                .into(resultDialog.ivIcon)
        } else {
            resultDialog.tvPolindrome.text = "Is Not Polindrome"
            Glide.with(requireContext())
                .asGif()
                .load(R.raw.failed)
                .into(resultDialog.ivIcon)
        }

        Handler(Looper.myLooper()!!).postDelayed({
            showSuccessDialog.dismiss()
        }, 5000)
    }

    private fun checkPolindrome(textPolindrome: String): Boolean
    {
        val text = textPolindrome.replace(" ","")
        val result = text.reversed()

        return text == result

    }
    companion object{
        var NAME: String? = ""

    }
}