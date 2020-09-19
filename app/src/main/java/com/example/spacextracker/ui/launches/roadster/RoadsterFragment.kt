package com.example.spacextracker.ui.launches.roadster

import androidx.lifecycle.ViewModelProviders
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.Observer
import com.example.spacextracker.R
import com.example.spacextracker.data.network.ConnectivityInterceptorImpl
import com.example.spacextracker.data.network.SpacexApiService
import com.example.spacextracker.data.network.SpacexNetworkDataSourceImpl
import kotlinx.android.synthetic.main.roadster_fragment.*
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch

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


        val apiService = SpacexApiService(ConnectivityInterceptorImpl(this.requireContext()))
        val spacexNetworkDataSource = SpacexNetworkDataSourceImpl(apiService)

        spacexNetworkDataSource.downloadedRoadsterEntry.observe(viewLifecycleOwner, Observer { roadster_text.text = it.toString() })

        GlobalScope.launch(Dispatchers.Main) {
            spacexNetworkDataSource.fetchRoadster()
        }
    }

}