package com.rentaloca.rentalocaapp.ui.profile

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.rentaloca.rentalocaapp.databinding.FragmentProfileBinding
import com.rentaloca.rentalocaapp.model.dummy.HomeModel
import com.rentaloca.rentalocaapp.model.dummy.ProfileModel
import com.rentaloca.rentalocaapp.ui.home.SpaceItemDecoration

class ProfileFragment : Fragment(),ProfileAdapter.ItemAdapterCallback {
    private lateinit var binding: FragmentProfileBinding
    private var opsiProfileList : ArrayList<ProfileModel> = ArrayList()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentProfileBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        initDataDummy()
        var adapter = ProfileAdapter(opsiProfileList, this)
        var layoutManager: RecyclerView.LayoutManager = LinearLayoutManager(activity)
        val spacingInPixels = 9
        val itemDecoration = SpaceItemDecoration(spacingInPixels)
        binding.rcProfile.addItemDecoration(itemDecoration)

        binding.rcProfile.layoutManager = layoutManager
        binding.rcProfile.adapter = adapter
    }

    private fun initDataDummy() {
        opsiProfileList = ArrayList()
        opsiProfileList.add(ProfileModel("Edit Profil"))
        opsiProfileList.add(ProfileModel("Payments"))
        opsiProfileList.add(ProfileModel("Rate App"))
        opsiProfileList.add(ProfileModel("Help Center"))
        opsiProfileList.add(ProfileModel("Privacy & Policy"))
        opsiProfileList.add(ProfileModel("Terms & Condition"))
        opsiProfileList.add(ProfileModel("Security"))

    }

    override fun onClick(v: View, data: ProfileModel) {
        Toast.makeText(context, "test klik " + data.title, Toast.LENGTH_SHORT).show()
    }
}