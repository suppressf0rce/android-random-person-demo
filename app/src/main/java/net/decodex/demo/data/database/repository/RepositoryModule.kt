package net.decodex.demo.data.database.repository

import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent
import net.decodex.demo.data.api.services.RandomUserApiService
import net.decodex.demo.data.database.dao.UserDao

@Module
@InstallIn(ViewModelComponent::class)
object RepositoryModule {

    @Provides
    fun provideUserRepository(api: RandomUserApiService, dao: UserDao): UserRepository {
        return UserRepository(api, dao)
    }
}