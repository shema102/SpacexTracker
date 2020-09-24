package com.shema102.spacextracker.ui.launches.all.list

import android.os.Bundle
import com.xwray.groupie.kotlinandroidextensions.ViewHolder
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.shema102.spacextracker.R
import com.shema102.spacextracker.data.db.entity.LaunchEntry
import com.shema102.spacextracker.ui.base.ScopedFragment
import com.xwray.groupie.GroupAdapter
import kotlinx.android.synthetic.main.launches_list_fragment.*
import kotlinx.coroutines.Dispatchers
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

        (activity as? AppCompatActivity)?.supportActionBar?.title = "All launches"
        (activity as? AppCompatActivity)?.supportActionBar?.subtitle = null

        bindUi()
    }

    private fun bindUi() = launch(Dispatchers.Main) {
        val launches = viewModel.launches.await()
        launches.observe(viewLifecycleOwner, {launchEntries ->
            if (launchEntries == null) return@observe

            group_loading.visibility = View.GONE

            initRecyclerView(launchEntries.toLaunchItems())
        })
    }

    private fun List<LaunchEntry>.toLaunchItems(): List<LaunchItem>{
        return this.map {
            LaunchItem(it)
        }
    }

    private fun initRecyclerView(items: List<LaunchItem>) {
        val groupAdapter = GroupAdapter<ViewHolder>().apply {
            addAll(items)
        }
        recyclerView.apply {
            layoutManager = LinearLayoutManager(this@LaunchesListFragment.context)
            adapter = groupAdapter
        }
//        groupAdapter.setOnItemClickListener { item, view ->
//            (item as? LaunchEntry)?.let {
//
//            }
//        }
    }
}