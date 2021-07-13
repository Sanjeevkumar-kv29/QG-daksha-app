package com.quickghy.qgdaksha.ui.dash

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


class ProfileFragment : Fragment() {

    companion object {
        fun newInstance() = ProfileFragment()
    }

    lateinit var viewModel: AuthViewModel
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

        //so that it calls the viewmodel owned by the parent activity
        activity.let {
            viewModel = ViewModelProvider(it!!).get(AuthViewModel::class.java)
        }
        // Inflate the layout for this fragment
        return binding.root
    }

}