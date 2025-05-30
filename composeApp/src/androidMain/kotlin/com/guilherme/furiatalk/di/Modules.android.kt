package com.guilherme.furiatalk.di

import com.guilherme.furiatalk.chat.data.local.DriverFactory
import org.koin.android.ext.koin.androidContext
import org.koin.core.module.Module
import org.koin.dsl.module

actual val platformModule: Module
    get() = module {
        single { DriverFactory(androidContext()) }
    }