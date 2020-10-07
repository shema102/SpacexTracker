package com.shema102.spacextracker.data.provider

import android.app.UiModeManager
import android.content.Context
import android.content.SharedPreferences
import androidx.appcompat.app.AppCompatDelegate
import androidx.preference.PreferenceManager
import com.shema102.spacextracker.R
import java.security.InvalidParameterException


class ThemeProviderImpl(val context: Context) : ThemeProvider {
    private val appContext = context.applicationContext

    private val preferences: SharedPreferences
        get() = PreferenceManager.getDefaultSharedPreferences(appContext)

    override fun getThemeFromPreferences(): Int {
        val selectedTheme = preferences.getString(
            appContext.getString(R.string.theme_preferences_key),
            appContext.getString(R.string.theme_preferences_value))

        return selectedTheme?.let {
            getTheme(it)
        } ?: AppCompatDelegate.MODE_NIGHT_FOLLOW_SYSTEM
    }

    override fun getTheme(selectedTheme: String): Int = when (selectedTheme){
        appContext.getString(R.string.dark_theme_preference_value) -> UiModeManager.MODE_NIGHT_YES
        appContext.getString(R.string.light_theme_preference_value) -> UiModeManager.MODE_NIGHT_NO
        appContext.getString(R.string.system_theme_preference_value) -> UiModeManager.MODE_NIGHT_AUTO
        else -> throw InvalidParameterException("Theme not defined for $selectedTheme")
    }

}