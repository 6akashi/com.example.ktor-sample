package example.com.features.login

import kotlinx.serialization.Serializable

@Serializable
data class LoginRemote(
    val login: String,
    val password: String
)

