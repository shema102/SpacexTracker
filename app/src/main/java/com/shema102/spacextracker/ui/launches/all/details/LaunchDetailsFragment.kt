package com.shema102.spacextracker.ui.launches.all.details

import android.content.ActivityNotFoundException
import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.bumptech.glide.Glide
import com.shema102.spacextracker.R
import com.shema102.spacextracker.data.db.entity.LaunchEntry
import com.shema102.spacextracker.data.db.entity.Payload
import com.shema102.spacextracker.data.provider.UnitProvider
import com.shema102.spacextracker.di.factory.LaunchDetailsViewModelFactory
import com.shema102.spacextracker.internal.IdNotFoundException
import com.shema102.spacextracker.internal.makeVisible
import com.shema102.spacextracker.ui.base.ScopedFragment
import com.shema102.spacextracker.ui.launches.common.PayloadItem
import com.xwray.groupie.GroupAdapter
import com.xwray.groupie.ViewHolder
import dagger.android.support.AndroidSupportInjection
import kotlinx.android.synthetic.main.launch_details_fragment.*
import kotlinx.coroutines.launch
import java.text.DateFormat
import java.util.*
import javax.inject.Inject
import javax.inject.Qualifier


class LaunchDetailsFragment : ScopedFragment() {

    private lateinit var viewModel: LaunchDetailsViewModel

    @Inject
    lateinit var unitProvider: UnitProvider

    @Inject
    lateinit var viewModelFactory: LaunchDetailsViewModelFactory

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.launch_details_fragment, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        val safeArgs = arguments?.let {
            LaunchDetailsFragmentArgs.fromBundle(it)
        }
        val launchId = safeArgs?.idString ?: throw IdNotFoundException()

        AndroidSupportInjection.inject(this)

        viewModel = ViewModelProvider(this, viewModelFactory).get(
            LaunchDetailsViewModel::class.java
        )

        viewModel.launchId = launchId

        bindUi()
    }

    private fun bindUi() = launch {
        val launch = viewModel.launch.await()
        launch.observe(viewLifecycleOwner, {
            if (it == null) return@observe
            updateMissionImage(it)
            group_loading.visibility = View.GONE
            textView_mission_name.text = it.name
            updateLaunchDate(it)
            textView_mission_details_text.text = it.details ?: context?.getString(R.string.no_info)

            if (it.payloadsList.isNotEmpty()) {
                initPayloadRecyclerView(it.payloadsList.toPayloadItems())
            }
            contentGroup.visibility = View.VISIBLE

            updateLinks(it)
        })
    }

    private fun updateMissionImage(launchEntry: LaunchEntry) {
        Glide.with(this)
            .load(
                launchEntry.links?.patch?.small
                    ?: R.drawable.ic_default_spacex_badge
            )
            .into(imageView_mission_badge)
    }

    private fun updateLaunchDate(launchEntry: LaunchEntry) {
        // Set as true if the date is "No earlier than"
//        val net = launchEntry.net
        // Set as true if date is "To be determined"
        val tbd = launchEntry.tbd
        // Set to one of ("half","quarter","year","month","day","hour")
        val datePrecision: String = launchEntry.datePrecision

        // converting unix timestamp to Date
        val milliseconds = launchEntry.dateUnix.toLong() * 1000
        val date = Date(milliseconds)

        val dateTime: String = if (tbd) {
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

//        if (net) {
//            dateTime = "No earlier than $dateTime"
//        }

        textView_launch_date.text = dateTime
    }

    private fun initPayloadRecyclerView(items: List<PayloadItem>) {
        val groupAdapter = GroupAdapter<ViewHolder>().apply {
            addAll(items)
        }
        recyclerView_payload.apply {
            layoutManager = LinearLayoutManager(this@LaunchDetailsFragment.context)
            adapter = groupAdapter
        }
    }

    private fun List<Payload>.toPayloadItems(): List<PayloadItem> {
        return this.map {
            PayloadItem(it, unitProvider, context)
        }
    }

    private fun updateLinks(launch: LaunchEntry) {
        val links = launch.links ?: return
        var linksNumber = 0

        val wikiLink: String? = links.wikipedia
        if (wikiLink != null) {
            bindLinkToButton(button_launch_links_wiki, wikiLink)
            button_launch_links_wiki.makeVisible()
            linksNumber++
        }

        val youtubeId: String? = links.youtubeId
        if (youtubeId != null) {
            val youtubeLink = "https://www.youtube.com/watch?v=$youtubeId"
            bindLinkToButton(button_launch_links_youtube, youtubeLink)
            button_launch_links_youtube.makeVisible()
            linksNumber++
        }

        val webcast: String? = links.webcast
        if (webcast != null) {
            bindLinkToButton(button_launch_links_webcast, webcast)
            button_launch_links_webcast.makeVisible()
            linksNumber++
        }

        val reddit: String? = links.reddit?.campaign
        if (reddit != null) {
            bindLinkToButton(button_launch_links_reddit, reddit)
            button_launch_links_reddit.makeVisible()
            linksNumber++
        }

        val article: String? = links.article
        if (article != null) {
            bindLinkToButton(button_launch_links_article, article)
            button_launch_links_article.makeVisible()
            linksNumber++
        }


        // if all links missing left links group GONE
        if (linksNumber > 0) {
            group_launch_links.makeVisible()
        }
    }

    private fun bindLinkToButton(button: Button, link: String) {
        button.setOnClickListener {
            val intent = Intent(
                Intent.ACTION_VIEW,
                Uri.parse(link)
            )
            try {
                startActivity(intent)
            } catch (e: ActivityNotFoundException) {
            }
        }
    }
}