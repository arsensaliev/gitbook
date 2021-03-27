package arsensaliev.io.gitbook.mvp.model.repo

import arsensaliev.io.gitbook.mvp.model.api.IDataSource
import io.reactivex.rxjava3.core.Single
import io.reactivex.rxjava3.schedulers.Schedulers

class RetrofitGithubUsersRepo(val api: IDataSource) : IGithubUsersRepo {

    override fun getUsers() = api.getUsers().subscribeOn(Schedulers.io())

}