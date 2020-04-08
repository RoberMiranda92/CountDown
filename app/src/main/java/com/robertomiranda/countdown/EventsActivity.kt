package com.robertomiranda.countdown

import android.os.Bundle
import com.google.android.material.snackbar.Snackbar
import androidx.appcompat.app.AppCompatActivity
import android.view.Menu
import android.view.MenuItem
import com.robertomiranda.countdown.koin.Scopes

import kotlinx.android.synthetic.main.activity_main.*
import org.koin.android.ext.android.getKoin
import org.koin.core.qualifier.named
import org.koin.java.KoinJavaComponent

class EventsActivity : AppCompatActivity() {

    private val eventScope =
        getKoin().getOrCreateScope(Scopes.EVENTS.name, named(Scopes.EVENTS.name))

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
    }


    override fun onDestroy() {
        super.onDestroy()
        eventScope.close()
    }
}
