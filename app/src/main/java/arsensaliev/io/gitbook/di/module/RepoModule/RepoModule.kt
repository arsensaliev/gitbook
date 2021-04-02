package arsensaliev.io.gitbook.di.module.RepoModule

import arsensaliev.io.gitbook.mvp.model.api.IDataSource
import arsensaliev.io.gitbook.mvp.model.cache.IGithubUsersCache
import arsensaliev.io.gitbook.mvp.model.network.INetworkStatus
import arsensaliev.io.gitbook.mvp.model.repo.IGithubUsersRepo
import arsensaliev.io.gitbook.mvp.model.repo.RetrofitGithubUsersRepo
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module
class RepoModule {

    @Provides
    @Singleton
    fun usersRepo(
        api: IDataSource,
        networkStatus: INetworkStatus,
        cache: IGithubUsersCache
    ): IGithubUsersRepo = RetrofitGithubUsersRepo(api, networkStatus, cache)
}