package com.example.issuesghub.di

import com.example.issuesghub.main.viewmodels.MainViewModel
import com.example.issuesghub.models.User
import com.example.issuesghub.network.api.ApiBuilder
import com.example.issuesghub.network.api.ApiService
import com.example.issuesghub.network.api.CallResponse
import com.example.issuesghub.network.repository.Repository_Implement
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val homeModule = module {
    single { ApiBuilder() }
    single { ApiService }
    single { CallResponse() }
    single { Repository_Implement() }
    single { User(get(),get(),get(),get(),get(),get(),get(),get()) }

    viewModel { MainViewModel(get ())}

}