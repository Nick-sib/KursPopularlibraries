package com.nick_sib.kursovikpopularlibraries.di.module


import com.nick_sib.kursovikpopularlibraries.App
import dagger.Module
import dagger.Provides
import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers
import io.reactivex.rxjava3.core.Scheduler

@Module
class AppModule(private val app: App) {
/**Модуль отвечающий за доступ к базовым элементам приложения Application
 * */
    @Provides
    fun app(): App = app

    @Provides
    fun mainThreadScheduler(): Scheduler = AndroidSchedulers.mainThread()
}