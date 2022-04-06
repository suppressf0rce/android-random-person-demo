package net.decodex.demo.viewmodels

import androidx.lifecycle.ViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import net.decodex.demo.data.database.repository.RepositoryModule
import net.decodex.demo.data.database.repository.UserRepository
import javax.inject.Inject

/**
 * An [ViewModel] class that injects [UserRepository]. It is used to get all users from the database
 * @param userRepository an instance of [UserRepository] which is injected by hilt and it provided
 * inside the [RepositoryModule]
 * @author Dejan Radmanovic
 * @since 1.0.0
 */
@HiltViewModel
class HistoryViewModel @Inject constructor(
    private val userRepository: UserRepository
) : ViewModel() {

    fun getAllPersons() = userRepository.getAllUsers()
}