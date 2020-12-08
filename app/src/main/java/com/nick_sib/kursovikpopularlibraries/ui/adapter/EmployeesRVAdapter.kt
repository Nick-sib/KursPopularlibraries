package com.nick_sib.kursovikpopularlibraries.ui.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import androidx.recyclerview.widget.RecyclerView
import com.nick_sib.kursovikpopularlibraries.R
import com.nick_sib.kursovikpopularlibraries.databinding.ItemEmployeeBinding
import com.nick_sib.kursovikpopularlibraries.defBirthday
import com.nick_sib.kursovikpopularlibraries.getAge
import com.nick_sib.kursovikpopularlibraries.mvp.presenter.list.IEmployeeListPresenter
import com.nick_sib.kursovikpopularlibraries.mvp.view.image.IImageLoader
import com.nick_sib.kursovikpopularlibraries.mvp.view.list.EmployeeItemView
import kotlinx.android.extensions.LayoutContainer

class EmployeesRVAdapter(
        private val presenter: IEmployeeListPresenter,
        private val imageLoader: IImageLoader<ImageView>
) : RecyclerView.Adapter<EmployeesRVAdapter.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) =
        ViewHolder(LayoutInflater.from(parent.context)
                .inflate(R.layout.item_employee, parent, false))

    override fun getItemCount() = presenter.getCount()

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.pos = position
        holder.containerView.setOnClickListener {
            presenter.itemClickListener?.invoke(holder)
        }
        presenter.bindView(holder)
    }

    inner class ViewHolder(
            override val containerView: View
    ) : RecyclerView.ViewHolder(containerView), LayoutContainer, EmployeeItemView {
        private var binding: ItemEmployeeBinding = ItemEmployeeBinding.bind(containerView)
        override fun setName(text: String) {
            binding.tvFName.text = text
        }

        override fun setAge(text: String) {
            val age = text.getAge()
            binding.tvAge.text = if (age < 0)
                defBirthday
            else
                containerView.resources.getQuantityString(R.plurals.employee_photo_standing, age, age)
        }

        override fun loadAvatar(url: String) {
            imageLoader.loadInto(url, binding.ivEmployees)
        }

        override var pos = -1
    }
}