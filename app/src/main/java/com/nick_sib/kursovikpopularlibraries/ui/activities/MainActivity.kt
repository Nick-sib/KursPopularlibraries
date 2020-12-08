package com.nick_sib.kursovikpopularlibraries.ui.activities

import android.os.Bundle
import android.view.View
import android.widget.Toast
import com.nick_sib.kursovikpopularlibraries.App
import com.nick_sib.kursovikpopularlibraries.R
import com.nick_sib.kursovikpopularlibraries.databinding.ActivityMainBinding
import com.nick_sib.kursovikpopularlibraries.mvp.presenter.MainPresenter
import com.nick_sib.kursovikpopularlibraries.mvp.view.RetrofitView
import com.nick_sib.kursovikpopularlibraries.ui.BackButtonListener
import moxy.MvpAppCompatActivity
import moxy.ktx.moxyPresenter
import ru.terrakok.cicerone.NavigatorHolder
import ru.terrakok.cicerone.android.support.SupportAppNavigator
import javax.inject.Inject

class MainActivity: MvpAppCompatActivity(), RetrofitView {

    @Inject
    lateinit var navigatorHolder: NavigatorHolder

    private lateinit var binding: ActivityMainBinding

    private val navigator = SupportAppNavigator(this, supportFragmentManager, R.id.container)

    private val presenter: MainPresenter by moxyPresenter {
        MainPresenter().apply {
            App.instance.appComponent.inject(this)
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
        supportFragmentManager.fragments.forEach {
            if (it is BackButtonListener && it.backPressed()) {
                return
            }
        }
        presenter.backClicked()
    }

    override fun beginLoading() {
        binding.progressBar.visibility = View.VISIBLE
    }

    override fun endLoading() {
        binding.progressBar.visibility = View.GONE
    }

    override fun showError(errorText: String) {
        Toast.makeText(this, errorText, Toast.LENGTH_LONG).show()
        binding.apply {
            progressBar.visibility = View.GONE
        }
    }

}