package com.nick_sib.kursovikpopularlibraries.mvp.model.cache.room


import com.nick_sib.kursovikpopularlibraries.mvp.model.cache.IRoomEmployeeCache
import com.nick_sib.kursovikpopularlibraries.mvp.model.entity.room.Database
import com.nick_sib.kursovikpopularlibraries.mvp.model.entity.room.RoomEmployee
import io.reactivex.rxjava3.core.Single

class RoomEmployeeCache(private val db: Database): IRoomEmployeeCache {
    override fun getEmployeesBySpecialtyId(specialtyId: Long): Single<List<RoomEmployee>> = Single.fromCallable {
        db.employeeDao.getEmployeesBySpecialtyId(specialtyId)
    }
}