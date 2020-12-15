package com.nick_sib.kursovikpopularlibraries.di.employees

import com.nick_sib.kursovikpopularlibraries.di.employeedetails.EmployeeDetailsSubComponent
import com.nick_sib.kursovikpopularlibraries.di.employees.module.EmployeesModule
import com.nick_sib.kursovikpopularlibraries.mvp.presenter.EmployeesPresenter
import dagger.Subcomponent


@EmployeesScope
@Subcomponent(
        modules = [
            EmployeesModule::class
        ]
)

interface EmployeesSubComponent {
    fun employeeDetailsSubComponent() : EmployeeDetailsSubComponent

    fun inject(employeesPresenter: EmployeesPresenter)
}