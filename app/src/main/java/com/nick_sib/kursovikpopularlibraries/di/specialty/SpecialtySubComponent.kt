package com.nick_sib.kursovikpopularlibraries.di.specialty


import com.nick_sib.kursovikpopularlibraries.di.specialty.module.SpecialtyModule
import com.nick_sib.kursovikpopularlibraries.mvp.presenter.SpecialitysPresenter
import dagger.Subcomponent


@SpecialtyScope
@Subcomponent(
        modules = [
            SpecialtyModule::class
        ]
)

interface SpecialtySubComponent {
    fun inject(specialityPresenter: SpecialitysPresenter)
}