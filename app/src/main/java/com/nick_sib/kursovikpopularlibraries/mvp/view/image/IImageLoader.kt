package com.nick_sib.kursovikpopularlibraries.mvp.view.image

interface IImageLoader<T> {
    fun loadInto(url: String, container: T)
}