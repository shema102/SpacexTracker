package com.shema102.spacextracker.ui.settings

import android.app.job.JobInfo
import android.app.job.JobScheduler
import android.content.ComponentName
import android.content.Context.JOB_SCHEDULER_SERVICE
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.app.AppCompatDelegate
import androidx.core.content.ContextCompat
import androidx.preference.ListPreference
import androidx.preference.Preference
import androidx.preference.PreferenceFragmentCompat
import androidx.preference.SwitchPreference
import com.shema102.spacextracker.NextLaunchNotifierJobService
import com.shema102.spacextracker.R
import com.shema102.spacextracker.data.provider.NotificationProvider
import com.shema102.spacextracker.data.provider.ThemeProvider
import org.kodein.di.KodeinAware
import org.kodein.di.android.x.closestKodein
import org.kodein.di.generic.instance

class SettingsFragment : PreferenceFragmentCompat(), KodeinAware {
    override val kodein by closestKodein()

    private val themePreference by lazy {
        findPreference<ListPreference>(getString(R.string.theme_preferences_key))
    }

    private val themeProvider: ThemeProvider by instance()

    private val notificationPreference by lazy {
        findPreference<SwitchPreference>(getString(R.string.notification_preferences_key))
    }

    private val notificationProvider: NotificationProvider by instance()

    private val JOB_ID: Int = 123456

    private val TAG = "MainActivity"

    override fun onCreatePreferences(savedInstanceState: Bundle?, rootKey: String?) {
        addPreferencesFromResource(R.xml.preferences)
        setThemePreference()
        setNotification()
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

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = super.onCreateView(inflater, container, savedInstanceState)
        setColors(view)
        return view
    }

    private fun setColors(view: View?) {
        context?.getColor(R.color.colorBackground)?.let { view?.setBackgroundColor(it) }
    }


    private fun setNotification() {
        notificationPreference?.onPreferenceChangeListener =
            Preference.OnPreferenceChangeListener { preference, newValue ->
                val preferenceKey = preference.key ?: ""
                if (preferenceKey == requireContext().getString(R.string.notification_preferences_key)) {
                    if (!notificationProvider.isNotificationSet())
                        scheduleNextLaunchNotificationJob()
                    else
                        cancelNextLaunchNotificationJob()
                }
                true
            }
    }

    private fun scheduleNextLaunchNotificationJob() {
        val interval: Long = 15 * 60 * 1000 // 15 minutes

        val componentName = ComponentName(
            requireContext(),
            NextLaunchNotifierJobService::class.java
        )
        val info = JobInfo.Builder(JOB_ID, componentName)
            .setPeriodic(interval)
            .setPersisted(true)
            .build()

        val scheduler = context?.getSystemService(JOB_SCHEDULER_SERVICE) as JobScheduler

        val resultCode = scheduler?.schedule(info)
        if (resultCode == JobScheduler.RESULT_SUCCESS) {
            Log.d(TAG, "Job scheduled")
        } else {
            Log.d(TAG, "Job scheduling failed")
        }

    }

    private fun cancelNextLaunchNotificationJob() {
        val scheduler = context?.getSystemService(JOB_SCHEDULER_SERVICE) as JobScheduler
        scheduler.cancel(JOB_ID)
        Log.d(TAG, "Job cancelled")
    }

}