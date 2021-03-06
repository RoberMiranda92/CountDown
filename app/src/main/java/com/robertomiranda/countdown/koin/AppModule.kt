package com.robertomiranda.countdown.koin

import com.robertomiranda.countdown.createevent.CreateEventViewModel
import com.robertomiranda.countdown.eventlist.EventListViewModel
import com.robertomiranda.countdown.model.CreateEventRepository
import com.robertomiranda.data.eventdetail.repository.EventsRepository
import com.robertomiranda.data.eventdetail.repository.LocalEventRepository
import com.robertomiranda.data.room.DaoFactory
import org.koin.android.ext.koin.androidContext
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.core.qualifier.named
import org.koin.dsl.module

enum class Scopes {
    EVENTS
}

val viewModelModules = module {
    viewModel { CreateEventViewModel(get(), getScope(Scopes.EVENTS.name).get()) }
    viewModel { EventListViewModel(getScope(Scopes.EVENTS.name).get()) }
}

val repositoryModules = module {
    single { CreateEventRepository() }
    scope(named(Scopes.EVENTS.name)) {
        scoped<EventsRepository> { LocalEventRepository(DaoFactory.getEventDao(androidContext())) }
    }
}

