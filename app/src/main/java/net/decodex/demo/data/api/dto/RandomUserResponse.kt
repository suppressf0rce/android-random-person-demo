package net.decodex.demo.data.api.dto

import net.decodex.demo.data.database.model.User

data class RandomUserResponse(
    val results: List<User>,
    val info: RandomUserResponseInfo
)