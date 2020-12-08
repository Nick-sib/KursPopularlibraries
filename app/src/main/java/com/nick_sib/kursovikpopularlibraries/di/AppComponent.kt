package com.nick_sib.kursovikpopularlibraries.di

import com.nick_sib.kursovikpopularlibraries.di.module.*
import com.nick_sib.kursovikpopularlibraries.mvp.presenter.EmployeeDetailsPresenter
import com.nick_sib.kursovikpopularlibraries.mvp.presenter.EmployeesPresenter
import com.nick_sib.kursovikpopularlibraries.mvp.presenter.SpecialitysPresenter
import com.nick_sib.kursovikpopularlibraries.mvp.presenter.MainPresenter
import com.nick_sib.kursovikpopularlibraries.ui.activities.MainActivity
import dagger.Component
import javax.inject.Singleton

@Singleton
@Component(
    modules = [
        AppModule::class,
        CiceroneModule::class,
        CacheModule::class,
        ApiModule::class,
        RepoModule::class
    ]
)

interface AppComponent {
    fun inject(mainActivity: MainActivity)
    fun inject(mainPresenter: MainPresenter)
    fun inject(specialtysPresenter: SpecialitysPresenter)
    fun inject(employeesPresenter: EmployeesPresenter)
    fun inject(employeeDetailsPresenter: EmployeeDetailsPresenter)
}