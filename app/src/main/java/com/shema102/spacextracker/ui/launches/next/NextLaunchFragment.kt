package com.shema102.spacextracker.ui.launches.next

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.ViewModelProvider
import com.bumptech.glide.Glide
import com.shema102.spacextracker.R
import com.shema102.spacextracker.data.db.entity.NextLaunch
import com.shema102.spacextracker.ui.base.ScopedFragment
import kotlinx.android.synthetic.main.next_launch_fragment.*
import kotlinx.coroutines.launch
import org.kodein.di.KodeinAware
import org.kodein.di.android.x.closestKodein
import org.kodein.di.generic.instance
import com.shema102.spacextracker.internal.dateTimeFromTimestamp

class NextLaunchFragment : ScopedFragment(), KodeinAware {
    override val kodein by closestKodein()

    private val viewModelFactory: NextLaunchViewModelFactory by instance()

    private lateinit var viewModel: NextLaunchViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.next_launch_fragment, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewModel =
            ViewModelProvider(this, viewModelFactory).get(NextLaunchViewModel::class.java)

        bindUi()
    }

    private fun bindUi() = launch {
        val nextLaunch = viewModel.nextLaunch.await()
        nextLaunch.observe(viewLifecycleOwner, {
            if (it == null) return@observe

            group_loading.visibility = View.GONE
            content.visibility = View.VISIBLE
            textView_mission_name.text = it.name
            textView_mission_details.text = it.details
            textView_launch_date.text = dateTimeFromTimestamp(it.dateUnix)
            textView_payload_name.text = it.payloadsList[0].toString()
            updateMissionImage(it)
        })
    }

    private fun updateMissionImage(launchEntry: NextLaunch) {
        Glide.with(this)
            .load(
                launchEntry.links?.patch?.small
                    ?: "https://https://www.spacex.com/static/images/share.jpg"
            )
            .into(imageView_mission_badge)
    }
}