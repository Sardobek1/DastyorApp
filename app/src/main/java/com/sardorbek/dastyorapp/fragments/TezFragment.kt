package com.sardorbek.dastyorapp.fragments

import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.sardorbek.dastyorapp.R
import com.sardorbek.dastyorapp.classes.NumberActivity
import com.sardorbek.dastyorapp.databinding.FragmentTezBinding
import kotlinx.android.synthetic.main.fragment_tez.*

class TezFragment : Fragment() {
    private var _binding: FragmentTezBinding? = null
    private val binding get() = _binding!!
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?,

    ): View? {
        _binding = FragmentTezBinding.inflate(inflater, container, false)


        binding.tonumber.setOnClickListener {
            val intent = Intent(activity,NumberActivity::class.java)
            startActivity(intent)
        }
        return binding.root

    }
    /*lateinit var mView: View
override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
    mView=inflater.inflate(R.layout.product_list,container,false)

    mView.addProduct.setOnClickListener {

        val intent=Intent(activity,ProductAddActivity::class.java)
        startActivity(intent)
    }
    return mView
}*/




}