package com.nick_sib.kursovikpopularlibraries.mvp.view.list

interface EmployeeItemView: IItemView {
    fun setName(text: String)
    fun setAge(text: String)
    fun loadAvatar(url: String)
}