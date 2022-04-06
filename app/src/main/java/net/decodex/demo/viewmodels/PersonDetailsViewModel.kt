package net.decodex.demo.viewmodels

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import net.decodex.demo.data.database.model.User
import net.decodex.demo.data.database.repository.RepositoryModule
import net.decodex.demo.data.database.repository.UserRepository
import javax.inject.Inject

/**
 * An [ViewModel] class that injects [UserRepository] and [SavedStateHandle]
 * It is used to get user info by email and update user information inside the database
 * @param userRepository an instance of [UserRepository] which is injected by hilt and it provided
 * inside the [RepositoryModule]
 * @param savedStateHandle an instance of [SavedStateHandle] which is injected by hilt and it is
 * provided by the androidX lifecycle library, and it is used for getting navigation arguments
 * @author Dejan Radmanovic
 * @since 1.0.0
 */
@HiltViewModel
class PersonDetailsViewModel @Inject constructor(
    private val userRepository: UserRepository,
    private val savedStateHandle: SavedStateHandle
) : ViewModel() {

    /**
     * Retrieves the user info from navigation arguments passed to the fragment by Navigation
     * User is received from [UserRepository] by email address
     */
    fun getUserInfo() = userRepository.getUserInfo(savedStateHandle.get<String>("email"))

    /**
     * Updates user favorite status by the [UserRepository]
     */
    fun updateUserFavoriteStatus(user: User) {
        viewModelScope.launch(Dispatchers.IO) {
            userRepository.updateUserFavoriteStatus(user)
        }
    }
}