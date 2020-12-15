package com.nick_sib.kursovikpopularlibraries

import android.app.Application
import com.nick_sib.kursovikpopularlibraries.di.AppComponent
import com.nick_sib.kursovikpopularlibraries.di.DaggerAppComponent
import com.nick_sib.kursovikpopularlibraries.di.employeedetails.EmployeeDetailsSubComponent
import com.nick_sib.kursovikpopularlibraries.di.employees.EmployeesSubComponent
import com.nick_sib.kursovikpopularlibraries.di.main.MainSubComponent
import com.nick_sib.kursovikpopularlibraries.di.module.AppModule
import com.nick_sib.kursovikpopularlibraries.di.specialty.SpecialtySubComponent

class App : Application() {
    companion object {
        lateinit var instance: App
            private set
    }

    lateinit var appComponent: AppComponent


    private var mainSubComponent: MainSubComponent? = null
    private var specialtySubComponent: SpecialtySubComponent? = null
    private var employeesSubComponent: EmployeesSubComponent? = null
    private var employeeDetailsSubComponent: EmployeeDetailsSubComponent? = null


    override fun onCreate() {
        super.onCreate()
        instance = this

        appComponent = DaggerAppComponent.builder()
                .appModule(AppModule(this))
                .build()
    }


    fun initMainSubComponent() = appComponent.mainSubComponent().also {
        mainSubComponent = it
    }

    fun releaseMainSubComponent() {
        mainSubComponent = null
    }

    fun initSpecialtySubComponent() = appComponent.specialtySubComponent().also {
        specialtySubComponent = it
    }

    fun releaseSpecialtySubComponent() {
        specialtySubComponent = null
    }

    fun initEmployeesSubComponent() = appComponent.employeesSubComponent().also {
        employeesSubComponent = it
    }

    fun releaseEmployeesSubComponent() {
        employeesSubComponent = null
    }


    fun initEmployeeDetailsSubComponent() = employeesSubComponent?.employeeDetailsSubComponent().also {
        employeeDetailsSubComponent = it
    }

    fun releaseEmployeeDetailsSubComponent() {
        employeeDetailsSubComponent = null
    }

}