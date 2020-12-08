package com.nick_sib.kursovikpopularlibraries.mvp.model.entity.room.dao

import androidx.room.*
import com.nick_sib.kursovikpopularlibraries.mvp.model.entity.room.RoomEmployee

@Dao
interface EmployeeDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insert(user: RoomEmployee): Long

    @Update
    fun update(user: RoomEmployee)
    @Update
    fun update(vararg users: RoomEmployee)
    @Update
    fun update(users: List<RoomEmployee>)

    @Delete
    fun delete(user: RoomEmployee)
    @Delete
    fun delete(vararg users: RoomEmployee)
    @Delete
    fun delete(users: List<RoomEmployee>)

    @Query("SELECT * FROM RoomEmployee")
    fun getAll(): List<RoomEmployee>

    @Query("SELECT RoomEmployee.* " +
            "FROM  RoomEmployee INNER JOIN RoomCrossTab ON RoomEmployee.id = RoomCrossTab.employeesId " +
            "WHERE RoomCrossTab.specialtyId = :sId ORDER BY RoomEmployee.f_name, RoomEmployee.l_name"
    )
    fun getEmployeesBySpecialtyId(sId: Long): List<RoomEmployee>
}