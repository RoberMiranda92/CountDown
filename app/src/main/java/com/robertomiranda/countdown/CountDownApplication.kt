package com.robertomiranda.countdown

import android.app.Application
import com.robertomiranda.countdown.koin.viewModelModules
import com.robertomiranda.countdown.koin.repositoryModules
import org.koin.android.ext.koin.androidContext
import org.koin.android.ext.koin.androidLogger
import org.koin.core.context.startKoin

class CountDownApplication: Application() {

    override fun onCreate() {
        super.onCreate()
        startKoin {
            // Koin Android logger
            androidLogger()
            //inject Android context
            androidContext(this@CountDownApplication)
            // use modules
            modules(viewModelModules, repositoryModules)
        }
    }
}