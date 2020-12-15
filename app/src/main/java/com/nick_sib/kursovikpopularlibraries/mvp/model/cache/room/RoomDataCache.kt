package com.nick_sib.kursovikpopularlibraries.mvp.model.cache.room


import com.nick_sib.kursovikpopularlibraries.dbDataFormat
import com.nick_sib.kursovikpopularlibraries.dbNameFormat
import com.nick_sib.kursovikpopularlibraries.dbURLFormat
import com.nick_sib.kursovikpopularlibraries.mvp.model.cache.IRoomDataCache
import com.nick_sib.kursovikpopularlibraries.mvp.model.entity.Employees
import com.nick_sib.kursovikpopularlibraries.mvp.model.entity.room.Database
import com.nick_sib.kursovikpopularlibraries.mvp.model.entity.room.RoomCrossTab
import com.nick_sib.kursovikpopularlibraries.mvp.model.entity.room.RoomEmployee
import com.nick_sib.kursovikpopularlibraries.mvp.model.entity.room.RoomSpecialty
import io.reactivex.rxjava3.core.Completable
import io.reactivex.rxjava3.core.Single
import io.reactivex.rxjava3.schedulers.Schedulers

class RoomDataCache(private val db: Database) : IRoomDataCache {
    //Кешируем полный список данных (специальности и сотрудники плюс таблица зависимостей)
    override fun putData(allData: Employees): Completable = Completable.fromAction {
        //очищаем все таблицы
        db.clearAllTables()
        //заполняем таблицы
        allData.response.forEach{employee ->
            val employeeId = db.employeeDao.insert(
                RoomEmployee(
                    f_name = employee.f_name.dbNameFormat(),
                    l_name = employee.l_name.dbNameFormat(),
                    birthday = employee.birthday.dbDataFormat(),
                    avatr_url = employee.avatr_url.dbURLFormat()
                )
            )
            employee.specialty.forEach {specialty ->
                db.specialtyDao.insert(
                    RoomSpecialty(
                        specialty.specialty_id,
                        specialty.name.dbNameFormat()
                    )
                )
                db.crossTab.insert(
                    RoomCrossTab(
                        employeesId = employeeId,
                        specialtyId = specialty.specialty_id
                    )
                )
            }

        }
    }

    //проверяем надичия данных в БД тк данные по внешнему ключу таблицы удаляются каскадом то
    //наличие хоть одной записи в таблице значит то есть что загружать
    override fun getEmployeesCount(): Single<Int> = Single.fromCallable {
        db.crossTab.getEmployeesCount()
    }.subscribeOn(Schedulers.io())
}