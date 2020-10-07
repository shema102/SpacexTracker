package com.shema102.spacextracker.ui.launches.next

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.bumptech.glide.Glide
import com.shema102.spacextracker.R
import com.shema102.spacextracker.data.db.entity.NextLaunchEntry
import com.shema102.spacextracker.data.db.entity.Payload
import com.shema102.spacextracker.data.provider.ThemeProvider
import com.shema102.spacextracker.data.provider.UnitProvider
import com.shema102.spacextracker.ui.base.ScopedFragment
import com.shema102.spacextracker.ui.launches.common.PayloadItem
import com.xwray.groupie.GroupAdapter
import com.xwray.groupie.ViewHolder
import kotlinx.android.synthetic.main.launch_details_fragment.*
import kotlinx.coroutines.launch
import org.kodein.di.KodeinAware
import org.kodein.di.android.x.closestKodein
import org.kodein.di.generic.instance
import java.text.DateFormat
import java.util.*

class NextLaunchFragment : ScopedFragment(), KodeinAware {
    override val kodein by closestKodein()

    private val viewModelFactory: NextLaunchViewModelFactory by instance()

    private lateinit var viewModel: NextLaunchViewModel

    private val unitProvider: UnitProvider by instance()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.launch_details_fragment, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewModel =
            ViewModelProvider(this, viewModelFactory).get(NextLaunchViewModel::class.java)

        (activity as? AppCompatActivity)?.supportActionBar?.title = "Next launch"
        (activity as? AppCompatActivity)?.supportActionBar?.subtitle = null
        bindUi()

    }

    private fun bindUi() = launch {
        val nextLaunch = viewModel.nextLaunch.await()
        nextLaunch.observe(viewLifecycleOwner, {
            if (it == null) return@observe
            updateMissionImage(it)
            group_loading.visibility = View.GONE
            textView_mission_name.text = it.name
            updateLaunchDate(it)
            textView_mission_details_text.text = it.details

            if (it.payloadsList.isNotEmpty()) {
                initPayloadRecyclerView(it.payloadsList.toPayloadItems())
            }
            contentGroup.visibility = View.VISIBLE
        })
    }

    private fun updateMissionImage(launchEntry: NextLaunchEntry) {
        Glide.with(this)
            .load(
                launchEntry.links?.patch?.small
                    ?: R.drawable.ic_default_spacex_badge
            )
            .into(imageView_mission_badge)
    }

    private fun updateLaunchDate(launchEntry: NextLaunchEntry) {
        // Set as true if the date is "No earlier than"
        val net = launchEntry.net
        // Set as true if date is "To be determined"
        val tbd = launchEntry.tbd
        // Set to one of ("half","quarter","year","month","day","hour")
        val datePrecision: String = launchEntry.datePrecision

        // converting unix timestamp to Date
        val milliseconds = launchEntry.dateUnix.toLong() * 1000
        val date = Date(milliseconds)

        var dateTime: String = if (tbd) {
            "TBD"
        } else {
            when (datePrecision) {
                "half",
                "quarter",
                "year",
                "month" -> DateFormat.getDateInstance(DateFormat.MEDIUM).format(date)
                "day",
                "hour" -> DateFormat.getTimeInstance(DateFormat.SHORT).format(date) + " " +
                        DateFormat.getDateInstance(DateFormat.SHORT).format(date)
                else -> "TBD"
            }
        }

        if (net) {
            dateTime = "No earlier than $dateTime"
        }

        textView_launch_date.text = dateTime
    }

    private fun initPayloadRecyclerView(items: List<PayloadItem>) {
        val groupAdapter = GroupAdapter<ViewHolder>().apply {
            addAll(items)
        }
        recyclerView_payload.apply {
            layoutManager = LinearLayoutManager(this@NextLaunchFragment.context)
            adapter = groupAdapter
        }
    }

    private fun List<Payload>.toPayloadItems(): List<PayloadItem> {
        return this.map {
            PayloadItem(it, unitProvider)
        }
    }
}