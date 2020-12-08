package com.nick_sib.kursovikpopularlibraries

import android.app.Application
import com.nick_sib.kursovikpopularlibraries.di.AppComponent
import com.nick_sib.kursovikpopularlibraries.di.DaggerAppComponent
import com.nick_sib.kursovikpopularlibraries.di.module.AppModule

class App : Application() {
    companion object {
        lateinit var instance: App
            private set
    }

    lateinit var appComponent: AppComponent

    override fun onCreate() {
        super.onCreate()
        instance = this

        appComponent = DaggerAppComponent.builder()
                .appModule(AppModule(this))
                .build()
    }
}