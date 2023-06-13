package com.rentaloca.rentalocaapp.ui.detail

import android.annotation.SuppressLint
import android.content.Context
import android.content.Intent
import android.os.AsyncTask
import android.os.Build
import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.preferencesDataStore
import androidx.lifecycle.ViewModelProvider
import com.rentaloca.rentalocaapp.R
import com.rentaloca.rentalocaapp.databinding.ActivityDetailBinding
import com.rentaloca.rentalocaapp.model.DressModel
import com.rentaloca.rentalocaapp.model.UserModel
import com.rentaloca.rentalocaapp.model.UserPreference
import com.rentaloca.rentalocaapp.ui.ViewModelFactory
import com.rentaloca.rentalocaapp.ui.auth.signin.SigninActivity
import java.util.*
import javax.mail.*
import javax.mail.internet.InternetAddress
import javax.mail.internet.MimeMessage

private val Context.dataStore: DataStore<Preferences> by preferencesDataStore(name = "settings")

class DetailActivity : AppCompatActivity(), View.OnClickListener {
    private lateinit var binding: ActivityDetailBinding
    private lateinit var detailViewModel: DetailViewModel
    private lateinit var user: UserModel

    @SuppressLint("SetTextI18n")
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
            binding.tvPriceDetail.text = "${dress.price} / ${dress.days}"
            binding.tvDescriptionDetail.text = dress.description
            binding.tvBodyShape.text = "Body Shape : ${dress.bodyshape}"
        }

        binding.buttonRent.setOnClickListener(this)

        detailViewModel = ViewModelProvider(
            this,
            ViewModelFactory(UserPreference.getInstance(dataStore))
        )[DetailViewModel::class.java]

        detailViewModel.getUser().observe(this) { user ->
            this.user = user
        }
    }

    override fun onClick(v: View) {
        when (v.id) {
            R.id.buttonRent -> {
                val dress = intent.getParcelableExtra(EXTRA_DRESS) as DressModel?
                if(user.isLogin == true && dress != null) {
//                    if () {
                        SendEmailTask().execute(dress)
//                    }
                } else if(user.isLogin == false) {
                    startActivity(Intent(this, SigninActivity::class.java))
                }
            }
        }
    }

    private inner class SendEmailTask : AsyncTask<DressModel, Void, Boolean>() {
        override fun doInBackground(vararg params: DressModel): Boolean {
            val dress = params[0]

            val props = Properties()
            props["mail.smtp.auth"] = "true"
            props["mail.smtp.starttls.enable"] = "true"
            props["mail.smtp.host"] = "smtp.gmail.com"
            props["mail.smtp.port"] = "587"

            val username = "rentalocabangkit2023@gmail.com" // Ganti dengan email pengirim Gmail
            val password = "neesdrblzftxnuna" // Ganti dengan password email pengirim Gmail

            val session = Session.getInstance(props,
                object : Authenticator() {
                    override fun getPasswordAuthentication(): PasswordAuthentication {
                        return PasswordAuthentication(username, password)
                    }
                })

            try {
                val message = MimeMessage(session)
                message.setFrom(InternetAddress(username))
                message.setRecipients(
                    Message.RecipientType.TO,
                    InternetAddress.parse(user.email) // Ganti dengan alamat email penerima
                )
                message.subject = "Rental Details: ${dress.dressname}"

                val emailContent = "Halo, ${user.fullname}\n\n" +
                        "Terima kasih telah menggunakan jasa aplikasi Rentaloca kami untuk menyewa pakaian. " +
                        "Berikut ini adalah detail pesanan Anda:\n\n" +
                        "- Nama Pakaian: ${dress.dressname}\n" +
                        "- Body Shape: ${dress.bodyshape}\n" +
                        "- Durasi Sewa: ${dress.days}\n\n" +
                        "Mohon melakukan pembayaran sebesar ${dress.price} ke rekening berikut:\n" +
                        "- Bank: [Nama Bank]\n" +
                        "- Nomor Rekening: [Nomor Rekening]\n" +
                        "- Atas Nama: Fakhirah Azzahra Nurheyanti\n\n" +
                        "Setelah melakukan pembayaran, harap konfirmasikan melalui pesan WhatsApp ke nomor berikut: [Nomor WhatsApp Rentaloca]. " +
                        "Mohon sertakan bukti pembayaran agar kami dapat memverifikasi dan mengkonfirmasi pemesanan Anda.\n\n" +
                        "Kami berharap pakaian yang Anda sewa dapat memenuhi kebutuhan dan harapan Anda. " +
                        "Jika Anda memiliki pertanyaan atau membutuhkan bantuan tambahan, jangan ragu untuk menghubungi kami melalui aplikasi Rentaloca.\n\n" +
                        "Sekali lagi, terima kasih telah memilih Rentaloca sebagai solusi penyewaan pakaian Anda. " +
                        "Kami berharap dapat melayani Anda lagi di masa depan.\n\n" +
                        "Terima kasih dan salam hangat,\n\n" +
                        "Tim Rentaloca"

                message.setText(emailContent)

                Transport.send(message)

                return true
            } catch (e: MessagingException) {
                e.printStackTrace()
                return false
            }
        }

        override fun onPostExecute(result: Boolean) {
            if (result) {
                Toast.makeText(this@DetailActivity, "Email berhasil dikirim", Toast.LENGTH_SHORT).show()
            } else {
                Toast.makeText(this@DetailActivity, "Gagal mengirim email", Toast.LENGTH_SHORT).show()
            }
        }
    }

    companion object {
        private const val EXTRA_DRESS = "key_dress"
    }
}