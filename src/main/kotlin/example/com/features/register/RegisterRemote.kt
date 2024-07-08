package example.com.features.register

import kotlinx.serialization.Serializable

@Serializable
data class RegistrationRecieveRemote(
    val login: String,
    val password: String,
    val email: String,
    val name: String,
    val secondname: String,
    val surname: String,
    val phone: String,
)
