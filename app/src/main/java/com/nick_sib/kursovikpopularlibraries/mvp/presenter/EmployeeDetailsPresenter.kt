package com.nick_sib.kursovikpopularlibraries.mvp.presenter


import com.nick_sib.kursovikpopularlibraries.mvp.model.cache.IRoomEmployeeDetailsCache
import com.nick_sib.kursovikpopularlibraries.mvp.model.entity.room.RoomEmployee
import com.nick_sib.kursovikpopularlibraries.mvp.view.RoomViewDetails
import io.reactivex.rxjava3.core.Scheduler
import io.reactivex.rxjava3.schedulers.Schedulers
import moxy.MvpPresenter
import ru.terrakok.cicerone.Router
import javax.inject.Inject

/**Часть данных сотрудника загружается из инстанса (RoomEmployee) фрагмента,
 * часть берется из БД (Specialty)*/

class EmployeeDetailsPresenter(
        private val employeeData: RoomEmployee
): MvpPresenter<RoomViewDetails<List<String>>>() {

    private val errorMessage = "Данные пользователя не загружены"

    @Inject
    lateinit var employeeDetailsCache: IRoomEmployeeDetailsCache
    @Inject
    lateinit var mainThreadScheduler: Scheduler
    @Inject
    lateinit var router: Router


    private fun loadData(){
        viewState.initView()
        employeeData.id?.run {
            employeeDetailsCache.getSpecialtyByEmployeeId(this)
                    .subscribeOn(Schedulers.io())
                    .observeOn(mainThreadScheduler)
                    .subscribe({
                        viewState.updateData(it)
                    }, {
                        it?.message?.run {
                            viewState.showError(this)
                        }
                    })
        } ?: viewState.showError(errorMessage)
    }

    override fun onFirstViewAttach() {
        super.onFirstViewAttach()
        viewState.initView()
        loadData()
    }

    override fun onDestroy() {
        super.onDestroy()
        viewState.release()
    }

}