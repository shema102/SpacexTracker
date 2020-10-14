package com.shema102.spacextracker.data.provider

import android.content.Context
import android.content.SharedPreferences
import androidx.preference.PreferenceManager
import com.shema102.spacextracker.R

class NotificationProviderImpl(val context: Context) : NotificationProvider {
    private val appContext = context.applicationContext

    private val preferences: SharedPreferences
        get() = PreferenceManager.getDefaultSharedPreferences(appContext)

    override fun isNotificationSet(): Boolean {
        return preferences.getBoolean(
            appContext.getString(R.string.notification_preferences_key),
            false
        )
    }
}