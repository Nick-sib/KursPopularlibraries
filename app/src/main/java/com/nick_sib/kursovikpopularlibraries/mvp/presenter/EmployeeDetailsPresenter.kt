package com.nick_sib.kursovikpopularlibraries.mvp.presenter

import com.nick_sib.kursovikpopularlibraries.mvp.model.cache.IRoomDataCache
import com.nick_sib.kursovikpopularlibraries.mvp.model.entity.room.RoomEmployee
import com.nick_sib.kursovikpopularlibraries.mvp.view.RoomViewDetails
import io.reactivex.rxjava3.core.Scheduler
import io.reactivex.rxjava3.schedulers.Schedulers
import moxy.MvpPresenter
import ru.terrakok.cicerone.Router
import javax.inject.Inject

class EmployeeDetailsPresenter(
        private val employeeData: RoomEmployee
): MvpPresenter<RoomViewDetails<List<String>>>() {

    @Inject
    lateinit var employeeDetailsCache: IRoomDataCache
    @Inject
    lateinit var mainThreadScheduler: Scheduler
    @Inject
    lateinit var router: Router


    private fun loadData(){
        viewState.initView()
        employeeData.id?.run {
            employeeDetailsCache.getSpecialtyByUserId(this)
                    .subscribeOn(Schedulers.io())
                    .observeOn(mainThreadScheduler)
                    .subscribe({
                        viewState.updateData(it)
                    }, {
                        it?.message?.run {
                            viewState.showError(this)
                        }
                    })
        } ?: viewState.showError("Данные пользователя не загружены")

    }

    override fun onFirstViewAttach() {
        super.onFirstViewAttach()
        viewState.initView()
        loadData()
    }

}