package com.guilherme.furiatalk.di

import com.guilherme.furiatalk.chat.data.api.GeminiServiceImpl
import com.guilherme.furiatalk.chat.data.local.DriverFactory
import com.guilherme.furiatalk.chat.data.repository.MessageRepositoryImpl
import com.guilherme.furiatalk.chat.domain.GeminiService
import com.guilherme.furiatalk.chat.domain.MessageRepository
import com.guilherme.furiatalk.chat.presentation.ChatViewModel
import org.koin.core.module.Module
import org.koin.core.module.dsl.viewModel
import org.koin.dsl.module

expect val platformModule: Module

val sharedModule = module {

    single<GeminiService> { GeminiServiceImpl() }
    single<MessageRepository> { MessageRepositoryImpl(get(), get()) }

    viewModel { ChatViewModel(get()) }
}