package com.nick_sib.kursovikpopularlibraries.di


import com.nick_sib.kursovikpopularlibraries.di.employees.EmployeesSubComponent
import com.nick_sib.kursovikpopularlibraries.di.main.MainSubComponent
import com.nick_sib.kursovikpopularlibraries.di.module.*
import com.nick_sib.kursovikpopularlibraries.di.specialty.SpecialtySubComponent
import com.nick_sib.kursovikpopularlibraries.ui.activities.MainActivity
import dagger.Component
import javax.inject.Singleton

@Singleton
@Component(
    modules = [
        AppModule::class,
        CiceroneModule::class,
        DataBaseModule::class,
        ApiModule::class
    ]
)

interface AppComponent {
    fun employeesSubComponent() : EmployeesSubComponent
    fun specialtySubComponent() : SpecialtySubComponent
    fun mainSubComponent() : MainSubComponent

    fun inject(mainActivity: MainActivity)
}