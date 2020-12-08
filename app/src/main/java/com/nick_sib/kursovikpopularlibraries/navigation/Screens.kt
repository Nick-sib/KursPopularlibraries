package com.nick_sib.kursovikpopularlibraries.navigation


import com.nick_sib.kursovikpopularlibraries.mvp.model.entity.room.RoomEmployee
import com.nick_sib.kursovikpopularlibraries.ui.fragments.EmployeeDetailsFragment
import com.nick_sib.kursovikpopularlibraries.ui.fragments.SpecialtysPageFragment
import ru.terrakok.cicerone.android.support.SupportAppScreen

class Screens {
    class SpecialtysScreen : SupportAppScreen() {
        override fun getFragment() = SpecialtysPageFragment()//.newInstance()
    }

    class EmployeeDetailsScreen(private var employeeData: RoomEmployee) : SupportAppScreen() {
        override fun getFragment() = EmployeeDetailsFragment.newInstance(employeeData)
    }
}