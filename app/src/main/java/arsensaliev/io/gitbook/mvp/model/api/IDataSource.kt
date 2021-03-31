package arsensaliev.io.gitbook.mvp.model.api

import arsensaliev.io.gitbook.mvp.model.entity.GithubRepository
import arsensaliev.io.gitbook.mvp.model.entity.GithubUser
import io.reactivex.rxjava3.core.Single
import retrofit2.http.GET
import retrofit2.http.Url

interface IDataSource {

    @GET("users")
    fun getUsers(): Single<List<GithubUser>>

    @GET
    fun getRepositories(@Url url: String): Single<List<GithubRepository>>
}