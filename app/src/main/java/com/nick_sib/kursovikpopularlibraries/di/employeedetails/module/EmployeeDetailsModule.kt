package com.nick_sib.kursovikpopularlibraries.di.employeedetails.module

import com.nick_sib.kursovikpopularlibraries.di.employeedetails.EmployeeDetailsScope
import com.nick_sib.kursovikpopularlibraries.mvp.model.cache.IRoomEmployeeDetailsCache
import com.nick_sib.kursovikpopularlibraries.mvp.model.cache.room.RoomEmployeeDetailsCache
import com.nick_sib.kursovikpopularlibraries.mvp.model.entity.room.Database
import dagger.Module
import dagger.Provides


@Module
class EmployeeDetailsModule {

    @EmployeeDetailsScope
    @Provides
    fun employeeDetailsCache(database: Database): IRoomEmployeeDetailsCache = RoomEmployeeDetailsCache(database)
}