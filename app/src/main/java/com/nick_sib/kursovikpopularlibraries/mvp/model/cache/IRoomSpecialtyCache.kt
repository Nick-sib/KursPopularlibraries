package com.nick_sib.kursovikpopularlibraries.mvp.model.cache

import com.nick_sib.kursovikpopularlibraries.mvp.model.entity.room.RoomSpecialty
import io.reactivex.rxjava3.core.Single

interface IRoomSpecialtyCache {
    fun getAllSpecialty(): Single<List<RoomSpecialty>>
}