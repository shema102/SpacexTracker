package com.shema102.spacextracker.ui.settings

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AppCompatDelegate
import androidx.preference.ListPreference
import androidx.preference.Preference
import androidx.preference.PreferenceFragmentCompat
import com.shema102.spacextracker.R
import com.shema102.spacextracker.data.provider.ThemeProvider
import dagger.android.support.AndroidSupportInjection
import javax.inject.Inject

class SettingsFragment : PreferenceFragmentCompat() {

    private val themePreference by lazy {
        findPreference<ListPreference>(getString(R.string.theme_preferences_key))
    }

    @Inject
    lateinit var themeProvider: ThemeProvider

    override fun onCreatePreferences(savedInstanceState: Bundle?, rootKey: String?) {
        addPreferencesFromResource(R.xml.preferences)
        setThemePreference()
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

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        AndroidSupportInjection.inject(this)

        val view = super.onCreateView(inflater, container, savedInstanceState)
        setColors(view)
        return view
    }

    private fun setColors(view: View?){
        context?.getColor(R.color.colorBackground)?.let { view?.setBackgroundColor(it) }
    }

}