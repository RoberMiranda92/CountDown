package com.robertomiranda.countdown.koin

import com.robertomiranda.countdown.createevent.CreateEventViewModel
import com.robertomiranda.countdown.eventlist.EventListViewModel
import com.robertomiranda.countdown.model.CreateEventRepository
import com.robertomiranda.data.eventdetail.repository.EventsRepository
import com.robertomiranda.data.eventdetail.repository.LocalEventRepository
import com.robertomiranda.data.room.DaoFactory
import org.koin.android.ext.koin.androidContext
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

enum class Scopes {
    CREATE_EVENT
}

val viewModelModules = module {
    //viewModel { CreateEventViewModel(get(), getScope(Scopes.CREATE_EVENT.name).get()) }
    viewModel { CreateEventViewModel(get(), get()) }
    viewModel { EventListViewModel(get()) }

}

val repositoryModules = module {
    single { CreateEventRepository() }
    single<EventsRepository> { LocalEventRepository(DaoFactory.getEventDao(androidContext())) }
//    scope(named(Scopes.CREATE_EVENT.name)) {
//        scoped<EventsRepository> { LocalEventRepository(DaoFactory.getEventDao(androidContext())) }
//    }

}

