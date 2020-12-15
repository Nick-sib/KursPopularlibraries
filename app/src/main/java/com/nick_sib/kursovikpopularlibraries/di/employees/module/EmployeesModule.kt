package com.nick_sib.kursovikpopularlibraries.di.employees.module

import com.nick_sib.kursovikpopularlibraries.di.employees.EmployeesScope
import com.nick_sib.kursovikpopularlibraries.mvp.model.cache.IRoomEmployeeCache
import com.nick_sib.kursovikpopularlibraries.mvp.model.cache.room.RoomEmployeeCache
import com.nick_sib.kursovikpopularlibraries.mvp.model.entity.room.Database
import dagger.Module
import dagger.Provides

@Module
class EmployeesModule {

    @EmployeesScope
    @Provides
    fun employeeCache(database: Database): IRoomEmployeeCache = RoomEmployeeCache(database)
}