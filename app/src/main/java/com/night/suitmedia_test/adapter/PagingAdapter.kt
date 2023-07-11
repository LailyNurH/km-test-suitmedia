package com.night.suitmedia_test.adapter

import android.content.Intent
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.findNavController
import androidx.paging.PagingDataAdapter
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.night.suitmedia_test.R
import com.night.suitmedia_test.databinding.ItemUserBinding
import com.night.suitmedia_test.network.model.Data

class PagingAdapter: PagingDataAdapter<Data, PagingAdapter.MyViewHolder>(DIFF_CALLBACK) {


    class MyViewHolder(private val binding: ItemUserBinding): RecyclerView.ViewHolder(binding.root) {
        fun bind(user: Data){
            binding.apply {
                tvFullName.text = "${user.firstName} ${user.lastName}"
                tvEmail.text = user.email

                Glide.with(itemView)
                    .load(user.avatar)
                    .into(ivProfile)
            }
        }
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        val user = getItem(position)
        if (user != null){
            holder.bind(user)
            val fullName = "${user.firstName} ${user.lastName}"
            Log.d("selected user",fullName.toString())
            holder.itemView.setOnClickListener{
                toSecondScreen(holder.itemView, fullName)
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        val binding = ItemUserBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return MyViewHolder(binding)
    }

    private fun toSecondScreen(itemView: View, name: String){
        itemView.setOnClickListener{
            SELECTED_NAME = name
            Log.d("selected user2",SELECTED_NAME.toString())

            val navController = itemView.findNavController()
            navController.navigate(R.id.action_thirdFragment_to_secondFragment)
        }
    }

    companion object{
        var SELECTED_NAME =""

        val DIFF_CALLBACK = object : DiffUtil.ItemCallback<Data>(){
            override fun areItemsTheSame(oldItem: Data, newItem: Data): Boolean {
                return oldItem == newItem
            }

            override fun areContentsTheSame(oldItem: Data, newItem: Data): Boolean {
                return oldItem.id == newItem.id
            }

        }
    }
}