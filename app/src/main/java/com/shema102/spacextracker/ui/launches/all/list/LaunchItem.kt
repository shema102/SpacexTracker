package com.shema102.spacextracker.ui.launches.all.list

import com.bumptech.glide.Glide
import com.shema102.spacextracker.R
import com.shema102.spacextracker.data.db.entity.LaunchEntry
import com.shema102.spacextracker.internal.dateTimeFromTimestamp
import com.xwray.groupie.kotlinandroidextensions.Item
import com.xwray.groupie.kotlinandroidextensions.ViewHolder
import kotlinx.android.synthetic.main.launch_item.*

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
        val launchDate: String = (launchEntry.staticFireDateUnix?.let { dateTimeFromTimestamp(it) } ?: "No info").toString()
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

}