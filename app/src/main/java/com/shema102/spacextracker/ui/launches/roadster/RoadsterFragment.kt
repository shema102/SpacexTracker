package com.shema102.spacextracker.ui.launches.roadster


import android.content.ActivityNotFoundException
import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.text.HtmlCompat
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.viewModels
import com.shema102.spacextracker.R
import com.shema102.spacextracker.data.db.unitlocalized.UnitSpecificRoadster
import com.shema102.spacextracker.data.provider.UnitProvider
import com.shema102.spacextracker.databinding.RoadsterFragmentBinding
import com.shema102.spacextracker.di.factory.RoadsterViewModelFactory
import com.shema102.spacextracker.internal.UnitSystem
import com.shema102.spacextracker.internal.toBoldHtml
import com.shema102.spacextracker.ui.base.ScopedFragment
import dagger.android.support.AndroidSupportInjection
import kotlinx.android.synthetic.main.roadster_fragment.*
import kotlinx.coroutines.launch
import javax.inject.Inject
import kotlin.math.roundToInt

class RoadsterFragment : ScopedFragment() {

    @Inject
    lateinit var viewModelFactory: RoadsterViewModelFactory

    private val viewModel: RoadsterViewModel by viewModels { viewModelFactory }

    @Inject
    lateinit var unitProvider: UnitProvider

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {

        val binding: RoadsterFragmentBinding = DataBindingUtil.inflate(
            inflater, R.layout.roadster_fragment, container, false
        )

        AndroidSupportInjection.inject(this)

        binding.viewModel = viewModel
        binding.lifecycleOwner = this.viewLifecycleOwner

        return binding.root
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        bindUi()
    }


    private fun bindUi() = launch {
        val roadster = viewModel.roadster
        roadster.observe(viewLifecycleOwner, {
            group_loading.visibility = View.GONE
            if (it == null) return@observe
            updateDistances(it)
            updateLinks(it)
            contentGroup.visibility = View.VISIBLE
        })
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