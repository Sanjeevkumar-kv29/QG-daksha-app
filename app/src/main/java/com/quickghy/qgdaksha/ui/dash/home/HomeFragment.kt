package com.quickghy.qgdaksha.ui.dash.home

import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.lifecycleScope
import com.quickghy.qgdaksha.R
import com.quickghy.qgdaksha.data.PrefDataStore
import com.quickghy.qgdaksha.ui.auth.AuthViewModel
import com.quickghy.qgdaksha.util.toast
import kotlinx.coroutines.launch
import org.koin.android.ext.android.inject
import org.koin.core.component.getScopeName

class HomeFragment : Fragment() {

    companion object {
        fun newInstance() = HomeFragment()
    }

    val viewModel: HomeViewModel by inject()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.home_fragment, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        /*viewModel = ViewModelProvider(this).get(HomeViewModel::class.java)
        // TODO: Use the ViewModel   */


        context?.toast(PrefDataStore(requireContext()).uphoneno.toString())
    }

}