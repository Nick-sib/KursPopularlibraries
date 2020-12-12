package com.nick_sib.kursovikpopularlibraries.mvp.model.repo.retrofit

import com.nick_sib.kursovikpopularlibraries.mvp.model.api.IDataSource
import com.nick_sib.kursovikpopularlibraries.mvp.model.cache.IRoomDataCache
import com.nick_sib.kursovikpopularlibraries.mvp.model.repo.IRepoAllData
import com.nick_sib.kursovikpopularlibraries.mvp.network.INetworkStatus
import io.reactivex.rxjava3.core.Completable
import io.reactivex.rxjava3.schedulers.Schedulers

class RetrofitData(
    private val api: IDataSource,
    private val networkStatus: INetworkStatus,
    private val cache: IRoomDataCache
): IRepoAllData {
    override fun loadAllData(): Completable = networkStatus.isOnlineSingle().flatMapCompletable { isOnline ->
        if (isOnline) {
            api.getAllData().flatMapCompletable {
                cache.putData(it)
            }
        } else {
            Completable.fromAction{
                
            }
        }
    }.subscribeOn(Schedulers.io())
}
