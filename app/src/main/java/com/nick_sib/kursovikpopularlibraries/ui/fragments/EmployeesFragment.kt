package com.nick_sib.kursovikpopularlibraries.ui.fragments

import android.content.res.Configuration
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import com.nick_sib.kursovikpopularlibraries.databinding.FragmentEmployeesBinding
import com.nick_sib.kursovikpopularlibraries.mvp.presenter.EmployeesPresenter
import com.nick_sib.kursovikpopularlibraries.mvp.view.image.GlideImageLoader
import com.nick_sib.kursovikpopularlibraries.mvp.view.RoomView
import com.nick_sib.kursovikpopularlibraries.ui.adapter.EmployeesRVAdapter
import moxy.MvpAppCompatFragment
import moxy.ktx.moxyPresenter

class EmployeesFragment : MvpAppCompatFragment(), RoomView {

    companion object {
        private val EXTRA_DATA = EmployeesFragment::class.java.name + "EXTRA_DATA"

        fun newInstance(data: Long) = EmployeesFragment().apply {
            arguments = Bundle().apply {
                putLong(EXTRA_DATA, data)
            }
        }
    }

    private val isLandscape: Boolean by lazy {
        resources.configuration.orientation == Configuration.ORIENTATION_LANDSCAPE
    }

    private var binding: FragmentEmployeesBinding? = null

    private var specialtyId: Long = 0

    private val presenter: EmployeesPresenter by moxyPresenter {
        EmployeesPresenter(specialtyId, isLandscape)
    }

    private val adapter: EmployeesRVAdapter by lazy {
        EmployeesRVAdapter(presenter.employeesListPresenter, GlideImageLoader())
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        specialtyId = arguments?.getLong(EXTRA_DATA)
                ?: let {
                    showError("Извините данные не переданы")
                    0L
                }
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
            inflater: LayoutInflater,
            container: ViewGroup?,
            savedInstanceState: Bundle?
    ): View = FragmentEmployeesBinding.inflate(inflater, container, false).let {
        binding = it
        it.root
    }

    override fun onDestroy() {
        super.onDestroy()
        binding = null
    }

    override fun initView() {
        binding?.run {
            rvEmployees.adapter = adapter
        }

    }

    override fun updateData() {
        adapter.notifyDataSetChanged()
    }

    override fun showError(errorText: String) {
        Toast.makeText(context, errorText, Toast.LENGTH_LONG).show()
    }

}