package com.shema102.spacextracker.data.provider

import android.content.Context
import android.content.SharedPreferences
import androidx.preference.PreferenceManager
import com.shema102.spacextracker.R
import com.shema102.spacextracker.internal.UnitSystem

class UnitProviderImpl(context: Context) : UnitProvider {
    private val appContext = context.applicationContext

    private val preferences: SharedPreferences
        get() = PreferenceManager.getDefaultSharedPreferences(appContext)

    override fun getUnitSystem(): UnitSystem {
        val selectedName = preferences.getString(appContext.getString(R.string.unit_system_preferences_key), UnitSystem.METRIC.name)
        return UnitSystem.valueOf(selectedName!!)
    }
}