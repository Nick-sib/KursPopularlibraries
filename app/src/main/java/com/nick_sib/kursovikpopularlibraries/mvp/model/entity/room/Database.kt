package com.nick_sib.kursovikpopularlibraries.mvp.model.entity.room

import androidx.room.RoomDatabase
import com.nick_sib.kursovikpopularlibraries.mvp.model.entity.room.dao.CrossTabDao
import com.nick_sib.kursovikpopularlibraries.mvp.model.entity.room.dao.EmployeeDao
import com.nick_sib.kursovikpopularlibraries.mvp.model.entity.room.dao.SpecialtyDao

@androidx.room.Database(
    entities = [RoomEmployee::class, RoomSpecialty::class, RoomCrossTab::class],
    version = 1
)
abstract class Database : RoomDatabase() {
    abstract val employeeDao: EmployeeDao
    abstract val specialtyDao: SpecialtyDao
    abstract val crossTab: CrossTabDao

    companion object {
        const val DB_NAME = "database.db"
    }
}