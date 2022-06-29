package com.sardorbek.dastyorapp.classes

import android.content.Context
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.Spinner
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.viewpager2.adapter.FragmentStateAdapter
import androidx.viewpager2.widget.ViewPager2
import com.android.volley.Response
import com.android.volley.toolbox.StringRequest
import com.android.volley.toolbox.Volley
import com.google.gson.Gson
import com.sardorbek.dastyorapp.R
import com.sardorbek.dastyorapp.adapter.FragmentAdapter
import com.sardorbek.dastyorapp.databinding.ActivityMainBinding
import com.sardorbek.dastyorapp.databinding.ActivityNumberBinding
import com.sardorbek.dastyorapp.fragments.IssiqFragment
import com.sardorbek.dastyorapp.fragments.SifatliFragment
import com.sardorbek.dastyorapp.fragments.TezFragment
import com.sardorbek.dastyorapp.models.InfoModel


class MainActivity : AppCompatActivity() {
    private lateinit var binding : ActivityMainBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)



        val viewPager: ViewPager2 = findViewById(R.id.viewPager2)

        val fragments: ArrayList<Fragment> = arrayListOf(

            SifatliFragment(),
            IssiqFragment(),
            TezFragment()
        )
        val adapter = FragmentAdapter(fragments,this )
        viewPager.adapter = adapter
    }
}
