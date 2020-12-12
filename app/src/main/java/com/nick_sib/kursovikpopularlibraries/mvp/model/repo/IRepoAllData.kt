package com.nick_sib.kursovikpopularlibraries.mvp.model.repo


import io.reactivex.rxjava3.core.Completable

interface IRepoAllData {
    fun loadAllData(): Completable

}