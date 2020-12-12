package com.nick_sib.kursovikpopularlibraries.mvp.model.cache

import com.nick_sib.kursovikpopularlibraries.mvp.model.entity.Employees
import com.nick_sib.kursovikpopularlibraries.mvp.model.entity.room.RoomEmployee
import com.nick_sib.kursovikpopularlibraries.mvp.model.entity.room.RoomSpecialty
import io.reactivex.rxjava3.core.Completable
import io.reactivex.rxjava3.core.Single

//разделить
interface IRoomDataCache {
    fun putData(allData: Employees): Completable
    fun getEmployees(specialtyId: Long): Single<List<RoomEmployee>>
    fun getSpecialty(): Single<List<RoomSpecialty>>
    fun getSpecialtyByUserId(userId: Long): Single<List<String>>
}