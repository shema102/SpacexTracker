package com.shema102.spacextracker.ui.settings

import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.app.AppCompatDelegate
import androidx.preference.ListPreference
import androidx.preference.Preference
import androidx.preference.PreferenceFragmentCompat
import com.shema102.spacextracker.R
import com.shema102.spacextracker.data.provider.ThemeProvider
import com.shema102.spacextracker.data.provider.ThemeProviderImpl
import org.kodein.di.KodeinAware
import org.kodein.di.android.x.closestKodein
import org.kodein.di.generic.instance

class SettingsFragment : PreferenceFragmentCompat(), KodeinAware {
    override val kodein by closestKodein()

    private val themePreference by lazy {
        findPreference<ListPreference>(getString(R.string.theme_preferences_key))
    }

    private val themeProvider: ThemeProvider by instance()

    override fun onCreatePreferences(savedInstanceState: Bundle?, rootKey: String?) {
        addPreferencesFromResource(R.xml.preferences)
        setThemePreference()
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        (activity as? AppCompatActivity)?.supportActionBar?.title = "Settings"
        (activity as? AppCompatActivity)?.supportActionBar?.subtitle = null
    }

    private fun setThemePreference() {
        themePreference?.onPreferenceChangeListener =
            Preference.OnPreferenceChangeListener { preference, newValue ->
                val preferenceKey = preference.key ?: ""
                if (preferenceKey == requireContext().getString(R.string.theme_preferences_key)) {
                    val theme = themeProvider.getTheme(newValue as String)
                    AppCompatDelegate.setDefaultNightMode(theme)
                }
                true
            }
    }

    // TODO Hide menu button on entering settings
}