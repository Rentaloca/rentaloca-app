package com.rentaloca.rentalocaapp.ui.home.all

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.rentaloca.rentalocaapp.databinding.ItemHomeVerticalBinding
import com.rentaloca.rentalocaapp.model.dummy.HomeModel


class HomeAllAdapter (
    private val listData : List<HomeModel>,
    private val itemAdapterCallback : ItemAdapterCallback,
) : RecyclerView.Adapter<HomeAllAdapter.ViewHolder>(){
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val binding = ItemHomeVerticalBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(listData[position], itemAdapterCallback)
    }

    override fun getItemCount(): Int {
        return listData.size
    }

    class ViewHolder(private val binding: ItemHomeVerticalBinding) : RecyclerView.ViewHolder(binding.root) {
        fun bind(data: HomeModel, itemAdapterCallback: ItemAdapterCallback) {
            binding.apply {
                tvTitle.text = data.title
                tvPrice.text = data.price

//                Glide.with(itemView.context)
//                    .load(data.src)
//                    .into(ivDummyBaju)

                itemView.setOnClickListener { itemAdapterCallback.onClick(itemView, data) }
            }
        }
    }

    interface ItemAdapterCallback {
        fun onClick(v: View, data:HomeModel)
    }
}