package com.nick_sib.kursovikpopularlibraries.mvp.model.entity.room

import androidx.room.Entity
import androidx.room.ForeignKey
import androidx.room.PrimaryKey

@Entity(
    foreignKeys = [ForeignKey(
        entity = RoomEmployee::class,
        parentColumns = ["id"],
        childColumns = ["employeesId"],
        onDelete = ForeignKey.CASCADE
    ),
    ForeignKey(
        entity = RoomSpecialty::class,
        parentColumns = ["specialtyId"],
        childColumns = ["specialtyId"],
    )]
)

data class RoomCrossTab(
    @PrimaryKey(autoGenerate = true)
    val id: Long? = null,
    val employeesId: Long,
    val specialtyId: Long
)