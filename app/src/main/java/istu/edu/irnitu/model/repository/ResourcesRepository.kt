package istu.edu.irnitu.model.repository

import io.reactivex.Single
import istu.edu.irnitu.entity.Resource
import istu.edu.irnitu.model.system.SchedulersProvider

class ResourcesRepository(private val schedulers: SchedulersProvider) {
    fun getResources(): Single<List<Resource>> = Single.just<List<Resource>>(arrayListOf(
            Resource("Сайт ИРНИТУ", "https://istu.edu", ""),
            Resource("Электронное образование", "https://istu.edu", ""),
            Resource("Электронный каталог", "https://istu.edu", ""),
            Resource("Электронная библиотека", "https://istu.edu", ""),
            Resource("Сайт библиотеки", "https://istu.edu", ""),
            Resource("Абитуриенту", "https://istu.edu", ""))
    )
            .subscribeOn(schedulers.io())
            .observeOn(schedulers.ui())
}