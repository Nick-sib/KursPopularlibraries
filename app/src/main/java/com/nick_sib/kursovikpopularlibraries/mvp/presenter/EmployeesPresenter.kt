package com.nick_sib.kursovikpopularlibraries.mvp.presenter

import com.nick_sib.kursovikpopularlibraries.App
import com.nick_sib.kursovikpopularlibraries.mvp.model.cache.IRoomDataCache
import com.nick_sib.kursovikpopularlibraries.mvp.model.entity.room.RoomEmployee
import com.nick_sib.kursovikpopularlibraries.mvp.presenter.list.IEmployeeListPresenter
import com.nick_sib.kursovikpopularlibraries.mvp.view.RoomView
import com.nick_sib.kursovikpopularlibraries.mvp.view.list.EmployeeItemView
import com.nick_sib.kursovikpopularlibraries.navigation.Screens
import io.reactivex.rxjava3.core.Scheduler
import io.reactivex.rxjava3.schedulers.Schedulers
import moxy.MvpPresenter
import ru.terrakok.cicerone.Router
import javax.inject.Inject

class EmployeesPresenter(private val specialtyId: Long) : MvpPresenter<RoomView>() {

    @Inject
    lateinit var employeesCache: IRoomDataCache
    @Inject
    lateinit var mainThreadScheduler: Scheduler
    @Inject
    lateinit var router: Router

    init {
        App.instance.appComponent.inject(this)
    }

    class EmployeesListPresenter : IEmployeeListPresenter {
        val employees = mutableListOf<RoomEmployee>()
        override var itemClickListener: ((EmployeeItemView) -> Unit)? = null
        override fun getCount() = employees.size

        override fun bindView(view: EmployeeItemView) {
            val employee = employees[view.pos]
            view.setName("${employee.f_name} ${employee.l_name}")
            view.setAge(employee.birthday)
            view.loadAvatar(employee.avatr_url)
        }
    }

    val employeesListPresenter = EmployeesListPresenter()

    private fun loadData(){
        employeesCache.getEmployees(specialtyId)
                .subscribeOn(Schedulers.io())
                .observeOn(mainThreadScheduler)
                .subscribe({ employees ->
                    employeesListPresenter.employees.clear()
                    employeesListPresenter.employees.addAll(employees)
                    viewState.updateData()
                }, {
                    it?.message?.run {
                        viewState.showError(this)
                    }
                })
    }

    override fun onFirstViewAttach() {
        super.onFirstViewAttach()
        viewState.initView()
        loadData()

        employeesListPresenter.itemClickListener = { itemView ->
            val employee = employeesListPresenter.employees[itemView.pos]
            //Log.d("myLOG", "click: $employee")
            router.navigateTo(Screens.EmployeeDetailsScreen(employee))
        }
    }

}