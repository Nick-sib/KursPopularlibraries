package com.nick_sib.kursovikpopularlibraries.di.module

import com.nick_sib.kursovikpopularlibraries.mvp.model.api.IDataSource
import com.nick_sib.kursovikpopularlibraries.mvp.model.cache.IRoomDataCache
import com.nick_sib.kursovikpopularlibraries.mvp.model.repo.IRepoAllData
import com.nick_sib.kursovikpopularlibraries.mvp.model.repo.retrofit.RetrofitData
import com.nick_sib.kursovikpopularlibraries.mvp.network.INetworkStatus
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module
class RepoModule {

    @Singleton
    @Provides
    fun dataRepo(
        api: IDataSource,
        networkStatus: INetworkStatus,
        cache: IRoomDataCache
    ): IRepoAllData = RetrofitData(api, networkStatus, cache)

}