package com.example.callingapp

import android.content.Intent
import android.content.pm.PackageManager
import android.net.Uri
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.hbb20.CountryCodePicker


class MainActivity : AppCompatActivity() {
    var countryCodePicker: CountryCodePicker? = null
    var phone: EditText? = null
    var message:EditText? = null
    var sendbtn: Button? = null
    var messagestr: String? = null
    var phonestr:kotlin.String? = ""

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        countryCodePicker = findViewById(R.id.countryCode);
        phone = findViewById(R.id.phoneNo);
        message = findViewById(R.id.message);
        sendbtn = findViewById(R.id.sendbtn)

        sendbtn!!.setOnClickListener(object : View.OnClickListener {
            override fun onClick(v: View) {
                messagestr = message!!.getText().toString()

                phonestr = phone!!.getText().toString()
                if (!messagestr!!.isEmpty() && !phonestr!!.isEmpty()) {
                    countryCodePicker!!.registerCarrierNumberEditText(phone)
                    phonestr = countryCodePicker!!.getFullNumber()
                    if (isWhatappInstalled) {
                        val i = Intent(
                            Intent.ACTION_VIEW, Uri.parse(
                                "https://api.whatsapp.com/send?phone=" + phonestr +
                                        "&text=" + messagestr
                            )
                        )
                        startActivity(i)
                        message!!.setText("")
                        phone!!.setText("")
                    } else {
                        Toast.makeText(
                            this@MainActivity,
                            "Whatsapp is not installed",
                            Toast.LENGTH_SHORT
                        ).show()
                    }
                } else {
                    Toast.makeText(
                        this@MainActivity,
                        "Please fill in the Phone no. and message it can't be empty",
                        Toast.LENGTH_LONG
                    ).show()
                }
            }
        })
    }

    private val isWhatappInstalled: Boolean
        private get() {
            val packageManager = packageManager
            val whatsappInstalled: Boolean
            whatsappInstalled = try {
                packageManager.getPackageInfo("com.whatsapp", PackageManager.GET_ACTIVITIES)
                true
            } catch (e: PackageManager.NameNotFoundException) {
                false
            }
            return whatsappInstalled
        }
}


