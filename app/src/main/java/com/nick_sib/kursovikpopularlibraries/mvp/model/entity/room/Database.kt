package com.nick_sib.kursovikpopularlibraries.mvp.model.entity.room

import androidx.room.Room
import androidx.room.RoomDatabase
import com.nick_sib.kursovikpopularlibraries.App
import com.nick_sib.kursovikpopularlibraries.mvp.model.entity.room.dao.CrossTabDao
import com.nick_sib.kursovikpopularlibraries.mvp.model.entity.room.dao.EmployeeDao
import com.nick_sib.kursovikpopularlibraries.mvp.model.entity.room.dao.SpecialtyDao

import javax.inject.Inject

@androidx.room.Database(
    entities = [RoomEmployee::class, RoomSpecialty::class, RoomCrossTab::class],
    version = 1
)
abstract class Database : RoomDatabase() {
    abstract val employeeDao: EmployeeDao
    abstract val specialtyDao: SpecialtyDao
    abstract val crossTab: CrossTabDao

//    @Inject
//    lateinit var app: App

    companion object {
        const val DB_NAME = "database.db"
//        var instance: Database? = null
//            get() = field ?: synchronized(Database::class.java) {
//                field = Room.databaseBuilder(App.instance, Database::class.java, DB_NAME)
//                    .fallbackToDestructiveMigration()
//                    .build()
//                field
//            }
//            private set
    }
}