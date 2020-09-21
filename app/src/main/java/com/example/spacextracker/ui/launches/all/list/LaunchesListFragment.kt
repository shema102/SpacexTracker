package com.example.spacextracker.ui.launches.all.list

import android.os.Bundle
import android.text.method.ScrollingMovementMethod
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.ViewModelProvider
import com.example.spacextracker.R
import com.example.spacextracker.ui.base.ScopedFragment
import kotlinx.android.synthetic.main.launches_list_fragment.*
import kotlinx.coroutines.launch
import org.kodein.di.KodeinAware
import org.kodein.di.android.x.closestKodein
import org.kodein.di.generic.instance

class LaunchesListFragment : ScopedFragment(), KodeinAware {
    override val kodein by closestKodein()

    private val viewModelFactory: LaunchesListViewModelFactory by instance()

    private lateinit var viewModel: LaunchesListViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.launches_list_fragment, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewModel =
            ViewModelProvider(this, viewModelFactory)
                .get(LaunchesListViewModel::class.java)

        launches_list.movementMethod = ScrollingMovementMethod()
        bindUi()
    }

    private fun bindUi() = launch {
        val launches = viewModel.launches.await()
        launches.observe(viewLifecycleOwner, {
            if (it == null) return@observe
            launches_list.text = it.map { item -> item.links?.youtubeId ?: "None" }
                .toString()
        })
    }
}