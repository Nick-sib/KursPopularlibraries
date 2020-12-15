package com.nick_sib.kursovikpopularlibraries.di.main.module

import com.nick_sib.kursovikpopularlibraries.di.main.MainScope
import com.nick_sib.kursovikpopularlibraries.mvp.model.api.IDataSource
import com.nick_sib.kursovikpopularlibraries.mvp.model.cache.IRoomDataCache
import com.nick_sib.kursovikpopularlibraries.mvp.model.cache.room.RoomDataCache
import com.nick_sib.kursovikpopularlibraries.mvp.model.entity.room.Database
import com.nick_sib.kursovikpopularlibraries.mvp.model.repo.IRepoAllData
import com.nick_sib.kursovikpopularlibraries.mvp.model.repo.retrofit.RetrofitData
import com.nick_sib.kursovikpopularlibraries.mvp.network.INetworkStatus
import dagger.Module
import dagger.Provides

@Module
class MainModule {

    @MainScope
    @Provides
    fun dataCache(database: Database): IRoomDataCache = RoomDataCache(database)

    @MainScope
    @Provides
    fun dataRepo(
            api: IDataSource,
            networkStatus: INetworkStatus,
            cache: IRoomDataCache
    ): IRepoAllData = RetrofitData(api, networkStatus, cache)
}