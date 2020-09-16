package com.example.spacextracker.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.example.spacextracker.R
import com.example.spacextracker.model.Launch
import com.example.spacextracker.model.SpacexApiService
import kotlinx.android.synthetic.main.fragment_next_launch.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class NextLaunchFragment : Fragment() {
    private val mApiService = SpacexApiService.create()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_next_launch, container, false)
    }

    override fun onResume() {
        super.onResume()

        mApiService.getNextLaunch()
            .enqueue(object : Callback<Launch> {
                override fun onResponse(call: Call<Launch>, response: Response<Launch>) {
                    val launch: Launch? = response.body()

                    if (launch != null) {
                        next_launch_text.text = launch.name
                    }
                }

                override fun onFailure(call: Call<Launch>, t: Throwable) {
                    next_launch_text.text = "Error"
                    t.printStackTrace()
                }
            })
    }
}