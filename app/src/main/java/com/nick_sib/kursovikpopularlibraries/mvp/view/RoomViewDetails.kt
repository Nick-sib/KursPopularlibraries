package com.nick_sib.kursovikpopularlibraries.mvp.view

import moxy.MvpView
import moxy.viewstate.strategy.AddToEndSingleStrategy
import moxy.viewstate.strategy.StateStrategyType

@StateStrategyType(AddToEndSingleStrategy::class)
interface RoomViewDetails<T> : MvpView {
    fun initView()
    fun updateData(data: T)
    fun showError(errorText: String)
    fun release()
}