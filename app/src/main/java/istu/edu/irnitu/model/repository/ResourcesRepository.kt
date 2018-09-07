package istu.edu.irnitu.model.repository

import io.reactivex.Single
import istu.edu.irnitu.entity.Resource
import istu.edu.irnitu.model.system.SchedulersProvider

interface ResourcesRepository {
    fun getResources(): Single<List<Resource>>
}

class ResourcesStubRepository(private val schedulers: SchedulersProvider) : ResourcesRepository {
    override fun getResources(): Single<List<Resource>> = Single.just<List<Resource>>(arrayListOf(
            Resource("Сайт ИРНИТУ", "https://istu.edu", "https://elc.istu.edu/static/mobile_app/logo_transparent.png"),
            Resource("Электронное образование", "https://elc.istu.edu/", "https://elc.istu.edu/static/mobile_app/elearn.gif"),
            Resource("Электронный каталог", "http://84.237.19.2:8081/Opac/", "http://84.237.19.2:8081/content/ebs.png"),
            Resource("Электронная библиотека", "http://elib.istu.edu/", "https://elc.istu.edu/static/mobile_app/elib.png"),
            Resource("Сайт библиотеки", "http://library.istu.edu/", "https://elc.istu.edu/static/mobile_app/lib.jpg"),
            Resource("Абитуриенту", "http://www.istu.edu/abiturientu/", "http://www.istu.edu/upload/iblock/fed/abiturientu.jpg"))
    )
            .subscribeOn(schedulers.io())
            .observeOn(schedulers.ui())
}

//class ResourcesRemoteRepository(private val schedulers: SchedulersProvider) : ResourcesRepository {
//    override fun getResources(): Single<List<Resource>> {
//
//    }
//
//}