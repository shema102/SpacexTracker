package com.shema102.spacextracker.ui.about

import android.os.Bundle
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.preference.PreferenceFragmentCompat

class AboutFragment: Fragment() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        Toast.makeText(activity?.applicationContext, "About", Toast.LENGTH_LONG).show()
    }
}