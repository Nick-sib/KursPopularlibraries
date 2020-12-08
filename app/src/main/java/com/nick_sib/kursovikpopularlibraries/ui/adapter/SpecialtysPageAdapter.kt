package com.nick_sib.kursovikpopularlibraries.ui.adapter

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentPagerAdapter
import com.nick_sib.kursovikpopularlibraries.mvp.presenter.list.ISpecialtysPagePresenter
import com.nick_sib.kursovikpopularlibraries.ui.fragments.EmployeesFragment

class SpecialtysPageAdapter(
        private val presenter: ISpecialtysPagePresenter,
        fm: FragmentManager
) : FragmentPagerAdapter(fm, BEHAVIOR_RESUME_ONLY_CURRENT_FRAGMENT) {

    override fun getCount() = presenter.getCount()

    override fun getPageTitle(position: Int) = presenter.getTitle(position)

    override fun getItem(position: Int): Fragment =
            EmployeesFragment.newInstance(presenter.getId(position))
}