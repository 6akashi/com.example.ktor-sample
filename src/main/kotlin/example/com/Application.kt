package example.com

import example.com.features.getInfo.configureInfoRouting
import example.com.features.login.configureLoginRouting
import example.com.features.register.configureRegisterRouting
import example.com.plugins.*
import io.ktor.server.application.*
import io.ktor.server.cio.*
import io.ktor.server.engine.*
import org.jetbrains.exposed.sql.Database

fun main() {
    Database.connect("jdbc:postgresql://localhost:5432/test", driver = "org.postgresql.Driver", user = "postgres",
        password = "Hardpass")
    embeddedServer(CIO, port = 8080, host = "0.0.0.0", module = Application::module)
        .start(wait = true)
}

fun Application.module() {
    configureSerialization()
    configureInfoRouting()
    configureRegisterRouting()
    configureLoginRouting()
}
