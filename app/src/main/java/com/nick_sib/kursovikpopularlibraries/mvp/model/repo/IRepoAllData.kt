package com.nick_sib.kursovikpopularlibraries.mvp.model.repo


import io.reactivex.rxjava3.core.Completable
import io.reactivex.rxjava3.core.Observable

interface IRepoAllData {
    fun loadAllData(): Completable
    fun waitInternet(): Observable<Boolean>
}