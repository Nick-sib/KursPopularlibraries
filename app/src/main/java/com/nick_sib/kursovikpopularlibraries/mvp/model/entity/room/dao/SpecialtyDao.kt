package com.nick_sib.kursovikpopularlibraries.mvp.model.entity.room.dao

import androidx.room.*
import com.nick_sib.kursovikpopularlibraries.mvp.model.entity.room.RoomSpecialty

@Dao
interface SpecialtyDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insert(specialty: RoomSpecialty)

    @Update
    fun update(specialty: RoomSpecialty)

    @Delete
    fun delete(specialty: RoomSpecialty)

    @Query("SELECT * FROM RoomSpecialty ORDER BY specialtyName")
    fun getAll(): List<RoomSpecialty>
}