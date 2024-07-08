package example.com.features.register

import example.com.database.tokens.TokenDTO
import example.com.database.tokens.Tokens
import example.com.database.users.UserDTO
import example.com.database.users.Users
import io.ktor.http.*
import io.ktor.server.application.*
import io.ktor.server.request.*
import io.ktor.server.response.*
import java.util.UUID

class RegisterController(val call: ApplicationCall) {

    suspend fun registerNewUser(){
        val registerRecieveRemote = call.receive<RegistrationRecieveRemote>()
        val userDTO = Users.fetchUser(registerRecieveRemote.login)

        if(userDTO != null){
            call.respond(HttpStatusCode.Conflict, "User already exist")
        }else{
            val token = UUID.randomUUID().toString()
            Users.insert(
                UserDTO(
                    login = registerRecieveRemote.login,
                    password = registerRecieveRemote.password,
                    email = registerRecieveRemote.email,
                    name = registerRecieveRemote.name,
                    secondname = registerRecieveRemote.secondname,
                    surname = registerRecieveRemote.surname,
                    phone = registerRecieveRemote.phone
                )
            )
            Tokens.insert(
                TokenDTO(
                    id = UUID.randomUUID().toString(),
                    login = registerRecieveRemote.login,
                    token = token
                )
            )
        }
    }

}