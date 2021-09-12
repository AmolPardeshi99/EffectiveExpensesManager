package com.example.effectiveexpensesmanager

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.effectiveexpensesmanager.adapter.FragmentAdapter
import com.example.effectiveexpensesmanager.fragments.BalanceFragment
import com.example.effectiveexpensesmanager.fragments.ExpenseFragment
import com.example.effectiveexpensesmanager.fragments.IncomeFragment
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val fragmentAdapter = FragmentAdapter(supportFragmentManager)
        fragmentAdapter.addFragment(IncomeFragment(),"Income")
        fragmentAdapter.addFragment(ExpenseFragment(),"Expense")
        fragmentAdapter.addFragment(BalanceFragment(),"Balance")

        viewPager.adapter= fragmentAdapter
        tabLayout.setupWithViewPager(viewPager)

        ivAddbtn.setOnClickListener {
            intent = Intent(this,AddDataActivity::class.java)
            startActivity(intent)
        }
    }
}