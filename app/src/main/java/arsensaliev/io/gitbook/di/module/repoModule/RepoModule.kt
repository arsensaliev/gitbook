package arsensaliev.io.gitbook.di.module.repoModule

import arsensaliev.io.gitbook.mvp.model.api.IDataSource
import arsensaliev.io.gitbook.mvp.model.cache.IGithubRepositoriesCache
import arsensaliev.io.gitbook.mvp.model.cache.IGithubUsersCache
import arsensaliev.io.gitbook.mvp.model.network.INetworkStatus
import arsensaliev.io.gitbook.mvp.model.repo.IGithubRepositoriesRepo
import arsensaliev.io.gitbook.mvp.model.repo.IGithubUsersRepo
import arsensaliev.io.gitbook.mvp.model.repo.RetrofitGithubRepositoriesRepo
import arsensaliev.io.gitbook.mvp.model.repo.RetrofitGithubUsersRepo
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module
class RepoModule {

    @Singleton
    @Provides
    fun usersRepo(
        api: IDataSource,
        networkStatus: INetworkStatus,
        cache: IGithubUsersCache
    ): IGithubUsersRepo = RetrofitGithubUsersRepo(api, networkStatus, cache)

    @Singleton
    @Provides
    fun repositoriesRepo(
        api: IDataSource,
        networkStatus: INetworkStatus,
        cache: IGithubRepositoriesCache
    ): IGithubRepositoriesRepo = RetrofitGithubRepositoriesRepo(api, networkStatus, cache)
}