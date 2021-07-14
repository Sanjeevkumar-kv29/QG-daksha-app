package com.quickghy.qgdaksha.ui.dash.offers

import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.quickghy.qgdaksha.R

class OffersFragment : Fragment() {

    companion object {
        fun newInstance() = OffersFragment()
    }

    private lateinit var viewModel: OffersViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.notif_fragment, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewModel = ViewModelProvider(this).get(OffersViewModel::class.java)
        // TODO: Use the ViewModel
    }

}