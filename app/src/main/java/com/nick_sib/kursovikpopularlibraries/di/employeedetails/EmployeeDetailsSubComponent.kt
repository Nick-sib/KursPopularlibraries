package com.nick_sib.kursovikpopularlibraries.di.employeedetails

import com.nick_sib.kursovikpopularlibraries.di.employeedetails.module.EmployeeDetailsModule
import com.nick_sib.kursovikpopularlibraries.mvp.presenter.EmployeeDetailsPresenter
import dagger.Subcomponent


@EmployeeDetailsScope
@Subcomponent(
        modules = [
            EmployeeDetailsModule::class
        ]
)

interface EmployeeDetailsSubComponent {
    fun inject(employeeDetailsPresenter: EmployeeDetailsPresenter)
}