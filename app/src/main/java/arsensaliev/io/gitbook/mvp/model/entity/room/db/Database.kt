package arsensaliev.io.gitbook.mvp.model.entity.room.db

import android.content.Context
import androidx.room.Room
import androidx.room.RoomDatabase
import arsensaliev.io.gitbook.mvp.model.entity.room.RoomGithubRepository
import arsensaliev.io.gitbook.mvp.model.entity.room.RoomGithubUser
import arsensaliev.io.gitbook.mvp.model.entity.room.dao.RepositoryDao
import arsensaliev.io.gitbook.mvp.model.entity.room.dao.UserDao

@androidx.room.Database(
    entities = [
        RoomGithubUser::class,
        RoomGithubRepository::class
    ],
    version = 2
)
abstract class Database : RoomDatabase() {
    abstract val userDao: UserDao
    abstract val repositoryDao: RepositoryDao

    companion object {
        private const val DB_NAME = "database.db"
        private var instance: Database? = null

        fun getInstance() =
            instance ?: throw  IllegalStateException("Database has not been created")

        fun create(context: Context) {
            if (instance == null) {
                instance = Room.databaseBuilder(
                    context,
                    Database::class.java,
                    DB_NAME
                ).build()
            }
        }
    }
}