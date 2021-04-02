package arsensaliev.io.gitbook.di

import arsensaliev.io.gitbook.di.module.ApiModule.ApiModule
import arsensaliev.io.gitbook.di.module.RepoModule.RepoModule
import arsensaliev.io.gitbook.di.module.app.AppModule
import arsensaliev.io.gitbook.di.module.cache.CacheModule
import arsensaliev.io.gitbook.di.module.cicerone.CiceroneModule
import arsensaliev.io.gitbook.mvp.presenter.main.MainPresenter
import arsensaliev.io.gitbook.mvp.presenter.user.UserPresenter
import arsensaliev.io.gitbook.mvp.presenter.users.UsersPresenter
import arsensaliev.io.gitbook.ui.activity.MainActivity
import dagger.Component

@Component(
    modules = [
        AppModule::class,
        CiceroneModule::class,
        ApiModule::class,
        RepoModule::class,
        CacheModule::class
    ]
)
interface AppComponent {
    fun inject(mainActivity: MainActivity)
    fun inject(usersPresenter: UsersPresenter)
    fun inject(mainPresenter: MainPresenter)
    fun inject(userPresenter: UserPresenter)
}