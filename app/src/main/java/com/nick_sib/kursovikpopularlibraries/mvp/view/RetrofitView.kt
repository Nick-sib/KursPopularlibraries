package com.nick_sib.kursovikpopularlibraries.mvp.view

import moxy.MvpView
import moxy.viewstate.strategy.AddToEndSingleStrategy
import moxy.viewstate.strategy.StateStrategyType

@StateStrategyType(AddToEndSingleStrategy::class)
interface RetrofitView : MvpView {
    fun beginLoading()
    fun endLoading()
    fun showError(errorText: String)
}