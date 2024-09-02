package chat.tigersnews.service

import chat.tigersnews.controller.user.request.UserCreateRequest
import chat.tigersnews.controller.user.request.UserModifyRequest
import chat.tigersnews.domain.UserInfo
import chat.tigersnews.repository.UserRepository
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional
import java.time.ZonedDateTime

@Service
class UserService(
    private val userRepository: UserRepository
) {

    fun createUser(userCreateRequest: UserCreateRequest) {

        userCreateRequest.email ?: throw IllegalArgumentException("이메일이 없는 요청입니다.")

        val user = UserInfo(null, userCreateRequest.email, true, null, ZonedDateTime.now())

        userRepository.save(user)
    }

    @Transactional
    fun disableUser(userModifyRequest: UserModifyRequest) {

        val user = userRepository.findByEmail(userModifyRequest.email).orElseThrow { IllegalStateException("그런 이메일은 없어") }

        user.enabled = false
        user.disableDate = ZonedDateTime.now()
    }
}