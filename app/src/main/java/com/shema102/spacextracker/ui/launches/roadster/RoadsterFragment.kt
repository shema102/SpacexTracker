package com.shema102.spacextracker.ui.launches.roadster


import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AppCompatActivity
import androidx.core.text.HtmlCompat
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.bumptech.glide.Glide
import com.shema102.spacextracker.R
import com.shema102.spacextracker.data.db.entity.NextLaunch
import com.shema102.spacextracker.data.db.entity.RoadsterEntry
import com.shema102.spacextracker.data.db.unitlocalized.UnitSpecificRoadster
import com.shema102.spacextracker.data.network.ConnectivityInterceptorImpl
import com.shema102.spacextracker.data.network.SpacexApiService
import com.shema102.spacextracker.data.network.SpacexNetworkDataSourceImpl
import com.shema102.spacextracker.data.provider.UnitProvider
import com.shema102.spacextracker.internal.UnitSystem
import com.shema102.spacextracker.ui.base.ScopedFragment
import kotlinx.android.synthetic.main.launch_details_fragment.*
import kotlinx.android.synthetic.main.payload_details_item.*
import kotlinx.android.synthetic.main.roadster_fragment.*
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import org.kodein.di.Kodein
import org.kodein.di.KodeinAware
import org.kodein.di.android.x.closestKodein
import org.kodein.di.generic.instance

class RoadsterFragment : ScopedFragment(), KodeinAware {
    override val kodein by closestKodein()

    private val viewModelFactory: RoadsterViewModelFactory by instance()

    private lateinit var viewModel: RoadsterViewModel

    private val unitProvider: UnitProvider by instance()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.roadster_fragment, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewModel = ViewModelProvider(this, viewModelFactory)
            .get(RoadsterViewModel::class.java)

        (activity as? AppCompatActivity)?.supportActionBar?.title = "Musk's Roadster"
        (activity as? AppCompatActivity)?.supportActionBar?.subtitle = null

        bindUi()
    }

    private fun bindUi() = launch {
        val roadster = viewModel.roadster.await()
        roadster.observe(viewLifecycleOwner, {
            if (it == null) return@observe
            updateRoadsterImage(it)
            updateRoadsterDetails(it)
            updateDistances(it)
            updateLinks(it)
        })
    }

    private fun updateRoadsterImage(roadster: UnitSpecificRoadster) {
        Glide.with(this)
            .load(
                roadster.images.random()
            )
            .into(imageView_roadster_banner)
    }

    private fun updateRoadsterDetails(roadster: UnitSpecificRoadster) {
        textView_roadster_details.text = roadster.details
    }

    private fun updateDistances(roadster: UnitSpecificRoadster) {
        val unitEnding: String =
            if (unitProvider.getUnitSystem() == UnitSystem.METRIC) "Km"
            else "Mi"

        val marsDistance = roadster.marsDistance
        val earthDistance = roadster.earthDistance

        textView_roadster_to_mars_distance.text = HtmlCompat.fromHtml(
            "<b>Mars distance:</b> $marsDistance $unitEnding",
            HtmlCompat.FROM_HTML_MODE_LEGACY
        )

        textView_roadster_to_earth_distance.text = HtmlCompat.fromHtml(
            "<b>Mars distance:</b> $earthDistance $unitEnding",
            HtmlCompat.FROM_HTML_MODE_LEGACY
        )
    }

    private fun updateLinks(roadster: UnitSpecificRoadster) {
        val wikiLink: String = roadster.wikipedia
        val youtubeLink: String = roadster.video
        textView_roadster_links_wiki.text = HtmlCompat.fromHtml(
                "<a href=\"$wikiLink\">Wikipedia</a>",
                HtmlCompat.FROM_HTML_MODE_LEGACY
            )

        textView_roadster_links_youtube.text = HtmlCompat.fromHtml(
                "<a href=\"$youtubeLink\">YouTube</a>",
                HtmlCompat.FROM_HTML_MODE_LEGACY
            )

    }


}