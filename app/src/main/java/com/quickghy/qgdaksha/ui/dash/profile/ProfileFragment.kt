package com.quickghy.qgdaksha.ui.dash.profile

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import com.quickghy.qgdaksha.R
import com.quickghy.qgdaksha.databinding.FragmentProfileBinding
import com.quickghy.qgdaksha.ui.dash.DashStateListener
import org.koin.android.ext.android.inject


class ProfileFragment : Fragment(), DashStateListener.IsUserLoggedin {

    companion object {
        fun newInstance() = ProfileFragment()
    }

    val viewModel: DashProfileViewModel by inject()
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

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        viewModel.Loginstatus()
    }

    override fun yes() {
        binding.llIfSigned1.visibility = View.VISIBLE
        binding.llIfUnsigned . visibility = View.GONE
    }

    override fun no() {

        binding.llIfSigned1.visibility = View.GONE
        binding.llIfUnsigned . visibility = View.VISIBLE
    }


}