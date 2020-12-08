package com.nick_sib.kursovikpopularlibraries.ui.fragments

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import com.nick_sib.kursovikpopularlibraries.App
import com.nick_sib.kursovikpopularlibraries.databinding.FragmentSpecialtysBinding
import com.nick_sib.kursovikpopularlibraries.mvp.presenter.SpecialitysPresenter
import com.nick_sib.kursovikpopularlibraries.mvp.view.RoomView
import com.nick_sib.kursovikpopularlibraries.ui.adapter.SpecialtysPageAdapter
import moxy.MvpAppCompatFragment
import moxy.ktx.moxyPresenter

class SpecialtysPageFragment : MvpAppCompatFragment(), RoomView {

//    companion object {
//        fun newInstance() = SpecialtysPageFragment()
//    }

    private var binding: FragmentSpecialtysBinding? = null

    private val presenter: SpecialitysPresenter by moxyPresenter {
        SpecialitysPresenter().apply {
            App.instance.appComponent.inject(this)
        }
    }

    private val adapter: SpecialtysPageAdapter by lazy {
        SpecialtysPageAdapter(presenter.specialtysPagePresenter, parentFragmentManager)
    }

    override fun onCreateView(
            inflater: LayoutInflater,
            container: ViewGroup?,
            savedInstanceState: Bundle?
    ): View = FragmentSpecialtysBinding.inflate(inflater, container, false).let {
        binding = it
        it.root
    }

    override fun onPause() {
        super.onPause()
        Log.d("myLOG", "onPause: ")
    }

    override fun onResume() {
        super.onResume()
        Log.d("myTAG", "onResume: ")
    }


    override fun onDestroy() {
        super.onDestroy()
        binding = null

    }

    override fun initView() {
        binding?.run {
            viewPager.adapter = adapter
            tabs.setupWithViewPager(viewPager)
        }
    }

    override fun updateData() {
        adapter.notifyDataSetChanged()
    }

    override fun showError(errorText: String) {
        Toast.makeText(context, errorText, Toast.LENGTH_LONG).show()
    }
}