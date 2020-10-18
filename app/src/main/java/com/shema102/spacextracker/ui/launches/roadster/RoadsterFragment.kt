package com.shema102.spacextracker.ui.launches.roadster


import android.content.ActivityNotFoundException
import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.text.HtmlCompat
import androidx.lifecycle.ViewModelProvider
import com.bumptech.glide.Glide
import com.shema102.spacextracker.R
import com.shema102.spacextracker.data.db.unitlocalized.UnitSpecificRoadster
import com.shema102.spacextracker.data.provider.UnitProvider
import com.shema102.spacextracker.internal.UnitSystem
import com.shema102.spacextracker.internal.toBoldHtml
import com.shema102.spacextracker.ui.base.ScopedFragment
import kotlinx.android.synthetic.main.roadster_fragment.*
import kotlinx.coroutines.launch
import org.kodein.di.KodeinAware
import org.kodein.di.android.x.closestKodein
import org.kodein.di.generic.instance
import kotlin.math.roundToInt

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

        bindUi()
    }

    private fun bindUi() = launch {
        val roadster = viewModel.roadster.await()
        roadster.observe(viewLifecycleOwner, {
            group_loading.visibility = View.GONE
            if (it == null) return@observe
            updateRoadsterImage(it)
            updateRoadsterDetails(it)
            updateDistances(it)
            updateLinks(it)
            contentGroup.visibility = View.VISIBLE
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
            if (unitProvider.getUnitSystem() == UnitSystem.METRIC) context?.getString(R.string.km)
                ?: "Km"
            else context?.getString(R.string.mi) ?: "Mi"

        val marsDistance = roadster.marsDistance.roundToInt()
        val earthDistance = roadster.earthDistance.roundToInt()

        textView_roadster_to_mars_distance.text = HtmlCompat.fromHtml(
            "${
                context?.getString(R.string.mars_distance)?.toBoldHtml()
            }: $marsDistance $unitEnding",
            HtmlCompat.FROM_HTML_MODE_LEGACY
        )

        textView_roadster_to_earth_distance.text = HtmlCompat.fromHtml(
            "${
                context?.getString(R.string.earth_distance)?.toBoldHtml()
            } $earthDistance $unitEnding",
            HtmlCompat.FROM_HTML_MODE_LEGACY
        )
    }

    private fun updateLinks(roadster: UnitSpecificRoadster) {
        val wikiLink: String = roadster.wikipedia
        val youtubeLink: String = roadster.video

        button_roadster_links_wiki.setOnClickListener {
            val intent = Intent(
                Intent.ACTION_VIEW,
                Uri.parse(wikiLink)
            )
            try {
                startActivity(intent)
            } catch (e: ActivityNotFoundException) {
            }
        }

        button_roadster_links_youtube.setOnClickListener {
            val intent = Intent(
                Intent.ACTION_VIEW,
                Uri.parse(youtubeLink)
            )
            try {
                startActivity(intent)
            } catch (e: ActivityNotFoundException) {
            }
        }

    }


}