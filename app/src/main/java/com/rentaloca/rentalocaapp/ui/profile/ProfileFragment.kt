package com.rentaloca.rentalocaapp.ui.profile

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.RadioGroup
import android.widget.Toast
import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.preferencesDataStore
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.rentaloca.rentalocaapp.databinding.FragmentHomeForYouBinding
import com.rentaloca.rentalocaapp.databinding.FragmentProfileBinding
import com.rentaloca.rentalocaapp.model.UserPreference
import com.rentaloca.rentalocaapp.model.dummy.ProfileModel
import com.rentaloca.rentalocaapp.ui.MainActivity
import com.rentaloca.rentalocaapp.ui.ViewModelFactory
import com.rentaloca.rentalocaapp.ui.auth.signin.SigninActivity
import com.rentaloca.rentalocaapp.ui.home.SpaceItemDecoration

private val Context.dataStore: DataStore<Preferences> by preferencesDataStore(name = "settings")

class ProfileFragment : Fragment(),ProfileAdapter.ItemAdapterCallback {
    private lateinit var binding2: FragmentHomeForYouBinding
    private lateinit var questionRadioGroup: RadioGroup
    private lateinit var bodyShapeRadioGroup: RadioGroup
    private lateinit var rvDressModel: RecyclerView
    private lateinit var profileViewModel: ProfileViewModel
    private lateinit var binding: FragmentProfileBinding
    private var opsiProfileNotLoginList : ArrayList<ProfileModel> = ArrayList()
    private var opsiProfileIsLoginList : ArrayList<ProfileModel> = ArrayList()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding2 = FragmentHomeForYouBinding.inflate(inflater, container, false)
        binding = FragmentProfileBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        questionRadioGroup = binding2.questionRadioGroup
        bodyShapeRadioGroup = binding2.bodyShapeRadioGroup
        rvDressModel = binding2.rcListForYou

        val spacingInPixels = 9
        val itemDecoration = SpaceItemDecoration(spacingInPixels)
        binding.rcProfile.addItemDecoration(itemDecoration)

        initDataProfileNotLogin()
        initDataProfileIsLogin()
        setupViewModel()
    }

    private fun initDataProfileNotLogin() {
        opsiProfileNotLoginList = ArrayList()
        opsiProfileNotLoginList.add(ProfileModel("Create Account Or Login"))
        opsiProfileNotLoginList.add(ProfileModel("Payments"))
        opsiProfileNotLoginList.add(ProfileModel("Rate App"))
        opsiProfileNotLoginList.add(ProfileModel("Help Center"))
        opsiProfileNotLoginList.add(ProfileModel("Privacy & Policy"))
        opsiProfileNotLoginList.add(ProfileModel("Terms & Condition"))
        opsiProfileNotLoginList.add(ProfileModel("Security"))

    }

    private fun initDataProfileIsLogin() {
        opsiProfileIsLoginList = ArrayList()
        opsiProfileIsLoginList.add(ProfileModel("Edit Profile"))
        opsiProfileIsLoginList.add(ProfileModel("Payments"))
        opsiProfileIsLoginList.add(ProfileModel("Rate App"))
        opsiProfileIsLoginList.add(ProfileModel("Help Center"))
        opsiProfileIsLoginList.add(ProfileModel("Privacy & Policy"))
        opsiProfileIsLoginList.add(ProfileModel("Terms & Condition"))
        opsiProfileIsLoginList.add(ProfileModel("Security"))
        opsiProfileIsLoginList.add(ProfileModel("Logout"))
    }

    private fun setupViewModel() {
        profileViewModel = ViewModelProvider(
            this,
            ViewModelFactory(UserPreference.getInstance(requireContext().dataStore))
        )[ProfileViewModel::class.java]

        profileViewModel.getUser().observe(viewLifecycleOwner) { user ->
            if (user.isLogin) {
                binding.tvUserName.text = user.fullname
                binding.tvUserEmail.text = user.email
                val adapter = ProfileAdapter(opsiProfileIsLoginList, this)
                val layoutManager: RecyclerView.LayoutManager = LinearLayoutManager(activity)
                binding.rcProfile.layoutManager = layoutManager
                binding.rcProfile.adapter = adapter
            } else {
                binding.tvUserNotLogin.text = "You Are Not Logged In"
                val adapter = ProfileAdapter(opsiProfileNotLoginList, this)
                val layoutManager: RecyclerView.LayoutManager = LinearLayoutManager(activity)
                binding.rcProfile.layoutManager = layoutManager
                binding.rcProfile.adapter = adapter
            }
        }
    }

    override fun onClick(v: View, data: ProfileModel) {
        when (data.title) {
            "Create Account Or Login" -> {
                val intent = Intent(context, SigninActivity::class.java)
                startActivity(intent)
            }
            "Logout" -> {
                profileViewModel.logout()
                rvDressModel.visibility = View.GONE
                bodyShapeRadioGroup.visibility = View.GONE
                binding2.questionTextView.visibility = View.VISIBLE
                binding2.questionTextView.text = "Pilih tipe tubuh"
                questionRadioGroup.visibility = View.VISIBLE
                val intent = Intent(context, MainActivity::class.java)
                intent.flags = Intent.FLAG_ACTIVITY_CLEAR_TASK or Intent.FLAG_ACTIVITY_NEW_TASK
                startActivity(intent)
            }
            else -> {
                Toast.makeText(context, data.title, Toast.LENGTH_SHORT).show()
            }
        }
    }
}