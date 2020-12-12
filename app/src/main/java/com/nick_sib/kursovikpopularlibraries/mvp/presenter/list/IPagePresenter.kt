package com.nick_sib.kursovikpopularlibraries.mvp.presenter.list


interface IPagePresenter {
    fun getCount(): Int
    fun getId(position: Int): Long
    fun getTitle(position: Int): String
}
