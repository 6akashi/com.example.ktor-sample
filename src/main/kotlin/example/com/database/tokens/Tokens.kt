package example.com.database.tokens

import org.jetbrains.exposed.sql.Table
import org.jetbrains.exposed.sql.insert
import org.jetbrains.exposed.sql.transactions.transaction

object Tokens: Table("tokens") {
    private val id = Tokens.varchar("id", 50)
    private val login = Tokens.varchar("login", 25)
    private val token = Tokens.varchar("token", 50)

    fun insert(TokenDTO: TokenDTO) {
        transaction {
            Tokens.insert {
                it[Tokens.id] = TokenDTO.id
                it[Tokens.login] = TokenDTO.login
                it[Tokens.token] = TokenDTO.token
            }
        }
    }
}