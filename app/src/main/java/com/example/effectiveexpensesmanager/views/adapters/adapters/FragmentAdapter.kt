package com.example.effectiveexpensesmanager.views.adapters.adapters

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentStatePagerAdapter

class FragmentAdapter(fragmentManager: FragmentManager) :
    FragmentStatePagerAdapter(fragmentManager, BEHAVIOR_RESUME_ONLY_CURRENT_FRAGMENT) {
    var fragmentlist : ArrayList<Fragment> = ArrayList()
    var fragmentTitle : ArrayList<String> = ArrayList()


    override fun getCount(): Int {
        return fragmentlist.size;
    }

    override fun getPageTitle(position: Int): CharSequence {
        return fragmentTitle[position]
    }

    override fun getItem(position: Int): Fragment {
        return fragmentlist[position]
    }

    fun addFragment(fragment: Fragment,title:String){
        fragmentlist.add(fragment)
        fragmentTitle.add(title)
    }
}