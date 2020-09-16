package com.example.spacextracker.ui.launches.next

import androidx.lifecycle.ViewModelProviders
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.spacextracker.R

class NextLaunchFragment : Fragment() {

    companion object {
        fun newInstance() = NextLaunchFragment()
    }

    private lateinit var viewModel: NextLaunchViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.next_launch_fragment, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewModel = ViewModelProviders.of(this).get(NextLaunchViewModel::class.java)
        // TODO: Use the ViewModel
    }

}