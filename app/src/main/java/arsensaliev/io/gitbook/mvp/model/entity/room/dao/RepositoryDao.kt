package arsensaliev.io.gitbook.mvp.model.entity.room.dao

import androidx.room.*
import arsensaliev.io.gitbook.mvp.model.entity.room.RoomGithubRepository

@Dao
interface RepositoryDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insert(repository: RoomGithubRepository)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insert(vararg repository: RoomGithubRepository)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insert(repository: List<RoomGithubRepository>)

    @Update
    fun update(repository: RoomGithubRepository)

    @Update
    fun update(vararg repository: RoomGithubRepository)

    @Update
    fun update(repository: List<RoomGithubRepository>)

    @Delete
    fun delete(repository: RoomGithubRepository)

    @Delete
    fun delete(vararg repository: RoomGithubRepository)

    @Delete
    fun delete(repository: List<RoomGithubRepository>)

    @Query("SELECT * FROM RoomGithubRepository")
    fun getAll(): List<RoomGithubRepository>

    @Query("SELECT * FROM RoomGithubRepository WHERE userId = :userId")
    fun findForUser(userId: String): List<RoomGithubRepository>
}