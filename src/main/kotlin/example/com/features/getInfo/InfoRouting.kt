package example.com.features.getInfo


import example.com.database.users.UserDTO
import io.ktor.server.application.*
import io.ktor.server.response.*
import io.ktor.server.routing.*

fun Application.configureInfoRouting(){
    routing {
        get("/getInfo"){
            val infoController = InfoController(call)
            infoController.loginRecieve()
        }
    }
}