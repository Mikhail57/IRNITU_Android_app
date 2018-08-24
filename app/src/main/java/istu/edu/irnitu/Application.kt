package istu.edu.irnitu

import android.app.Application
import istu.edu.irnitu.di.AppComponent
import istu.edu.irnitu.di.DaggerAppComponent
import istu.edu.irnitu.di.modules.ContextModule

class Application : Application() {

    override fun onCreate() {
        super.onCreate()

        appComponent = DaggerAppComponent.builder()
                .contextModule(ContextModule(this))
                .build()
    }

    companion object {
        lateinit var appComponent: AppComponent
    }

}