package com.rentaloca.rentalocaapp.ui.detail

import android.annotation.SuppressLint
import android.content.Context
import android.content.Intent
import android.os.AsyncTask
import android.os.Build
import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
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
import com.rentaloca.rentalocaapp.ui.MainActivity
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

//        detailViewModel = ViewModelProvider(
//            this,
//            ViewModelFactory(UserPreference.getInstance(dataStore))
//        )[DetailViewModel::class.java]

        detailViewModel.getUser().observe(this) { user ->
            this.user = user
        }
    }

    override fun onClick(v: View) {
        when (v.id) {
            R.id.buttonRent -> {
                val dress = intent.getParcelableExtra(EXTRA_DRESS) as DressModel?
                if(user.isLogin == true && dress != null) {
                    SendEmailTask().execute(dress)
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
//                message.setRecipients(
//                    Message.RecipientType.TO,
//                    //InternetAddress.parse(user.email) // Ganti dengan alamat email penerima
//                )
                message.subject = "Rental Details: ${dress.dressname}"

                val emailContent = "Hi, ${user.fullname}!\n\n" +
                        "Thank you for using our Rentaloca app to rent clothing. Here are the details of your order:\n\n" +
                        "Dress Name: ${dress.dressname}\n" +
                        "Body Shape: ${dress.bodyshape}\n" +
                        "Rental Duration: ${dress.days}\n\n" +
                        "Please make a payment of ${dress.price} to the following bank account:\n\n" +
                        "Bank: BRI\n" +
                        "Account Number: 0111-01-058352-50-7\n" +
                        "Account Name: Fakhirah Azzahra Nurheyanti\n\n" +
                        "After making the payment, kindly confirm via WhatsApp message to the following number: 089636026480. " +
                        "Please attach the payment proof for verification and order confirmation.\n\n" +
                        "We will promptly send the ordered items to your provided address () once we have verified your order.\n\n" +
                        "We hope that the rented dress fulfills your needs and expectations. If you have any questions or require further assistance, feel free to reply to this email.\n\n" +
                        "Once again, thank you for choosing Rentaloca as your clothing rental solution. We look forward to serving you again in the future.\n\n" +
                        "Thank you and warm regards,\n\n" +
                        "Rentaloca Team"

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
                AlertDialog.Builder(this@DetailActivity).apply {
                    setTitle("Order Success!")
                    setMessage("Your order has been received. Please check your email for confirmation.")
                    setPositiveButton("Ok") { _, _ ->
                        val intent = Intent(context, MainActivity::class.java)
                        intent.flags = Intent.FLAG_ACTIVITY_CLEAR_TASK or Intent.FLAG_ACTIVITY_NEW_TASK
                        startActivity(intent)
                        finish()
                    }
                    create()
                    show()
                }
            } else {
                AlertDialog.Builder(this@DetailActivity).apply {
                    setTitle("Order Failed!")
                    setMessage("Have you used the correct email? Please double-check and try order again!")
                    setPositiveButton("Ok") { _, _ ->
                        val intent = Intent(context, MainActivity::class.java)
                        intent.flags = Intent.FLAG_ACTIVITY_CLEAR_TASK or Intent.FLAG_ACTIVITY_NEW_TASK
                        startActivity(intent)
                        finish()
                    }
                    create()
                    show()
                }
            }
        }
    }

    companion object {
        private const val EXTRA_DRESS = "key_dress"
    }
}