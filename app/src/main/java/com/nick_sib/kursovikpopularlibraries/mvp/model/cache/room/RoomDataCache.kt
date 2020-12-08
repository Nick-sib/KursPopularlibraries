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

class RoomDataCache(private val db: Database) : IRoomDataCache {

    override fun putData(allData: Employees): Completable = Completable.fromAction {
        //очищаем таблицы
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

    override fun getSpecialty(): Single<List<RoomSpecialty>> = Single.fromCallable {
        db.specialtyDao.getAll()
    }

    override fun getEmployees(specialtyId: Long): Single<List<RoomEmployee>> = Single.fromCallable {
        db.employeeDao.getEmployeesBySpecialtyId(specialtyId)
    }

    override fun getSpecialtyByUserId(userId: Long): Single<List<String>> = Single.fromCallable {
        db.crossTab.getSpecialtyByUserId(userId)
    }

}
/*override fun putRepos(repositories: List<GithubRepository>, user: GithubUser): Completable =
        Completable.fromAction{
            val roomUser = db.userDao.findByLogin(user.login)
            val roomRepos = repositories.map {
                RoomGithubRepository(it.id, it.name, it.forksCount, roomUser?.id ?: 0L) }
            db.repositoryDao.insert(roomRepos)
        }

    override fun getRepos(user: GithubUser): Single<List<GithubRepository>> =
        Single.fromCallable {
            val roomUser = db.userDao.findByLogin(user.login)
            roomUser?.run{
                db.repositoryDao.findForUser(id).map { GithubRepository(it.id, it.name, it.forksCount) }
            } ?: throw RuntimeException("No such user in cache")
        }*/