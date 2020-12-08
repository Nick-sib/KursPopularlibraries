package com.nick_sib.kursovikpopularlibraries.mvp.presenter


import com.nick_sib.kursovikpopularlibraries.mvp.model.cache.IRoomDataCache
import com.nick_sib.kursovikpopularlibraries.mvp.model.entity.room.RoomSpecialty
import com.nick_sib.kursovikpopularlibraries.mvp.presenter.list.ISpecialtysPagePresenter
import com.nick_sib.kursovikpopularlibraries.mvp.view.RoomView
import io.reactivex.rxjava3.core.Scheduler
import io.reactivex.rxjava3.schedulers.Schedulers
import moxy.MvpPresenter
import javax.inject.Inject

class SpecialitysPresenter : MvpPresenter<RoomView>() {

    @Inject lateinit var specialtysCache: IRoomDataCache
    @Inject lateinit var mainThreadScheduler: Scheduler


    class SpecialtysPagePresenter: ISpecialtysPagePresenter {
        val specialtys = mutableListOf<RoomSpecialty>()

        override fun getCount() = specialtys.size
        override fun getTitle(position: Int) = specialtys[position].specialtyName
        override fun getId(position: Int) = specialtys[position].specialtyId
    }

    val specialtysPagePresenter = SpecialtysPagePresenter()

    private fun loadData(){
        specialtysCache.getSpecialty()
                .subscribeOn(Schedulers.io())
                .observeOn(mainThreadScheduler)
                .subscribe({ specialtys ->
                    specialtysPagePresenter.specialtys.clear()
                    specialtysPagePresenter.specialtys.addAll(specialtys)
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
    }

}