package com.nick_sib.kursovikpopularlibraries.mvp.model.entity.room.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.nick_sib.kursovikpopularlibraries.mvp.model.entity.room.RoomCrossTab

@Dao
interface CrossTabDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insert(data: RoomCrossTab)

    @Query("DELETE FROM RoomCrossTab")
    fun clearCrossTable()

    @Query("SELECT employeesId FROM RoomCrossTab WHERE specialtyId = :specialtyId")
    fun getEmployeesBySpecialtyId(specialtyId: Long): List<Long>

    @Query(
            "SELECT RoomSpecialty.specialtyName " +
                    "FROM  RoomCrossTab INNER JOIN RoomSpecialty ON RoomCrossTab.specialtyId = RoomSpecialty.specialtyId " +
                    "WHERE RoomCrossTab.employeesId = :idEmployee"
    )
    fun getSpecialtyByEmployeeId(idEmployee: Long): List<String>

    @Query("SELECT COUNT(employeesId) FROM RoomCrossTab")
    fun getEmployeesCount(): Int
}