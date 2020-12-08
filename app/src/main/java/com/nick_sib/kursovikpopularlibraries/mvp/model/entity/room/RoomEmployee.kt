package com.nick_sib.kursovikpopularlibraries.mvp.model.entity.room

import android.os.Parcelable
import androidx.room.Entity
import androidx.room.PrimaryKey
import kotlinx.parcelize.Parcelize

@Parcelize
@Entity
class RoomEmployee(
    @PrimaryKey(autoGenerate = true)
    val id: Long? = null,
    val f_name: String,
    val l_name: String,
    val birthday: String,
    val avatr_url: String
): Parcelable