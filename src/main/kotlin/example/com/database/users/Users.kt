@file:Suppress("DEPRECATION")

package example.com.database.users
import org.jetbrains.exposed.sql.Table
import org.jetbrains.exposed.sql.insert
import org.jetbrains.exposed.sql.select
import org.jetbrains.exposed.sql.transactions.transaction

object Users : Table("users") {
    private val login = Users.varchar("login", 25)
    private val password = Users.varchar("password", 25)
    private val name = Users.varchar("name", 25)
    private val secondname = Users.varchar("secondname", 25)
    private val surname = Users.varchar("surname", 25)
    private val email = Users.varchar("email", 25)
    private val phone = Users.varchar("phone", 15)

    fun insert(UserDTO: UserDTO) {
        transaction {
            Users.insert {
                it[login] = UserDTO.login
                it[password] = UserDTO.password
                it[name] = UserDTO.name ?: ""
                it[secondname] = UserDTO.secondname ?: ""
                it[surname] = UserDTO.surname ?: ""
                it[email] = UserDTO.email
                it[phone] = UserDTO.phone
            }
        }
    }

    fun fetchUser(login: String): UserDTO? {
        return try {
            transaction { val userModel = Users.select { Users.login.eq(login) }.single()
                UserDTO(
                    login = userModel[Users.login],
                    password = userModel[password],
                    name = userModel[name],
                    secondname = userModel[secondname],
                    surname = userModel[surname],
                    email = userModel[email],
                    phone = userModel[phone]
                ) }

        }catch(e: Exception){
            null
        }

    }
}