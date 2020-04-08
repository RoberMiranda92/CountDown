package com.robertomiranda.countdown.koin

import com.robertomiranda.countdown.createevent.CreateEventViewModel
import com.robertomiranda.countdown.model.CreateEventRepository
import com.robertomiranda.data.eventdetail.repository.EventsRepository
import com.robertomiranda.data.eventdetail.repository.LocalEventRepository
import com.robertomiranda.data.room.DaoFactory
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.core.qualifier.named
import org.koin.dsl.module

val viewModelModules = module {
    viewModel { CreateEventViewModel(get(), getScope("CreateEvent").get()) }
}

val repositoryModules = module {
    single { CreateEventRepository() }
    scope(named("CreateEvent")) {
        scoped<EventsRepository> { LocalEventRepository(DaoFactory.getEventDao()) }
    }

}

