package com.rentaloca.rentalocaapp.ui.home.foryou

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

//class HomeForYouAdapter (
//    private val listData : List<HomeModel>,
//    private val itemAdapterCallback : ItemAdapterCallback,
//) : RecyclerView.Adapter<HomeForYouAdapter.ViewHolder>(){
//    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
//        val binding = ItemHomeVerticalBinding.inflate(LayoutInflater.from(parent.context), parent, false)
//        return ViewHolder(binding)
//    }
//
//    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
//        holder.bind(listData[position], itemAdapterCallback)
//    }
//
//    override fun getItemCount(): Int {
//        return listData.size
//    }
//
//    class ViewHolder(private val binding: ItemHomeVerticalBinding) : RecyclerView.ViewHolder(binding.root) {
//        fun bind(data: HomeModel, itemAdapterCallback: ItemAdapterCallback) {
//            binding.apply {
//                tvTitle.text = data.title
//                tvPrice.text = data.price
//
//                itemView.setOnClickListener { itemAdapterCallback.onClick(itemView, data) }
//            }
//        }
//    }
//
//    interface ItemAdapterCallback {
//        fun onClick(v: View, data:HomeModel)
//    }
//}

class HomeForYouAdapter (
    private val listDataForYou : ArrayList<DressModel>,
) : RecyclerView.Adapter<HomeForYouAdapter.ListViewHolder>(){
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ListViewHolder {
        val view: View = LayoutInflater.from(parent.context).inflate(R.layout.item_home_vertical, parent, false)
        return ListViewHolder(view)
    }

    override fun onBindViewHolder(holder: ListViewHolder, position: Int) {
        val (photo, title, price) = listDataForYou[position]
        holder.imgPhoto.setImageResource(photo)
        holder.tvTitle.text = title
        holder.tvPrice.text = price
        holder.itemView.setOnClickListener {
            val intentDetail = Intent(holder.itemView.context, DetailActivity::class.java)
            intentDetail.putExtra("key_dress", listDataForYou[holder.adapterPosition])
            holder.itemView.context.startActivity(intentDetail)
        }
    }

    override fun getItemCount(): Int = listDataForYou.size

    class ListViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val imgPhoto: ImageView = itemView.findViewById(R.id.ivDummyBaju)
        val tvTitle: TextView = itemView.findViewById(R.id.tvTitle)
        val tvPrice: TextView = itemView.findViewById(R.id.tvPrice)
    }
}