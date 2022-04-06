package net.decodex.demo.data.api

import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import net.decodex.demo.data.api.services.RandomUserApiService

@Module
@InstallIn(SingletonComponent::class)
object ApiModule {

    @Provides
    fun provideRandomUserApiService(@BaseRetrofitInstance retrofit: Retrofit): RandomUserApiService {
        return retrofit.create(RandomUserApiService::class.java)
    }
}