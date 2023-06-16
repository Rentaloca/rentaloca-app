package com.rentaloca.rentalocaapp.ui.home.foryou

import android.annotation.SuppressLint
import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.RadioGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.rentaloca.rentalocaapp.R
import com.rentaloca.rentalocaapp.databinding.FragmentHomeForYouBinding
import com.rentaloca.rentalocaapp.model.DressModel
import com.rentaloca.rentalocaapp.ui.home.SpaceItemDecoration

class HomeForYouFragment : Fragment() {
    private lateinit var binding: FragmentHomeForYouBinding
    private lateinit var questionRadioGroup: RadioGroup
    private lateinit var bodyShapeRadioGroup: RadioGroup
    private lateinit var rvDressModel: RecyclerView
    private val bajuListForyou = ArrayList<DressModel>()
    private var bodyshape = ""

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentHomeForYouBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        questionRadioGroup = binding.questionRadioGroup
        bodyShapeRadioGroup = binding.bodyShapeRadioGroup
        rvDressModel = binding.rcListForYou
        val spacingInPixels = 14
        val itemDecoration = SpaceItemDecoration(spacingInPixels)
        binding.rcListForYou.addItemDecoration(itemDecoration)

        questionRadioGroup.setOnCheckedChangeListener { _, checkedId ->
            if (checkedId == R.id.radioYes) {
                binding.questionTextView.text = "Choose Your Body Shape"
                bodyShapeRadioGroup.visibility = View.VISIBLE
                questionRadioGroup.visibility = View.GONE
            } else if (checkedId == R.id.radioNo) {
                binding.questionTextView.text = "Please go to Facebook to find out your\n body shape using body shape detection filters"
                binding.buttonToFB.visibility = View.VISIBLE
                bodyShapeRadioGroup.visibility = View.GONE
                questionRadioGroup.visibility = View.GONE
            }
        }

        bodyShapeRadioGroup.setOnCheckedChangeListener { _, checkedId ->
            when (checkedId) {
                R.id.rbRectangle -> {
                    binding.questionTextView.visibility = View.GONE
                    bodyShapeRadioGroup.visibility = View.GONE
                    questionRadioGroup.visibility = View.GONE
                    rvDressModel.visibility = View.VISIBLE
                    bodyshape = ""
                    bodyshape = "Rectangle"
                    showRecyclerList()
                }
                R.id.rbITriangle -> {
                    binding.questionTextView.visibility = View.GONE
                    bodyShapeRadioGroup.visibility = View.GONE
                    questionRadioGroup.visibility = View.GONE
                    rvDressModel.visibility = View.VISIBLE
                    bodyshape = ""
                    bodyshape = "Inverted Triangle"
                    showRecyclerList()
                }
                R.id.rbPear -> {
                    binding.questionTextView.visibility = View.GONE
                    bodyShapeRadioGroup.visibility = View.GONE
                    questionRadioGroup.visibility = View.GONE
                    rvDressModel.visibility = View.VISIBLE
                    bodyshape = ""
                    bodyshape = "Pear"
                    showRecyclerList()
                }
                R.id.rbApple -> {
                    binding.questionTextView.visibility = View.GONE
                    bodyShapeRadioGroup.visibility = View.GONE
                    questionRadioGroup.visibility = View.GONE
                    rvDressModel.visibility = View.VISIBLE
                    bodyshape = ""
                    bodyshape = "Apple"
                    showRecyclerList()
                }
                R.id.rbHourglass -> {
                    binding.questionTextView.visibility = View.GONE
                    bodyShapeRadioGroup.visibility = View.GONE
                    questionRadioGroup.visibility = View.GONE
                    rvDressModel.visibility = View.VISIBLE
                    bodyshape = ""
                    bodyshape = "Hourglass"
                    showRecyclerList()
                }
            }
        }

        binding.buttonToFB.setOnClickListener {
            val url = "https://www.facebook.com/fbcameraeffects/testit/618045853600166/ZTNiZGQ5MWJkOWNjNzE0ODZlNTljNjhhOTBmOWE2ZGU=/"
            val intent = Intent(Intent.ACTION_VIEW)
            intent.data = Uri.parse(url)
            startActivity(intent)
            binding.questionTextView.text = "Do You Already Know Your Body Shape?"
            binding.buttonToFB.visibility = View.GONE
            bodyShapeRadioGroup.visibility = View.GONE
            questionRadioGroup.visibility = View.VISIBLE
        }
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
        for (i in dataBodyshape.indices) {
            if (dataBodyshape[i] == bodyshape) {
                val dress = DressModel(dataPhoto.getResourceId(i, -1), dataDressname[i], dataPrice[i], dataDays[i], dataDescription[i], dataBodyshape[i])
                listDress.add(dress)
            }
        }
        return listDress
    }

    private fun showRecyclerList() {
        bajuListForyou.clear()
        bajuListForyou.addAll(getListDress())
        rvDressModel.layoutManager = GridLayoutManager(activity, 2)
        val listTechCompanyAdapter = HomeForYouAdapter(bajuListForyou)
        rvDressModel.adapter = listTechCompanyAdapter
    }
}