package com.nick_sib.kursovikpopularlibraries.mvp.view

import moxy.MvpView
import moxy.viewstate.strategy.AddToEndSingleStrategy
import moxy.viewstate.strategy.StateStrategyType

@StateStrategyType(AddToEndSingleStrategy::class)
interface RoomView : MvpView {
    fun initView()
    fun updateData()
    fun showError(errorText: String)
    fun release()
}