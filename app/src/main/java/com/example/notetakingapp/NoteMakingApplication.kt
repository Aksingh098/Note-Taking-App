package com.example.notetakingapp

import android.app.Application
import com.example.notetakingapp.data.local.AppContainer
import com.example.notetakingapp.data.local.DefaultAppContainer

class NoteMakingApplication: Application() {

    lateinit var container: AppContainer

    override fun onCreate() {
        super.onCreate()
        container = DefaultAppContainer(this)
    }
}