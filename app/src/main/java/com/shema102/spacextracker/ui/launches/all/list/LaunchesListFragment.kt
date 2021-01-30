package com.shema102.spacextracker.ui.launches.all.list

import android.os.Bundle
import com.xwray.groupie.kotlinandroidextensions.ViewHolder
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.navigation.Navigation
import androidx.recyclerview.widget.LinearLayoutManager
import com.shema102.spacextracker.R
import com.shema102.spacextracker.data.db.entity.LaunchEntry
import com.shema102.spacextracker.di.factory.LaunchesListViewModelFactory
import com.shema102.spacextracker.ui.base.ScopedFragment
import com.xwray.groupie.GroupAdapter
import dagger.android.support.AndroidSupportInjection
import kotlinx.android.synthetic.main.launches_list_fragment.*
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import javax.inject.Inject

class LaunchesListFragment : ScopedFragment() {

    @Inject
    lateinit var viewModelFactory: LaunchesListViewModelFactory

    private val viewModel: LaunchesListViewModel by viewModels { viewModelFactory }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.launches_list_fragment, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        AndroidSupportInjection.inject(this)

        bindUi()
    }

    private fun bindUi() = launch(Dispatchers.Main) {
        val launches = viewModel.launches.await()
        launches.observe(viewLifecycleOwner, { launchEntries ->
            if (launchEntries == null) return@observe

            group_loading.visibility = View.GONE

            initRecyclerView(launchEntries.toLaunchItems())
        })
    }

    private fun List<LaunchEntry>.toLaunchItems(): List<LaunchItem> {
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
        groupAdapter.setOnItemClickListener { item, view ->
            (item as? LaunchItem)?.let {
                showLaunchDetails(it.launchEntry.id, view)
            }
        }
    }

    private fun showLaunchDetails(id: String, view: View) {
        val actionDetail = LaunchesListFragmentDirections.actionDetail(id)
        Navigation.findNavController(view).navigate(actionDetail)
    }
}