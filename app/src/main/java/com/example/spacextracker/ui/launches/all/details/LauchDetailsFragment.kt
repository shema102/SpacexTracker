package com.example.spacextracker.ui.launches.all.details

import androidx.lifecycle.ViewModelProviders
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.spacextracker.R

class LauchDetailsFragment : Fragment() {

    companion object {
        fun newInstance() = LauchDetailsFragment()
    }

    private lateinit var viewModel: LauchDetailsViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.launch_details_fragment, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewModel = ViewModelProviders.of(this).get(LauchDetailsViewModel::class.java)
        // TODO: Use the ViewModel
    }

}