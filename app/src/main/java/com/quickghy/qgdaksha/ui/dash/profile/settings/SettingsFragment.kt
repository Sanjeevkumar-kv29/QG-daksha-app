package com.quickghy.qgdaksha.ui.dash.profile.settings

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.quickghy.qgdaksha.R
import com.quickghy.qgdaksha.databinding.SettingsFragmentBinding

class SettingsFragment : Fragment() {
    companion object {
        fun newInstance() = SettingsFragment()
    }

    private lateinit var viewModel: SettingsViewModel
    lateinit var binding: SettingsFragmentBinding
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {

        binding = DataBindingUtil.inflate(
            inflater,
            R.layout.settings_fragment,
            container,
            false
        )
        return binding.root
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewModel = ViewModelProvider(this).get(SettingsViewModel::class.java)

        viewModel.offer.observe(requireActivity()) {
            binding.settingSwitchOffer.isChecked = it
        }
        viewModel.push.observe(requireActivity()) {
            binding.settingSwitchPush.isChecked = it
        }
        viewModel.textNotif.observe(requireActivity()) {
            binding.settingSwitchText.isChecked = it
        }
        viewModel.mail.observe(requireActivity()) {
            binding.settingSwitchMail.isChecked = it
        }
        viewModel.initializeSettings()
    }

}