package com.creatifdev.koin

import android.app.Application
import androidx.multidex.MultiDex
import com.creatifdev.koin.module.NetworkModule

import com.creatifdev.koin.module.PostModule
import org.koin.android.ext.koin.androidContext
import org.koin.android.ext.koin.androidLogger
import org.koin.core.context.startKoin


class MainApplication : Application() {

    private val TAG = MainApplication::class.java.simpleName

    override fun onCreate() {
        super.onCreate()
        MultiDex.install(this)

        startKoin {
            androidLogger()
            androidContext(this@MainApplication)
            modules(listOf(PostModule, NetworkModule))
        }

    }
}