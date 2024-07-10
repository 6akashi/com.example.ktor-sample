package example.com.features.getInfo

import example.com.database.users.UserDTO
import example.com.database.users.Users
import io.ktor.http.*
import io.ktor.server.application.*
import io.ktor.server.request.*
import io.ktor.server.response.*

class InfoController(val call: ApplicationCall) {
    suspend fun loginRecieve() {
        val receive = call.receive<InfoRecieve>()
        val userDTO: UserDTO? = Users.fetchUser(receive.login)
        if (userDTO != null) {
            call.respond(
                InfoResponse(
                    email = userDTO.email,
                    name = userDTO.name.toString(),
                    secondname = userDTO.secondname,
                    surname = userDTO.surname.toString(),
                    phone = userDTO.phone
                )
            )
        }else{
            call.respond(HttpStatusCode.BadRequest, "User didnt found")
        }
    }
}