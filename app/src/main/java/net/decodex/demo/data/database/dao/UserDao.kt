package net.decodex.demo.data.database.dao

import androidx.lifecycle.LiveData
import androidx.room.*
import net.decodex.demo.data.database.model.User

@Dao
interface UserDao {
    @Query("SELECT * FROM user")
    suspend fun getAll(): List<User>

    @Query("SELECT * FROM user WHERE isFavorite = 1")
    suspend fun getAllFavorites(): List<User>

    @Query("SELECT * FROM user WHERE email = :email")
    fun getUserInfoLive(email: String): LiveData<User>

    @Insert
    suspend fun insertAll(vararg user: User)

    @Delete
    suspend fun delete(user: User)

    @Update
    suspend fun updateUser(vararg users: User)
}