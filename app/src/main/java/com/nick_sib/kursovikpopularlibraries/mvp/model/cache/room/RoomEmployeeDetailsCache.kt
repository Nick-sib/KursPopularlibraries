package com.nick_sib.kursovikpopularlibraries.mvp.model.cache.room

import com.nick_sib.kursovikpopularlibraries.mvp.model.cache.IRoomEmployeeDetailsCache
import com.nick_sib.kursovikpopularlibraries.mvp.model.entity.room.Database
import io.reactivex.rxjava3.core.Single

class RoomEmployeeDetailsCache(private val db: Database): IRoomEmployeeDetailsCache {
    override fun getSpecialtyByEmployeeId(userId: Long): Single<List<String>> = Single.fromCallable {
        db.crossTab.getSpecialtyByEmployeeId(userId)
    }
}