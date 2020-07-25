package com.findme.tugaspam.ui

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import com.findme.tugaspam.R
import com.findme.tugaspam.ui.fragment.AccountFragment
import com.findme.tugaspam.ui.fragment.AddFragment
import com.findme.tugaspam.ui.fragment.HomeFragment
import kotlinx.android.synthetic.main.activity_home.*

class HomeActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_home)

        if (savedInstanceState == null) {
            loadFragment(HomeFragment())
        }

        buble_navigation.setNavigationChangeListener { view, position ->
            when (view.id) {
                R.id.home -> {
                    loadFragment(HomeFragment())
                }
                R.id.add -> {
                    loadFragment(AddFragment())
                }
                R.id.account -> {
                    loadFragment(AccountFragment())
                }
            }
        }
    }

    private fun loadFragment(fragment: Fragment) {
        supportFragmentManager.beginTransaction()
            .replace(R.id.fl_container, fragment)
            .commit()
    }
}
