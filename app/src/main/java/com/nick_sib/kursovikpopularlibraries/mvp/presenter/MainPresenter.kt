package com.nick_sib.kursovikpopularlibraries.mvp.presenter


import com.nick_sib.kursovikpopularlibraries.mvp.model.cache.IRoomDataCache
import com.nick_sib.kursovikpopularlibraries.mvp.model.repo.IRepoAllData
import com.nick_sib.kursovikpopularlibraries.mvp.model.throws.ThrowableConnectAndDataBase
import com.nick_sib.kursovikpopularlibraries.mvp.model.throws.ThrowableConnectOnly
import com.nick_sib.kursovikpopularlibraries.mvp.view.RetrofitView
import com.nick_sib.kursovikpopularlibraries.navigation.Screens
import io.reactivex.rxjava3.core.Scheduler
import moxy.MvpPresenter
import ru.terrakok.cicerone.Router
import javax.inject.Inject

/**При загрузке пробуем загрузить данные из интернета если это не удалось
 * проверяем если данные в БД если они есть берём их но предупреждаем пользователя
 * если нет ни интернета не данных в БД предупреждаем пользователя и ждём иньернет соединения
 * */
class MainPresenter : MvpPresenter<RetrofitView>() {

    @Inject lateinit var router: Router
    @Inject lateinit var usersRepo: IRepoAllData
    @Inject lateinit var mainThreadScheduler: Scheduler
    @Inject lateinit var dataBaseCache: IRoomDataCache

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
            }, { error ->
                if (error is ThrowableConnectAndDataBase) {
                    getDataBaseEmployeesCount()
                } else {
                    viewState.showError(error)
                }
            })
    }

    //проверяем наличие данных в БД
    private fun getDataBaseEmployeesCount(){
        dataBaseCache.getEmployeesCount()
                .observeOn(mainThreadScheduler)
                .subscribe({ count ->
                    if (count != 0) {
                        viewState.showError(
                            ThrowableConnectOnly()
                        )
                        viewState.endLoading()
                    } else {
                        waitInternetConnection()
                    }
                }, {
                    viewState.showError(it)
                })
    }

    private fun waitInternetConnection() {
        viewState.showError(ThrowableConnectAndDataBase())
        usersRepo.waitInternet()
                .observeOn(mainThreadScheduler)
                .subscribe({
                    if (it) {
                        viewState.hideShack()
                        loadData()
                    }
                }, {
                    viewState.showError(it)
                })
    }

    fun showSpecialtyScreen(){
        router.newRootScreen(Screens.SpecialtysScreen())
    }

    fun backClicked() {
        router.exit()
    }
}