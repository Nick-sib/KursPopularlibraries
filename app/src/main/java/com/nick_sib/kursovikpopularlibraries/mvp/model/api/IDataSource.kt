package com.nick_sib.kursovikpopularlibraries.mvp.model.api


import com.nick_sib.kursovikpopularlibraries.mvp.model.entity.Employees
import io.reactivex.rxjava3.core.Single
import retrofit2.http.GET

interface IDataSource {
/**Retrofit непосредственно загрузка данных с ветки testTask.json
 * */
    @GET("testTask.json")
    fun getAllData(): Single<Employees>

}