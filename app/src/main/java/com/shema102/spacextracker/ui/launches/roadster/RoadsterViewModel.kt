package com.shema102.spacextracker.ui.launches.roadster

import android.widget.ImageView
import androidx.databinding.BindingAdapter
import androidx.lifecycle.LiveData
import androidx.lifecycle.Transformations
import androidx.lifecycle.ViewModel
import com.bumptech.glide.Glide
import com.shema102.spacextracker.data.db.unitlocalized.UnitSpecificRoadster
import com.shema102.spacextracker.data.provider.UnitProvider
import com.shema102.spacextracker.data.repository.SpacexRepository
import com.shema102.spacextracker.internal.UnitSystem
import kotlinx.coroutines.runBlocking

class RoadsterViewModel(
    private val spacexRepository: SpacexRepository,
    unitProvider: UnitProvider
) : ViewModel() {
    private val unitSystem = unitProvider.getUnitSystem()

    private val isMetric: Boolean
        get() = unitSystem == UnitSystem.METRIC


    val roadster: LiveData<out UnitSpecificRoadster?>
        get() = runBlocking{ spacexRepository.getRoadster(isMetric) }

    val roadsterDetails = Transformations.map(roadster){ it?.details }

    val roadsterImages = Transformations.map(roadster){ it?.images }

    companion object{
        @JvmStatic
        @BindingAdapter("imageUrl")
        fun loadImage(view: ImageView, imageUrl: List<String>?){
            if (imageUrl != null) {
                Glide.with(view.context)
                    .load(
                        imageUrl.random()
                    )
                    .into(view)
            }
        }
    }
}