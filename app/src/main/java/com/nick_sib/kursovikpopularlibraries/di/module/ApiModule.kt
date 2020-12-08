package com.nick_sib.kursovikpopularlibraries.di.module


import com.nick_sib.kursovikpopularlibraries.App
import com.nick_sib.kursovikpopularlibraries.mvp.model.api.IDataSource
import com.nick_sib.kursovikpopularlibraries.mvp.network.INetworkStatus
import com.nick_sib.kursovikpopularlibraries.ui.network.AndroidNetworkStatus
import dagger.Module
import dagger.Provides
import retrofit2.Retrofit
import retrofit2.adapter.rxjava3.RxJava3CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Named
import javax.inject.Singleton

@Module
class ApiModule {
/**По заданию в качестве baseUrl выдвалось 'http://gitlab.65apps.com/65gb/static/raw/master/'
 * проблему безопасности можно было решать через создания доверенной зоны (xml), но backend
 * gitlab.65apps.com проходит проверку сертификата https поэтому просто заменим сертификат
 * */

    @Named("baseUrl")
    @Provides
    fun baseUrl(): String = "https://gitlab.65apps.com/65gb/static/raw/master/"

    @Provides
    fun api(@Named("baseUrl") baseUrl: String): IDataSource = Retrofit.Builder()
        .baseUrl(baseUrl)
        .addCallAdapterFactory(RxJava3CallAdapterFactory.create())
        .addConverterFactory(GsonConverterFactory.create())
        .build()
        .create(IDataSource::class.java)


    @Singleton
    @Provides
    fun networkStatus(app: App): INetworkStatus = AndroidNetworkStatus(app)
}