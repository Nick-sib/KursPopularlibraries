package com.nick_sib.kursovikpopularlibraries.mvp.presenter.list

import com.nick_sib.kursovikpopularlibraries.mvp.view.list.IItemView

interface IPagePresenter<V : IItemView> {
    fun getCount(): Int
    fun getId(position: Int): Long
    fun getTitle(position: Int): String
}
