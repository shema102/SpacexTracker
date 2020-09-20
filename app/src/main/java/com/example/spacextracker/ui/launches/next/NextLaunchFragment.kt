package com.example.spacextracker.ui.launches.next

import androidx.lifecycle.ViewModelProviders
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.example.spacextracker.R
import com.example.spacextracker.data.network.ConnectivityInterceptorImpl
import com.example.spacextracker.data.network.SpacexApiService
import com.example.spacextracker.data.network.SpacexNetworkDataSourceImpl
import com.example.spacextracker.ui.base.ScopedFragment
import com.example.spacextracker.ui.launches.roadster.RoadsterViewModelFactory
import kotlinx.android.synthetic.main.next_launch_fragment.*
import kotlinx.android.synthetic.main.roadster_fragment.*
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import org.kodein.di.Kodein
import org.kodein.di.KodeinAware
import org.kodein.di.android.x.closestKodein
import org.kodein.di.generic.instance

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
        val roadster = viewModel.nextLaunch.await()
        roadster.observe(viewLifecycleOwner, {
            if (it == null) return@observe
            next_launch.text = it.toString()
        })
    }
}