package com.nick_sib.kursovikpopularlibraries.di.main


import com.nick_sib.kursovikpopularlibraries.di.main.module.MainModule
import com.nick_sib.kursovikpopularlibraries.mvp.presenter.MainPresenter
import dagger.Subcomponent


@MainScope
@Subcomponent(
        modules = [
            MainModule::class
        ]
)

interface MainSubComponent {
    fun inject(mainPresenter: MainPresenter)
}