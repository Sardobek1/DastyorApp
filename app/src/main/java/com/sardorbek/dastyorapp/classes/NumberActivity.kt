package com.sardorbek.dastyorapp.classes

import android.content.Context
import android.content.Intent
import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.CountDownTimer
import android.view.View
import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.Spinner
import android.widget.Toast
import androidx.annotation.RequiresApi
import com.android.volley.Response
import com.android.volley.toolbox.StringRequest
import com.android.volley.toolbox.Volley
import com.google.gson.Gson
import com.sardorbek.dastyorapp.R
import com.sardorbek.dastyorapp.databinding.ActivityMainBinding
import com.sardorbek.dastyorapp.databinding.ActivityNumberBinding
import com.sardorbek.dastyorapp.models.InfoModel
import com.sardorbek.dastyorapp.models.SuccesModel
import kotlinx.android.synthetic.main.activity_number.*


class NumberActivity : AppCompatActivity() {

    private lateinit var binding: ActivityNumberBinding

    private val url = "https://dastyordelivery.uz/api/phone/auth/"


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityNumberBinding.inflate(layoutInflater)
        setContentView(binding.root)

binding.tocode.setOnClickListener {
    if (binding.phonenumber.text.toString().isNotEmpty()) {
        val url = "https://dastyordelivery.uz/api/phone/auth/"

        sendPhoneCode(binding.phonenumber.text.toString(), this, url)
    } else {
        Toast.makeText(this, "Raqam kiriting !", Toast.LENGTH_SHORT).show()
    }
}

}

private fun sendPhoneCode(phone: String, ctx: Context, url: String) {
    val queue = Volley.newRequestQueue(ctx)
    val request =
        object : StringRequest(Method.POST, url, Response.Listener { response ->
            val data = Gson().fromJson(response, InfoModel::class.java)
            val intent = Intent(this, MainActivity::class.java)
            intent.putExtra("time", data.expiresIn)
            intent.putExtra("phone", binding.phonenumber.text.toString())
            startActivity(intent)
            finish()
        }, Response.ErrorListener {
            Toast.makeText(this, "error", Toast.LENGTH_SHORT).show()
        }) {
            override fun getParams(): MutableMap<String, String> {
                val body = HashMap<String, String>()
                body["phone"] = phone
                return body
            }
        }
    queue.add(request)
    binding.tocode.setOnClickListener {
        startActivity(Intent(this, CodeActivity::class.java))
    }

}
}

