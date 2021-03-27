package arsensaliev.io.gitbook.mvp.model.api

import arsensaliev.io.gitbook.mvp.model.entity.GithubUser
import io.reactivex.rxjava3.core.Single
import retrofit2.http.GET

interface IDataSource {

    @GET("users")
    fun getUsers() : Single<List<GithubUser>>

}