package com.nick_sib.kursovikpopularlibraries.di.module

import androidx.room.Room
import com.nick_sib.kursovikpopularlibraries.App
import com.nick_sib.kursovikpopularlibraries.mvp.model.entity.room.Database
import dagger.Module
import dagger.Provides
import javax.inject.Singleton


@Module
class DataBaseModule {
    /**Модуль отвечающий за доступ к базе данных используется библиотека Room
     * */
    @Singleton
    @Provides
    fun database(app: App): Database = Room.databaseBuilder(
        app,
        Database::class.java,
        Database.DB_NAME
    ).build()

}