package com.quickghy.qgdaksha

import com.quickghy.qgdaksha.data.PrefDataStore
import com.quickghy.qgdaksha.data.auth.network.MainApis
import com.quickghy.qgdaksha.data.auth.repositories.AuthUserRepository
import com.quickghy.qgdaksha.ui.auth.AuthViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val appModule = module{

single { MainApis() }
single { PrefDataStore(get()) }
single { AuthUserRepository(get(),get()) }

}

val viewmodelModule = module {

    viewModel { AuthViewModel(get()) }
}
