package com.quickghy.qgdaksha.ui.dash.profile

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.lifecycle.viewModelScope
import com.quickghy.qgdaksha.R
import com.quickghy.qgdaksha.databinding.FragmentProfileBinding
import com.quickghy.qgdaksha.ui.dash.DashStateListener
import kotlinx.coroutines.launch
import org.koin.android.ext.android.inject


class ProfileFragment : Fragment(){

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

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewModel.profiletoken.observe(requireActivity()){
            viewModel.viewModelScope.launch {
                if (it.isNullOrEmpty()){

                    binding.llIfSigned1.visibility = View.GONE
                    binding.llIfUnsigned . visibility = View.VISIBLE
                }
                else{

                    binding.llIfSigned1.visibility = View.VISIBLE
                    binding.llIfUnsigned . visibility = View.GONE
                }
            }

            viewModel.getprofile.observe(requireActivity()) {
                if(it!=null) {
                    binding.profileName.text = it.data.name
                    binding.profileEmail.text = it.data.email
                    binding.profileMobile.text = it.data.phoneNo
                }
            }

        }

        binding.getaddress.setOnClickListener {
            startActivity(Intent(requireContext(),MapMainActivity::class.java))
        }
    }
}