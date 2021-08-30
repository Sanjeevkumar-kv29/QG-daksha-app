package com.quickghy.qgdaksha.ui.dash.home

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.databinding.DataBindingUtil
import com.quickghy.qgdaksha.R
import com.quickghy.qgdaksha.databinding.HomeFragmentBinding
import org.koin.android.ext.android.inject

class HomeFragment : Fragment() {

    val viewModel: HomeViewModel by inject()
    lateinit var binding: HomeFragmentBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = DataBindingUtil.inflate(
            inflater,
            R.layout.home_fragment,
            container,
            false
        )

        binding.viewmodel = viewModel

        viewModel.profile.observe(requireActivity(), {
            Log.d("PROFILE_DATA", "$it")
        })

        viewModel.error.observe(requireActivity(), {
            Log.d("PROFILE_DATA", "ERR -- $it")
        })

        return binding.root
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        /*viewModel = ViewModelProvider(this).get(HomeViewModel::class.java)
        // TODO: Use the ViewModel   */

        viewModel.userlogincheck()

    }



}