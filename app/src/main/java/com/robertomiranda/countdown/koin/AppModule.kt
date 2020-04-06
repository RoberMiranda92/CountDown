package com.robertomiranda.countdown.koin

import com.robertomiranda.countdown.createevent.CreateEventViewModel
import com.robertomiranda.countdown.model.CreateEventRepository
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val viewModelModules = module {

    // ViewModel for CreateEventView
    viewModel { CreateEventViewModel(get()) }

}

val repositoryModules = module {

    single { CreateEventRepository() }
}