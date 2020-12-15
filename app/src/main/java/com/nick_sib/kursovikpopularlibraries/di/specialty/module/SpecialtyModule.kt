package com.nick_sib.kursovikpopularlibraries.di.specialty.module

import com.nick_sib.kursovikpopularlibraries.di.specialty.SpecialtyScope
import com.nick_sib.kursovikpopularlibraries.mvp.model.cache.IRoomSpecialtyCache
import com.nick_sib.kursovikpopularlibraries.mvp.model.cache.room.RoomSpecialtyCache
import com.nick_sib.kursovikpopularlibraries.mvp.model.entity.room.Database
import dagger.Module
import dagger.Provides


@Module
class SpecialtyModule {

    @SpecialtyScope
    @Provides
    fun specialtyCache(database: Database): IRoomSpecialtyCache = RoomSpecialtyCache(database)
}