package com.quickghy.qgdaksha

import com.quickghy.qgdaksha.data.NetworkConnectionInterceptor
import com.quickghy.qgdaksha.data.PrefDataStore
import com.quickghy.qgdaksha.data.SettingDataStore
import com.quickghy.qgdaksha.data.auth.network.AuthMainApis
import com.quickghy.qgdaksha.data.auth.repositories.AuthUserRepository
import com.quickghy.qgdaksha.data.dash.home.repositories.HomeRepository
import com.quickghy.qgdaksha.data.dash.profile.network.ProfileApi
import com.quickghy.qgdaksha.data.dash.profile.network.SettingsApis
import com.quickghy.qgdaksha.data.dash.profile.repositories.ProfileRepository
import com.quickghy.qgdaksha.data.dash.profile.repositories.SettingsRepository
import com.quickghy.qgdaksha.data.map.network.DirectionsApi
import com.quickghy.qgdaksha.ui.auth.AuthViewModel
import com.quickghy.qgdaksha.ui.dash.DashViewModel
import com.quickghy.qgdaksha.ui.dash.cart.CartViewModel
import com.quickghy.qgdaksha.ui.dash.home.HomeViewModel
import com.quickghy.qgdaksha.ui.dash.offers.OffersViewModel
import com.quickghy.qgdaksha.ui.dash.profile.DashProfileViewModel
import com.quickghy.qgdaksha.ui.dash.profile.settings.SettingsViewModel
import com.quickghy.qgdaksha.ui.map.MapRepository
import com.quickghy.qgdaksha.ui.map.MapViewModel
import com.quickghy.qgdaksha.util.ApiClient
import org.koin.android.ext.koin.androidApplication
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

/**
 * @Author: Sanjeev Kumar
 * @Date: 27-07-2021
 */

val appModule = module {

    single { NetworkConnectionInterceptor(androidApplication()) }

    single { ApiClient(get()).create(AuthMainApis::class.java) }
    single { ApiClient(get()).create(ProfileApi::class.java) }
    single { ApiClient(get()).create(SettingsApis::class.java) }
    single { ApiClient(get()).create(DirectionsApi::class.java) }

    single { PrefDataStore(get()) }
    single { SettingDataStore(get()) }

    single { AuthUserRepository(get(), get(), get(), get()) }
    single { HomeRepository(get(), get(), get()) }
    single { ProfileRepository(get(), get()) }
    single { SettingsRepository(get()) }
    single { MapRepository(get()) }


}

val viewmodelModule = module {

    viewModel { AuthViewModel(get()) }
    viewModel { CartViewModel() }
    viewModel { HomeViewModel(get()) }
    viewModel { OffersViewModel() }
    viewModel { DashViewModel() }
    viewModel { DashProfileViewModel(get()) }
    viewModel { SettingsViewModel(get(), get()) }

    viewModel { MapViewModel(get(), get()) }

}
