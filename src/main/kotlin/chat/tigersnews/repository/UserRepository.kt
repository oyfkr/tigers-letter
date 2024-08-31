package chat.tigersnews.repository

import chat.tigersnews.domain.UserInfo
import org.springframework.data.jpa.repository.JpaRepository
import java.util.*

interface UserRepository : JpaRepository<UserInfo, Long> {

    fun findByEmail(email: String): Optional<UserInfo>
}