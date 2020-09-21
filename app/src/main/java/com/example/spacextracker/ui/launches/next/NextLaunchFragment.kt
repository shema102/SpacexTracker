package com.example.spacextracker.ui.launches.next

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.ViewModelProvider
import com.example.spacextracker.R
import com.example.spacextracker.ui.base.ScopedFragment
import kotlinx.android.synthetic.main.next_launch_fragment.*
import kotlinx.coroutines.launch
import org.kodein.di.KodeinAware
import org.kodein.di.android.x.closestKodein
import org.kodein.di.generic.instance
import java.text.SimpleDateFormat
import java.util.Date

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
        val nextLaunch = viewModel.nextLaunch.await()
        nextLaunch.observe(viewLifecycleOwner, {
            if (it == null) return@observe

            group_loading.visibility = View.GONE
            content.visibility = View.VISIBLE
            mission_name.text = it.name
            mission_details.text = it.details
            launch_date.text = timeFromTimestamp(it.dateUnix)
            payload_name.text = it.payloadsList[0].toString()
        })
    }

    private fun timeFromTimestamp(timestamp: Int): String{
        val milis = timestamp.toLong() * 1000
        val date = Date(milis)
        return SimpleDateFormat("hh:mm MM.dd.yyyy").format(date)
    }
}