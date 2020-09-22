package com.example.spacextracker.ui.launches.all.list

import com.bumptech.glide.Glide
import com.example.spacextracker.R
import com.example.spacextracker.data.db.entity.LaunchEntry
import com.xwray.groupie.kotlinandroidextensions.Item
import com.xwray.groupie.kotlinandroidextensions.ViewHolder
import kotlinx.android.synthetic.main.launch_item.*
import java.text.SimpleDateFormat
import java.util.*

class LaunchItem(
    private val launchEntry: LaunchEntry
) : Item() {
    override fun bind(viewHolder: ViewHolder, position: Int) {
        viewHolder.apply {
            textView_name.text = launchEntry.name
            updateLaunchDate()
            updateMissionImage()
        }
    }

    override fun getLayout(): Int = R.layout.launch_item

    private fun ViewHolder.updateLaunchDate() {
        val launchDate: String = (launchEntry.staticFireDateUnix?.let { timeFromTimestamp(it) } ?: "No info").toString()
        textView_launch_date.text = launchDate
    }

    private fun ViewHolder.updateMissionImage() {
        Glide.with(this.containerView)
            .load(
                launchEntry.links?.patch?.small
                    ?: "https://https://www.spacex.com/static/images/share.jpg"
            )
            .into(imageView_mission_badge)
    }

    private fun timeFromTimestamp(timestamp: Int): String {
        val milis = timestamp.toLong() * 1000
        val date = Date(milis)
        return SimpleDateFormat("hh:mm MM.dd.yyyy").format(date)
    }
}