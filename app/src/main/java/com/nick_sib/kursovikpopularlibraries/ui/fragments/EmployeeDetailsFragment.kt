package com.nick_sib.kursovikpopularlibraries.ui.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import com.nick_sib.kursovikpopularlibraries.App
import com.nick_sib.kursovikpopularlibraries.cropList
import com.nick_sib.kursovikpopularlibraries.databinding.FragmentEmployeeDetailsBinding
import com.nick_sib.kursovikpopularlibraries.mvp.model.entity.room.RoomEmployee
import com.nick_sib.kursovikpopularlibraries.mvp.presenter.EmployeeDetailsPresenter
import com.nick_sib.kursovikpopularlibraries.mvp.view.RoomViewDetails
import com.nick_sib.kursovikpopularlibraries.mvp.view.image.GlideImageLoader
import moxy.MvpAppCompatFragment
import moxy.ktx.moxyPresenter

class EmployeeDetailsFragment: MvpAppCompatFragment(), RoomViewDetails<List<String>> {


    companion object {
        private val EXTRA_DATA = EmployeeDetailsFragment::class.java.name + "EXTRA_DATA"

        fun newInstance(data: RoomEmployee) = EmployeeDetailsFragment().apply {
            arguments = Bundle().apply {
                putParcelable(EXTRA_DATA, data)
            }
        }
    }

    private lateinit var employee: RoomEmployee
    private var binding: FragmentEmployeeDetailsBinding? = null

    private val presenter: EmployeeDetailsPresenter by moxyPresenter {
        EmployeeDetailsPresenter(employee).apply {
            App.instance.appComponent.inject(this)
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        employee = arguments?.getParcelable(EXTRA_DATA)
                ?: let {
                    showError("Извините данные не переданы")
                    RoomEmployee(0,"","","","")
                }
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
            inflater: LayoutInflater,
            container: ViewGroup?,
            savedInstanceState: Bundle?
    ): View = FragmentEmployeeDetailsBinding.inflate(inflater, container, false).let {
        binding = it
        it.root
    }

    override fun onDestroy() {
        super.onDestroy()
        binding = null
    }


    override fun initView() {
        binding?.run {
            tvFName.text = employee.f_name
            tvLName.text = employee.l_name
            tvBirthday.text = employee.birthday
            GlideImageLoader().loadInto(employee.avatr_url,ivEmployees)
        }
    }

    override fun updateData(data: List<String>) {
        binding?.run {
            tvSpecialties.text =
                    data.toString().cropList()
        }
    }

    override fun showError(errorText: String) {
        Toast.makeText(context, errorText, Toast.LENGTH_LONG).show()
    }

}