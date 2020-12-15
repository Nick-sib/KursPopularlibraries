package com.nick_sib.kursovikpopularlibraries.mvp.model.cache.room


import com.nick_sib.kursovikpopularlibraries.mvp.model.cache.IRoomSpecialtyCache
import com.nick_sib.kursovikpopularlibraries.mvp.model.entity.room.Database
import com.nick_sib.kursovikpopularlibraries.mvp.model.entity.room.RoomSpecialty
import io.reactivex.rxjava3.core.Single

class RoomSpecialtyCache(private val db: Database): IRoomSpecialtyCache {
    override fun getAllSpecialty(): Single<List<RoomSpecialty>> = Single.fromCallable {
        db.specialtyDao.getAll()
    }
}