package com.rentaloca.rentalocaapp.ui.profile

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.RecyclerView
import com.rentaloca.rentalocaapp.R
import com.rentaloca.rentalocaapp.databinding.ItemProfileBinding
import com.rentaloca.rentalocaapp.model.dummy.ProfileModel

class ProfileAdapter (
    private val listData : List<ProfileModel>,
    private val itemAdapterCallback : ItemAdapterCallback,
) : RecyclerView.Adapter<ProfileAdapter.ViewHolder>(){
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val binding = ItemProfileBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(listData[position], itemAdapterCallback)
    }

    override fun getItemCount(): Int {
        return listData.size
    }

    class ViewHolder(private val binding: ItemProfileBinding) : RecyclerView.ViewHolder(binding.root) {
        fun bind(data: ProfileModel, itemAdapterCallback: ItemAdapterCallback) {
            binding.apply {
                if (data.title == "Logout") {
                    tvTitle.setTextColor(ContextCompat.getColor(itemView.context, R.color.red))
                    ivIcon.setImageResource(R.drawable.ic_arrow_right_red)
                }
                tvTitle.text = data.title

                itemView.setOnClickListener { itemAdapterCallback.onClick(itemView, data) }
            }
        }
    }

    interface ItemAdapterCallback {
        fun onClick(v: View, data:ProfileModel)
    }
}