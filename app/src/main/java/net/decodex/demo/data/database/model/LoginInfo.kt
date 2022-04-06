package net.decodex.demo.data.database.model

import java.util.*

data class LoginInfo(
    val uuid: UUID,
    val username: String,
    val password: String,
    val salt: String,
    val md5: String,
    val sha1: String,
    val sha256: String
)
