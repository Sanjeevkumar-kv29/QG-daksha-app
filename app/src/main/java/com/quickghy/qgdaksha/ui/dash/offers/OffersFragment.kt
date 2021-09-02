package com.quickghy.qgdaksha.ui.dash.offers

import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.quickghy.qgdaksha.R
import com.quickghy.qgdaksha.ui.dash.home.HomeViewModel
import org.koin.android.ext.android.inject

class OffersFragment : Fragment() {

    companion object {
        fun newInstance() = OffersFragment()
    }

    val viewModel: OffersViewModel by inject()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.notif_fragment, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

    }

}