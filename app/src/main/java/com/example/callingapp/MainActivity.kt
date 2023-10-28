package com.example.callingapp

import android.Manifest
import android.content.Intent
import android.content.pm.PackageManager
import android.net.Uri
import android.os.Bundle
import android.view.View
import android.widget.EditText
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import com.google.android.material.floatingactionbutton.FloatingActionButton


class MainActivity : AppCompatActivity() {
    var phoneNo: EditText? = null
    var callbtn: FloatingActionButton? = null
    var PERMISSION_CODE = 100
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        phoneNo = findViewById(R.id.editTextPhone);
        callbtn = findViewById(R.id.callbtn);

        if (ContextCompat.checkSelfPermission(this@MainActivity, Manifest.permission.CALL_PHONE
        ) != PackageManager.PERMISSION_GRANTED
        ) {
            ActivityCompat.requestPermissions(
                this@MainActivity,
                arrayOf<String>(Manifest.permission.CALL_PHONE),
                MainActivity.Companion.PERMISSION_CODE
            )
        }
        callbtn!!.setOnClickListener(View.OnClickListener {
            val phoneno = phoneNo!!.getText().toString()
            val i = Intent(Intent.ACTION_CALL)
            i.data = Uri.parse("tel:$phoneno")
            startActivity(i)
        })
    }

    companion object {
        var PERMISSION_CODE = 100
    }
}