package com.robertomiranda.countdown.koin

import com.robertomiranda.countdown.createevent.CreateEventViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val appModule = module {

    // ViewModel for CreateEventView
    viewModel { CreateEventViewModel() }

}