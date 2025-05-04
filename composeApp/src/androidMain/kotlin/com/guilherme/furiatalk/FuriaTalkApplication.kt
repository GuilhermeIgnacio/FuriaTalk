package com.guilherme.furiatalk

import android.app.Application
import com.guilherme.furiatalk.di.initKoin
import org.koin.android.ext.koin.androidContext

class FuriaTalkApplication: Application() {

    override fun onCreate() {
        super.onCreate()
        initKoin {
            androidContext(this@FuriaTalkApplication)
        }
    }

}