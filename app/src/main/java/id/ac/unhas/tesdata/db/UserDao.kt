package id.ac.unhas.tesdata.db

import androidx.room.*

@Dao
interface UserDao {
    @Query("SELECT * FROM userinfo ORDER BY id DESC")
    fun getAllUserInfo(): List<UserEntity>?

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    fun insertUser(user: UserEntity?)

    @Delete
    fun deleteUser(user: UserEntity?)

    @Update
    fun updateUser(user:UserEntity?)
}