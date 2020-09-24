package com.shema102.spacextracker.ui.launches.roadster


import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.shema102.spacextracker.R
import com.shema102.spacextracker.data.network.ConnectivityInterceptorImpl
import com.shema102.spacextracker.data.network.SpacexApiService
import com.shema102.spacextracker.data.network.SpacexNetworkDataSourceImpl
import com.shema102.spacextracker.ui.base.ScopedFragment
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
            if (it == null) return@observe
            roadster_text.text = it.toString()
        })
    }

}