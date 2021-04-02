package arsensaliev.io.gitbook.ui

import android.app.Application
import arsensaliev.io.gitbook.di.AppComponent
import arsensaliev.io.gitbook.di.DaggerAppComponent
import arsensaliev.io.gitbook.di.module.app.AppModule
import arsensaliev.io.gitbook.mvp.model.entity.room.db.Database

class App : Application() {
    companion object {
        lateinit var instance: App
    }

    lateinit var appComponent: AppComponent

    override fun onCreate() {
        super.onCreate()
        instance = this
        Database.create(this)

        appComponent = DaggerAppComponent
            .builder()
            .appModule(AppModule(this))
            .build()

    }


}