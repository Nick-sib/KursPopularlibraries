package com.nick_sib.kursovikpopularlibraries.mvp.presenter.list

import com.nick_sib.kursovikpopularlibraries.mvp.view.list.IItemView

interface IListPresenter<V : IItemView> {
    var itemClickListener: ((V) -> Unit)?
    fun bindView(view: V)
    fun getCount(): Int
}