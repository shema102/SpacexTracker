package com.shema102.spacextracker.ui.settings

import android.os.Bundle
import android.widget.Toast
import androidx.fragment.app.Fragment

class SettingsFragment: Fragment() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        Toast.makeText(activity?.applicationContext, "Settings", Toast.LENGTH_LONG).show()
    }
}