package com.shema102.spacextracker.ui

import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.app.AppCompatDelegate
import androidx.navigation.NavController
import androidx.navigation.Navigation
import androidx.navigation.ui.NavigationUI
import androidx.navigation.ui.setupWithNavController
import com.shema102.spacextracker.R
import com.shema102.spacextracker.SpacexApplication
import com.shema102.spacextracker.data.provider.ThemeProvider
import kotlinx.android.synthetic.main.activity_main.*
import javax.inject.Inject

class MainActivity : AppCompatActivity() {

    private lateinit var navController: NavController

    @Inject
    lateinit var themeProvider: ThemeProvider

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        injectDagger()

        navController = Navigation.findNavController(this, R.id.nav_host_fragment)
        bottom_navigation.setupWithNavController(navController)
        NavigationUI.setupActionBarWithNavController(this, navController)

        setTheme()
    }

    private fun injectDagger(){
        SpacexApplication.instance.applicationComponent.inject(this)
    }

    private fun setTheme() {
        val theme = themeProvider.getThemeFromPreferences()
        AppCompatDelegate.setDefaultNightMode(theme)
    }

    override fun onSupportNavigateUp(): Boolean {
        return NavigationUI.navigateUp(navController, null)
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.overflow_menu, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        NavigationUI.onNavDestinationSelected(item, navController)
        return super.onOptionsItemSelected(item)
    }
}