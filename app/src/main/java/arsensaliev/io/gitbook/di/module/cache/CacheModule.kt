package arsensaliev.io.gitbook.di.module.cache

import androidx.room.Room
import arsensaliev.io.gitbook.mvp.model.cache.IGithubUsersCache
import arsensaliev.io.gitbook.mvp.model.cache.room.RoomGithubUsersCache
import arsensaliev.io.gitbook.mvp.model.entity.room.db.Database
import arsensaliev.io.gitbook.ui.App
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module
class CacheModule {

    @Provides
    @Singleton

    fun database(app: App): Database =
        Room.databaseBuilder(app, Database::class.java, Database.DB_NAME).build()

    @Provides
    @Singleton
    fun usersCache(db: Database): IGithubUsersCache = RoomGithubUsersCache(db)
}