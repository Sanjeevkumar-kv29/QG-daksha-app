package com.quickghy.qgdaksha

import com.quickghy.qgdaksha.data.PrefDataStore
import com.quickghy.qgdaksha.data.auth.network.AuthMainApis
import com.quickghy.qgdaksha.data.auth.network.NetworkConnectionInterceptor
import com.quickghy.qgdaksha.data.auth.repositories.AuthUserRepository
import com.quickghy.qgdaksha.ui.auth.AuthViewModel
import com.quickghy.qgdaksha.ui.dash.DashViewModel
import com.quickghy.qgdaksha.ui.dash.cart.CartViewModel
import com.quickghy.qgdaksha.ui.dash.home.HomeViewModel
import com.quickghy.qgdaksha.ui.dash.offers.OffersViewModel
import com.quickghy.qgdaksha.util.ApiClient
import org.koin.android.ext.koin.androidApplication
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.core.instance.newInstance
import org.koin.dsl.module

/**
 * @Author: Sanjeev Kumar
 * @Date: 27-07-2021
 */

val appModule = module{

single { NetworkConnectionInterceptor(androidApplication()) }
single { ApiClient(get()).create(AuthMainApis::class.java) }
single { PrefDataStore(get()) }
single { AuthUserRepository(get(), get()) }

}

val viewmodelModule = module {

    viewModel { AuthViewModel(get()) }
    viewModel{ CartViewModel()}
    viewModel{ HomeViewModel()}
    viewModel{ OffersViewModel() }
    viewModel{DashViewModel()}

}
