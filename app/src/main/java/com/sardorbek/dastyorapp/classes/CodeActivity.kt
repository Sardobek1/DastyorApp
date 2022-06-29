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
import androidx.fragment.app.Fragment
import androidx.viewpager2.adapter.FragmentStateAdapter
import androidx.viewpager2.widget.ViewPager2
import com.android.volley.Response
import com.android.volley.toolbox.StringRequest
import com.android.volley.toolbox.Volley
import com.google.gson.Gson
import com.sardorbek.dastyorapp.R
import com.sardorbek.dastyorapp.databinding.ActivityCodeBinding
import com.sardorbek.dastyorapp.databinding.ActivityMainBinding
import com.sardorbek.dastyorapp.databinding.ActivityNumberBinding
import com.sardorbek.dastyorapp.fragments.IssiqFragment
import com.sardorbek.dastyorapp.fragments.TezFragment
import com.sardorbek.dastyorapp.models.InfoModel
import com.sardorbek.dastyorapp.models.SuccesModel


class CodeActivity : AppCompatActivity() {
    private var time: Long = 120
    private lateinit var binding: ActivityCodeBinding
    private val url = "https://dastyordelivery.uz/api/phone/auth/"
    private val verifyUrl = "https://dastyordelivery.uz/api/phone/verify/"
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        time = intent.getIntExtra("time", 120) * 1000.toLong()
        binding = ActivityCodeBinding.inflate(layoutInflater)
        setContentView(binding.root)
        startTimer()
        binding.again.setOnClickListener {
            sendPhoneCode(intent.getStringExtra("phone").toString(), url)
            binding.again.visibility = View.VISIBLE
            binding.pinview.visibility = View.VISIBLE
            it.visibility = View.GONE
            startTimer()
        }
        binding.clicktomenu.setOnClickListener {
            intent.getStringExtra("phone")?.let { phone -> verifyPhone(phone, verifyUrl) }
        }
    }


    private fun startTimer() {
        val timer = object : CountDownTimer(time, 1000) {
            override fun onTick(millisUntilFinished: Long) {
                val seconds = millisUntilFinished / 1000
                binding.again.text = timeConversion(seconds.toInt())
            }

            @RequiresApi(Build.VERSION_CODES.M)
            override fun onFinish() {
                binding.again.visibility = View.GONE
                binding.pinview.visibility = View.GONE
                binding.again.visibility = View.VISIBLE
            }
        }
        timer.start()


    }

    private fun timeConversion(totalSeconds: Int): String {
        val minutesInAnHour = 60
        val secondsInAMinute = 60
        val seconds = totalSeconds % secondsInAMinute
        val totalMinutes = totalSeconds / secondsInAMinute
        val minutes = totalMinutes % minutesInAnHour
        return "$minutes : $seconds"
    }

    private fun sendPhoneCode(phone: String, url: String) {
        val queue = Volley.newRequestQueue(this)
        val request =
            object : StringRequest(Method.POST, url, Response.Listener { response ->
                val data = Gson().fromJson(response, InfoModel::class.java)
                time = data.expiresIn.toLong()
            }, Response.ErrorListener {

            }) {
                override fun getParams(): MutableMap<String, String> {
                    val body = HashMap<String, String>()
                    body["phone"] = phone
//                    body["token"] = phone
                    return body
                }
            }
        queue.add(request)
    }

    private fun verifyPhone(phone: String, url: String) {
        val queue = Volley.newRequestQueue(this)
        val request =
            object : StringRequest(Method.POST, url, Response.Listener { response ->
                val data = Gson().fromJson(response, SuccesModel::class.java)
                time = data.expires_in.toLong()
            }, Response.ErrorListener { error ->
                Toast.makeText(this, error.message.toString(), Toast.LENGTH_SHORT).show()
            }) {
                override fun getParams(): MutableMap<String, String>? {
                    val body = HashMap<String, String>()
                    body["phone"] = phone
                    body["token"] = binding.pinview.text.toString()
                    return body
                }
            }
        queue.add(request)
    }
}


