package example.com.features.login

import example.com.database.tokens.TokenDTO
import example.com.database.tokens.Tokens
import example.com.database.users.Users
import io.ktor.http.*
import io.ktor.server.request.*
import io.ktor.server.application.*
import io.ktor.server.response.*
import java.util.*

class LoginController(private val call: ApplicationCall) {
    suspend fun performLogin(){
        val recieve = call.receive<LoginRemote>()
        val userDTO = Users.fetchUser(recieve.login)

        if(userDTO == null){
            call.respond(HttpStatusCode.BadRequest, "User didnt found")
        }else if(userDTO.password == recieve.password){
            val token = UUID.randomUUID().toString()
            Tokens.insert(
                TokenDTO(
                    id = UUID.randomUUID().toString(),
                    login = recieve.login,
                    token = token
                )
            )
        }else{
            call.respond(HttpStatusCode.BadRequest, "Incorrect password")
        }
    }
}