package example.com.features.getInfo

import kotlinx.serialization.Serializable

@Serializable
data class InfoResponse(
    val email: String,
    val name: String,
    val secondname: String?,
    val surname: String,
    val phone: String,
)

@Serializable
data class InfoRecieve(
    val login: String
)