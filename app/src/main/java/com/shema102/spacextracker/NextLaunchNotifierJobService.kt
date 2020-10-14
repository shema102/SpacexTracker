package com.shema102.spacextracker

import android.app.Notification
import android.app.NotificationChannel
import android.app.NotificationManager
import android.app.PendingIntent
import android.app.job.JobParameters
import android.app.job.JobService
import android.content.Intent
import android.os.Build
import android.util.Log
import androidx.core.app.NotificationManagerCompat
import com.shema102.spacextracker.data.repository.SpacexRepository
import com.shema102.spacextracker.ui.MainActivity
import org.kodein.di.Kodein
import org.kodein.di.KodeinAware
import org.kodein.di.android.closestKodein
import org.kodein.di.generic.instance

class NextLaunchNotifierJobService : JobService(), KodeinAware {
    override val kodein: Kodein by closestKodein()

    val repository: SpacexRepository by instance()

    private var jobCancelled = false

    private val TAG = "Job"

    override fun onStartJob(params: JobParameters?): Boolean {
        Thread(Runnable {
            for (i in 0..9) {
                Log.d(TAG, "run: $i")
                if (jobCancelled) {
                    return@Runnable
                }
                try {
                    postNotification("Job started $i")
                    Thread.sleep(1000)
                } catch (e: InterruptedException) {
                    e.printStackTrace()
                }
            }
            Log.d(TAG, "Job finished")
            jobFinished(params, false)
        }).start()

        Log.d(TAG, "Job scheduled")
        return true
    }

    override fun onStopJob(params: JobParameters?): Boolean {
        postNotification("Job stopped")
        Log.d(TAG, "Job cancelled")
        jobCancelled = true
        return true
    }

    private fun postNotification(title: String) {
        val managerCompat = NotificationManagerCompat.from(this)

        val builder = Notification.Builder(this)
            .setContentTitle(title)
            .setContentText("Test text")
            .setSmallIcon(R.drawable.ic_default_spacex_badge)

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            val id = "next_launch_channel"
            val channel = NotificationChannel(id, "123", NotificationManager.IMPORTANCE_HIGH)
            managerCompat.createNotificationChannel(channel)

            builder.setChannelId(id)
        }

        val notifyIntent = Intent(this, MainActivity::class.java)
        val pendingIntent =
            PendingIntent.getActivity(
                this,
                2,
                notifyIntent,
                PendingIntent.FLAG_UPDATE_CURRENT
            )
        builder.setContentIntent(pendingIntent)
        val notificationCompat = builder.build()

        managerCompat.notify(3, notificationCompat)
    }
}

