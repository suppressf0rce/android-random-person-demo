package net.decodex.demo.data.database.repository

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.liveData
import net.decodex.demo.data.Resource
import net.decodex.demo.data.api.services.RandomUserApiService
import net.decodex.demo.data.database.dao.UserDao
import net.decodex.demo.data.database.model.User
import javax.inject.Inject

class UserRepository @Inject constructor(
    private val api: RandomUserApiService,
    private val dao: UserDao
) {

    fun generateRandomUser() = liveData {
        emit(Resource.loading(null))
        try {
            val user = api.generateNewUser().results.first()
            dao.insertAll(user)
            emit(Resource.success(user))
        } catch (ex: Exception) {
            emit(Resource.error(null, ex.message ?: ""))
        }
    }

    fun getAllUsers() = liveData {
        emit(Resource.loading(null))
        try {
            val user = dao.getAll()
            emit(Resource.success(user))
        } catch (ex: Exception) {
            emit(Resource.error(null, "Error while trying to get all users!"))
        }
    }

    fun getFavoriteUsers() = liveData {
        emit(Resource.loading(null))
        try {
            val user = dao.getAllFavorites()
            emit(Resource.success(user))
        } catch (ex: Exception) {
            emit(Resource.error(null, "Error while trying to get favorite users!"))
        }
    }

    suspend fun updateUserFavoriteStatus(user: User) {
        user.isFavorite = !user.isFavorite
        dao.updateUser(user)
    }

    fun getUserInfo(email: String?): LiveData<User> {
        return email?.let {
            dao.getUserInfoLive(it)
        }?: MutableLiveData()
    }
}