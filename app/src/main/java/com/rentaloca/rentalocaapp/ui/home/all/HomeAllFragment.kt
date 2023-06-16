package com.rentaloca.rentalocaapp.ui.home.all

import android.annotation.SuppressLint
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.rentaloca.rentalocaapp.R
import com.rentaloca.rentalocaapp.databinding.FragmentHomeAllBinding
import com.rentaloca.rentalocaapp.model.DressModel
import com.rentaloca.rentalocaapp.ui.home.SpaceItemDecoration

class HomeAllFragment : Fragment() {
    private lateinit var rvDressModel: RecyclerView
    private lateinit var binding: FragmentHomeAllBinding
    private val bajuList = ArrayList<DressModel>()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentHomeAllBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        rvDressModel = binding.rcListAll
        val spacingInPixels = 14
        val itemDecoration = SpaceItemDecoration(spacingInPixels)
        binding.rcListAll.addItemDecoration(itemDecoration)

        showRecyclerList()
    }

    @SuppressLint("Recycle")
    private fun getListDress(): ArrayList<DressModel> {
        val dataPhoto = resources.obtainTypedArray(R.array.data_photo)
        val dataDressname = resources.getStringArray(R.array.data_dressname)
        val dataPrice = resources.getStringArray(R.array.data_price)
        val dataDays = resources.getStringArray(R.array.data_days)
        val dataDescription = resources.getStringArray(R.array.data_description)
        val dataBodyshape = resources.getStringArray(R.array.data_bodyshape)
        val listDress = ArrayList<DressModel>()
        for (i in dataDressname.indices) {
            val dress = DressModel(dataPhoto.getResourceId(i, -1), dataDressname[i], dataPrice[i], dataDays[i], dataDescription[i], dataBodyshape[i])
            listDress.add(dress)
        }
        return listDress
    }

    private fun showRecyclerList() {
        bajuList.clear()
        bajuList.addAll(getListDress())
        rvDressModel.layoutManager = GridLayoutManager(activity, 2)
        val listTechCompanyAdapter = HomeAllAdapter(bajuList)
        rvDressModel.adapter = listTechCompanyAdapter
    }

}