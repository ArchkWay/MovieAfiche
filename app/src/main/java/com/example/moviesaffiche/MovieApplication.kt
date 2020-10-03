package com.example.moviesaffiche

import androidx.multidex.MultiDex
import androidx.multidex.MultiDexApplication

class MovieApplication : MultiDexApplication() {

    override fun onCreate() {
        super.onCreate()
        MultiDex.install(this)
    }
}
