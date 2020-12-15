package com.nick_sib.kursovikpopularlibraries.ui.activities

import android.os.Bundle
import android.view.View
import com.google.android.material.snackbar.Snackbar
import com.nick_sib.kursovikpopularlibraries.App
import com.nick_sib.kursovikpopularlibraries.R
import com.nick_sib.kursovikpopularlibraries.databinding.ActivityMainBinding
import com.nick_sib.kursovikpopularlibraries.di.main.MainSubComponent
import com.nick_sib.kursovikpopularlibraries.mvp.model.throws.ThrowableConnectAndDataBase
import com.nick_sib.kursovikpopularlibraries.mvp.model.throws.ThrowableConnectOnly
import com.nick_sib.kursovikpopularlibraries.mvp.presenter.MainPresenter
import com.nick_sib.kursovikpopularlibraries.mvp.view.RetrofitView
import com.nick_sib.kursovikpopularlibraries.navigation.Screens
import com.nick_sib.kursovikpopularlibraries.ui.BackButtonListener
import moxy.MvpAppCompatActivity
import moxy.ktx.moxyPresenter
import ru.terrakok.cicerone.NavigatorHolder
import ru.terrakok.cicerone.Router
import ru.terrakok.cicerone.android.support.SupportAppNavigator
import javax.inject.Inject


class MainActivity: MvpAppCompatActivity(), RetrofitView {

    @Inject
    lateinit var navigatorHolder: NavigatorHolder
    @Inject
    lateinit var router: Router

    private lateinit var binding: ActivityMainBinding
    private var snack: Snackbar? = null

    private val navigator = SupportAppNavigator(this, supportFragmentManager, R.id.container)
    private val navigatorLand = SupportAppNavigator(this, supportFragmentManager, R.id.pageContainer)

    private var mainSubComponent: MainSubComponent? = null

    private val presenter: MainPresenter by moxyPresenter {
        mainSubComponent = App.instance.initMainSubComponent()
        MainPresenter().apply {
            mainSubComponent?.inject(this)
        }
    }

    init {
        App.instance.appComponent.inject(this)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
    }

    override fun onResumeFragments() {
        super.onResumeFragments()
        navigatorHolder.setNavigator(navigator)
    }

    override fun onPause() {
        super.onPause()
        navigatorHolder.removeNavigator()
    }

    override fun onBackPressed() {
        binding.pageContainer?.run {
            closeApp()
        }
        supportFragmentManager.fragments.forEach {
            if (it is BackButtonListener && it.backPressed()) {
                return
            }
        }
        router.exit()
    }

    override fun beginLoading() {
        binding.progressBar.visibility = View.VISIBLE

    }

    override fun endLoading() {
        binding.run {
            pageContainer?.run {
                navigatorHolder.setNavigator(navigatorLand)
                router.newRootScreen(Screens.SpecialtysScreen())
                navigatorHolder.setNavigator(navigator)
            } ?:
                router.newRootScreen(Screens.SpecialtysScreen())
        }
        binding.progressBar.visibility = View.GONE
        release()
    }

    override fun showError(error: Throwable) {
        when (error) {
            is ThrowableConnectAndDataBase -> {
                showSnack(resources.getString(R.string.snack_message_internet_and_db), R.string.snack_button_close) { closeApp() }
            }
            is ThrowableConnectOnly -> {
                showSnack(resources.getString(R.string.snack_message_internet_only), R.string.snack_button_gotit)
            }
            else -> {
                showSnack("${error.message}!", R.string.snack_button_gotit)
            }
        }
    }

    override fun hideShack() {
        snack?.dismiss()
        binding.apply {
            progressBar.visibility = View.GONE
        }
    }

    private fun showSnack(messageText: String, buttonText: Int, onItemClick: (() -> Unit)? = null) {
        snack = Snackbar.make(binding.root, messageText, Snackbar.LENGTH_INDEFINITE)
                .setAction(buttonText) {
                    onItemClick?.invoke()
                }.apply {
                    show()
                }
    }

    private fun closeApp() {
        this@MainActivity.finish()
    }

    fun release() {
        mainSubComponent = null
        App.instance.releaseMainSubComponent()
    }

}