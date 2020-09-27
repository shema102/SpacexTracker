package com.shema102.spacextracker.ui.launches.all.details

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.shema102.spacextracker.R
import com.shema102.spacextracker.internal.IdNotFoundException
import com.shema102.spacextracker.ui.base.ScopedFragment
import kotlinx.android.synthetic.main.launch_details_fragment.*
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import org.kodein.di.KodeinAware
import org.kodein.di.android.x.closestKodein
import org.kodein.di.generic.factory
import org.kodein.di.generic.instance

class LaunchDetailsFragment : ScopedFragment(), KodeinAware {
    override val kodein by closestKodein()

    private val viewModelFactoryInstanceFactory
            : ((String) -> LaunchDetailsViewModelFactory) by factory()

    private lateinit var viewModel: LaunchDetailsViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.launch_details_fragment, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        val safeArgs = arguments?.let {
            LaunchDetailsFragmentArgs.fromBundle(it)
        }
        val launchId: String = safeArgs?.idString ?: throw IdNotFoundException()

        viewModel = ViewModelProvider(this, viewModelFactoryInstanceFactory(launchId)).get(LaunchDetailsViewModel::class.java)

        (activity as? AppCompatActivity)?.supportActionBar?.title = "Launch details"
        (activity as? AppCompatActivity)?.supportActionBar?.subtitle = null

        bindUi()
    }

    fun bindUi()= launch(Dispatchers.Main) {
        val launch = viewModel.launch.await()

        launch.observe(viewLifecycleOwner, {
            if (it == null) return@observe

            text.text = it.name
        })
    }
}