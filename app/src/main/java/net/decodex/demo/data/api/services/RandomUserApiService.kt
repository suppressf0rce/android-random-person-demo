package net.decodex.demo.data.api.services

import retrofit2.http.GET
import net.decodex.demo.data.api.dto.RandomUserResponse

interface RandomUserApiService {

    @GET("api")
    suspend fun generateNewUser(): RandomUserResponse
}