package com.nick_sib.kursovikpopularlibraries.mvp.model.cache

import com.nick_sib.kursovikpopularlibraries.mvp.model.entity.Employees
import io.reactivex.rxjava3.core.Completable
import io.reactivex.rxjava3.core.Single

interface IRoomDataCache {
    fun putData(allData: Employees): Completable
    fun getEmployeesCount(): Single<Int>
}