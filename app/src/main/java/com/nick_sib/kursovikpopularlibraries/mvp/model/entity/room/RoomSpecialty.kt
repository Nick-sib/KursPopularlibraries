package com.nick_sib.kursovikpopularlibraries.mvp.model.entity.room

import androidx.room.Entity
import androidx.room.PrimaryKey


@Entity
data class RoomSpecialty(
    @PrimaryKey
    val specialtyId: Long,
    val specialtyName: String
)