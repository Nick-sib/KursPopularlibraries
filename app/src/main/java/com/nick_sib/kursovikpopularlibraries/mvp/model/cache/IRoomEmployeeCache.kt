package com.nick_sib.kursovikpopularlibraries.mvp.model.cache

import com.nick_sib.kursovikpopularlibraries.mvp.model.entity.room.RoomEmployee
import io.reactivex.rxjava3.core.Single

interface IRoomEmployeeCache {
    fun getEmployeesBySpecialtyId(specialtyId: Long): Single<List<RoomEmployee>>
}