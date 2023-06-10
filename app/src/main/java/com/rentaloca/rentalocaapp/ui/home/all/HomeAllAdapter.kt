package com.rentaloca.rentalocaapp.ui.home.all

import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.rentaloca.rentalocaapp.R
import com.rentaloca.rentalocaapp.model.DressModel
import com.rentaloca.rentalocaapp.ui.detail.DetailActivity

class HomeAllAdapter (
    private val listData : ArrayList<DressModel>,
//    private val itemAdapterCallback : ItemAdapterCallback,
) : RecyclerView.Adapter<HomeAllAdapter.ListViewHolder>(){
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ListViewHolder {
        val view: View = LayoutInflater.from(parent.context).inflate(R.layout.item_home_vertical, parent, false)
        return ListViewHolder(view)
    }

//    override fun onBindViewHolder(holder: ListViewHolder, position: Int) {
//        holder.bind(listData[position], itemAdapterCallback)
//    }
    override fun onBindViewHolder(holder: ListViewHolder, position: Int) {
        val (photo, title, price) = listData[position]
        holder.imgPhoto.setImageResource(photo)
        holder.tvTitle.text = title
        holder.tvPrice.text = price
        holder.itemView.setOnClickListener {
            val intentDetail = Intent(holder.itemView.context, DetailActivity::class.java)
            intentDetail.putExtra("key_dress", listData[holder.adapterPosition])
            holder.itemView.context.startActivity(intentDetail)
        }
    }

//    override fun getItemCount(): Int {
//        return listData.size
//    }
    override fun getItemCount(): Int = listData.size

//    class ListViewHolder(private val binding: ItemHomeVerticalBinding) : RecyclerView.ViewHolder(binding.root) {
//        fun bind(data: HomeModel, itemAdapterCallback: ItemAdapterCallback) {
//            binding.apply {
//                tvTitle.text = data.title
//                tvPrice.text = data.price
//
//                itemView.setOnClickListener { itemAdapterCallback.onClick(itemView, data) }
//            }
//        }
//    }

    class ListViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val imgPhoto: ImageView = itemView.findViewById(R.id.ivDummyBaju)
        val tvTitle: TextView = itemView.findViewById(R.id.tvTitle)
        val tvPrice: TextView = itemView.findViewById(R.id.tvPrice)
    }

//    interface ItemAdapterCallback {
//        fun onClick(v: View, data:HomeModel)
//    }
}