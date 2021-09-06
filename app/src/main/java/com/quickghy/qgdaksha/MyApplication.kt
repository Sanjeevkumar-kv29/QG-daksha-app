package com.quickghy.qgdaksha

import android.app.Application
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.startKoin

/**
 * @Author: Sanjeev Kumar
 * @Date: 27-07-2021
 */

class MyApplication : Application(){

    override fun onCreate() {
        super.onCreate()

        startKoin {
            androidContext(this@MyApplication)
            modules(listOf(appModule, viewmodelModule))
        }

    }



}