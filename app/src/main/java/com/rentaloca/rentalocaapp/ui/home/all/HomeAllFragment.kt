package com.rentaloca.rentalocaapp.ui.home.all

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.rentaloca.rentalocaapp.R
import com.rentaloca.rentalocaapp.databinding.FragmentHomeAllBinding
import com.rentaloca.rentalocaapp.model.dummy.HomeModel
import com.rentaloca.rentalocaapp.ui.home.SectionPagerAdapter
import com.rentaloca.rentalocaapp.ui.home.SpaceItemDecoration

class HomeAllFragment : Fragment(), HomeAllAdapter.ItemAdapterCallback {
    private lateinit var binding: FragmentHomeAllBinding
    private var bajuList : ArrayList<HomeModel> = ArrayList()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentHomeAllBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        initDataDummy()
        var adapter = HomeAllAdapter(bajuList, this)
        var layoutManager: RecyclerView.LayoutManager = GridLayoutManager(activity, 2)
        val spacingInPixels = 14
        val itemDecoration = SpaceItemDecoration(spacingInPixels)
        binding.rcListAll.addItemDecoration(itemDecoration)

        binding.rcListAll.layoutManager = layoutManager
        binding.rcListAll.adapter = adapter
    }

    private fun initDataDummy() {
        bajuList = ArrayList()
        bajuList.add(HomeModel("Baju mantapu", "", "Rp.100.000"))
        bajuList.add(HomeModel("Baju gakgerah", "", "Rp.200.000"))
        bajuList.add(HomeModel("Baju ternyaman", "", "Rp.900.000"))
        bajuList.add(HomeModel("Baju ternyaman", "", "Rp.900.000"))
        bajuList.add(HomeModel("Baju ternyaman", "", "Rp.900.000"))
        bajuList.add(HomeModel("Baju mantapu", "", "Rp.100.000"))
        bajuList.add(HomeModel("Baju gakgerah", "", "Rp.200.000"))
        bajuList.add(HomeModel("Baju ternyaman", "", "Rp.900.000"))
        bajuList.add(HomeModel("Baju ternyaman", "", "Rp.900.000"))
        bajuList.add(HomeModel("Baju ternyaman", "", "Rp.900.000"))

    }

    override fun onClick(v: View, data: HomeModel) {
        Toast.makeText(context, "test klik gaes", Toast.LENGTH_SHORT).show()
    }

}