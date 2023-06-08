package com.rentaloca.rentalocaapp.ui.home.foryou

import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.rentaloca.rentalocaapp.R
import com.rentaloca.rentalocaapp.databinding.FragmentHomeAllBinding
import com.rentaloca.rentalocaapp.databinding.FragmentHomeForYouBinding
import com.rentaloca.rentalocaapp.model.dummy.HomeModel
import com.rentaloca.rentalocaapp.ui.detail.DetailActivity
import com.rentaloca.rentalocaapp.ui.home.SpaceItemDecoration
import com.rentaloca.rentalocaapp.ui.home.all.HomeAllAdapter

class HomeForYouFragment : Fragment(), HomeForYouAdapter.ItemAdapterCallback {
    private lateinit var binding: FragmentHomeForYouBinding
    private var bajuListForyou : ArrayList<HomeModel> = ArrayList()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentHomeForYouBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        initDataDummy()
        var adapter = HomeForYouAdapter(bajuListForyou, this)
        var layoutManager: RecyclerView.LayoutManager = GridLayoutManager(activity, 2)
        val spacingInPixels = 14
        val itemDecoration = SpaceItemDecoration(spacingInPixels)
        binding.rcListForYou.addItemDecoration(itemDecoration)

        binding.rcListForYou.layoutManager = layoutManager
        binding.rcListForYou.adapter = adapter
    }

    private fun initDataDummy() {
        bajuListForyou = ArrayList()
        bajuListForyou.add(HomeModel("Baju foryouyak", "", "Rp.100.000"))
        bajuListForyou.add(HomeModel("Baju foryouyak", "", "Rp.200.000"))
        bajuListForyou.add(HomeModel("Baju foryouyak", "", "Rp.900.000"))
        bajuListForyou.add(HomeModel("Baju foryouyak", "", "Rp.900.000"))
        bajuListForyou.add(HomeModel("Baju foryouyak", "", "Rp.900.000"))
        bajuListForyou.add(HomeModel("Baju foryouyak", "", "Rp.100.000"))
        bajuListForyou.add(HomeModel("Baju foryouyak", "", "Rp.200.000"))
        bajuListForyou.add(HomeModel("Baju foryouyak", "", "Rp.900.000"))
        bajuListForyou.add(HomeModel("Baju foryouyak", "", "Rp.900.000"))
        bajuListForyou.add(HomeModel("Baju foryouyak", "", "Rp.900.000"))

    }

    override fun onClick(v: View, data: HomeModel) {
        startActivity(Intent(activity, DetailActivity::class.java))
    }

}