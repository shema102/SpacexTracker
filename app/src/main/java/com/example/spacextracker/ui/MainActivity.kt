package com.example.spacextracker.ui

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Menu
import androidx.fragment.app.Fragment
import com.example.spacextracker.R
import com.google.android.material.bottomnavigation.BottomNavigationView
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val bottomNavigation = bottom_navigation
        supportFragmentManager
            .beginTransaction()
            .replace(R.id.fragment_container, HomeFragment())
            .commit()
        bottomNavigation.setOnNavigationItemSelectedListener(bottomNavigationListener)
    }

    private val bottomNavigationListener =
        BottomNavigationView.OnNavigationItemSelectedListener { item ->
            var selectedFragment: Fragment? = null

            when (item.itemId) {
                R.id.nav_home -> selectedFragment = HomeFragment()
                R.id.nav_next_launch -> selectedFragment = NextLaunchFragment()
                R.id.nav_roadster -> selectedFragment = RoadsterFragment()
            }

            if (selectedFragment != null) {
                supportFragmentManager
                    .beginTransaction()
                    .replace(R.id.fragment_container, selectedFragment)
                    .commit()
                return@OnNavigationItemSelectedListener true
            }

            return@OnNavigationItemSelectedListener false
        }


    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.top_menu, menu)
        return super.onCreateOptionsMenu(menu)
    }
}