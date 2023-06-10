package com.rentaloca.rentalocaapp.ui.detail

import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import com.rentaloca.rentalocaapp.R
import com.rentaloca.rentalocaapp.databinding.ActivityDetailBinding
import com.rentaloca.rentalocaapp.model.DressModel

class DetailActivity : AppCompatActivity(), View.OnClickListener  {
    private lateinit var binding: ActivityDetailBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_detail)
        binding = ActivityDetailBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val dress = if (Build.VERSION.SDK_INT >= 33) {
            intent.getParcelableExtra(EXTRA_DRESS, DressModel::class.java)
        } else {
            @Suppress("DEPRECATION")
            intent.getParcelableExtra(EXTRA_DRESS)
        }

        if (dress != null) {
            binding.tvPhoto.setImageResource(dress.photo)
            binding.tvTitleDetail.text = dress.dressname
            binding.tvPriceDetail.text = dress.price
            binding.tvDescriptionDetail.text = dress.description
//            binding.detailFounders.text = dress.bodyshape
        }

//        binding.actionShare.setOnClickListener(this)
    }

    override fun onClick(v: View) {
//        val techCompany2 = if (Build.VERSION.SDK_INT >= 33) {
//            intent.getParcelableExtra(EXTRA_DRESS, DressModel::class.java)
//        } else {
//            @Suppress("DEPRECATION")
//            intent.getParcelableExtra(EXTRA_DRESS)
//        }

//        when (v.id) {
//            R.id.action_share -> {
//                val share = Intent(Intent.ACTION_SEND)
//                share.type = "text/plain"
//                if (techCompany2 != null) {
//                    share.putExtra(Intent.EXTRA_TEXT, "Company : ${techCompany2.name}, Founded : ${techCompany2.founded}, Founders : ${techCompany2.founders}, Description : ${techCompany2.description}")
//                }
//                startActivity(Intent.createChooser(share, "Share"))
//            }
//        }
    }

    companion object {
        private const val EXTRA_DRESS = "key_dress"
    }
}