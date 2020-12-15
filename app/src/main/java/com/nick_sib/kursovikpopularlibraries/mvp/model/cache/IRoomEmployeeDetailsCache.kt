package com.nick_sib.kursovikpopularlibraries.mvp.model.cache

import io.reactivex.rxjava3.core.Single

interface IRoomEmployeeDetailsCache {
    fun getSpecialtyByEmployeeId(userId: Long): Single<List<String>>
}