package com.sardorbek.dastyorapp.adapter

import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentActivity
import androidx.viewpager2.adapter.FragmentStateAdapter
import com.sardorbek.dastyorapp.fragments.IssiqFragment
import com.sardorbek.dastyorapp.fragments.SifatliFragment
import com.sardorbek.dastyorapp.fragments.TezFragment
class FragmentAdapter(val items:ArrayList<Fragment>,activity: AppCompatActivity):
        FragmentStateAdapter(activity){
    override fun getItemCount(): Int {
        return items.size
    }

    override fun createFragment(position: Int): Fragment {
return items[position]
    }

}