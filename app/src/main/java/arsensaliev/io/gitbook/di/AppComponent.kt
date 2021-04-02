package arsensaliev.io.gitbook.di

import arsensaliev.io.gitbook.di.module.api.ApiModule
import arsensaliev.io.gitbook.di.module.app.AppModule
import arsensaliev.io.gitbook.di.module.cache.CacheModule
import arsensaliev.io.gitbook.di.module.cicerone.CiceroneModule
import arsensaliev.io.gitbook.di.module.image.ImageModule
import arsensaliev.io.gitbook.di.module.repoModule.RepoModule
import arsensaliev.io.gitbook.mvp.presenter.main.MainPresenter
import arsensaliev.io.gitbook.mvp.presenter.user.UserPresenter
import arsensaliev.io.gitbook.mvp.presenter.users.UsersPresenter
import arsensaliev.io.gitbook.ui.activity.MainActivity
import arsensaliev.io.gitbook.ui.adapter.user.UserRVAdapter
import arsensaliev.io.gitbook.ui.adapter.users.UsersRVAdapter
import dagger.Component
import javax.inject.Singleton

@Singleton
@Component(
    modules = [
        AppModule::class,
        CiceroneModule::class,
        ApiModule::class,
        RepoModule::class,
        CacheModule::class,
        ImageModule::class
    ]
)
interface AppComponent {
    fun inject(mainActivity: MainActivity)
    fun inject(mainPresenter: MainPresenter)
    fun inject(usersPresenter: UsersPresenter)
    fun inject(usersRVAdapter: UsersRVAdapter)
    fun inject(userPresenter: UserPresenter)
    fun inject(userRVAdapter: UserRVAdapter)
}