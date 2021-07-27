package com.quickghy.qgdaksha.ui.dash.profile

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.quickghy.qgdaksha.R
import com.quickghy.qgdaksha.databinding.FragmentProfileBinding
import com.quickghy.qgdaksha.ui.auth.AuthViewModel
import com.quickghy.qgdaksha.ui.dash.home.HomeViewModel
import org.koin.android.ext.android.inject


class ProfileFragment : Fragment() {

    companion object {
        fun newInstance() = ProfileFragment()
    }

    val viewModel: AuthViewModel by inject()
    lateinit var binding: FragmentProfileBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {

        binding = DataBindingUtil.inflate(
            inflater,
            R.layout.fragment_profile,
            container,
            false
        )


        return binding.root
    }

}