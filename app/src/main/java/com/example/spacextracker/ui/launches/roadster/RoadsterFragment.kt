package com.example.spacextracker.ui.launches.roadster

import androidx.lifecycle.ViewModelProviders
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.spacextracker.R

class RoadsterFragment : Fragment() {

    companion object {
        fun newInstance() = RoadsterFragment()
    }

    private lateinit var viewModel: RoadsterViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.roadster_fragment, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewModel = ViewModelProviders.of(this).get(RoadsterViewModel::class.java)
        // TODO: Use the ViewModel
    }

}