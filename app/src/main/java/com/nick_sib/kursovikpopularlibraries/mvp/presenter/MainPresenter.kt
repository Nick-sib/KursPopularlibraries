package com.nick_sib.kursovikpopularlibraries.mvp.presenter

import com.nick_sib.kursovikpopularlibraries.mvp.model.repo.IRepoAllData
import com.nick_sib.kursovikpopularlibraries.mvp.view.RetrofitView
import io.reactivex.rxjava3.core.Scheduler
import moxy.MvpPresenter
import ru.terrakok.cicerone.Router
import javax.inject.Inject

class MainPresenter : MvpPresenter<RetrofitView>() {

    @Inject lateinit var usersRepo: IRepoAllData
    @Inject lateinit var router: Router
    @Inject lateinit var mainThreadScheduler: Scheduler

    override fun onFirstViewAttach() {
        super.onFirstViewAttach()
        loadData()
    }

    private fun loadData() {
        viewState.beginLoading()
        usersRepo.loadAllData()
            .observeOn(mainThreadScheduler)
            .subscribe({
                viewState.endLoading()
            }, {
                it?.message?.run {
                    viewState.showError(this)
                }
            })
    }

    fun backClicked() {
        router.exit()
    }
}