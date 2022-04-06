package net.decodex.demo.data.api.dto

data class RandomUserResponseInfo(
    val seed: String,
    val results: Int,
    val page: Int,
    val version: String
)
