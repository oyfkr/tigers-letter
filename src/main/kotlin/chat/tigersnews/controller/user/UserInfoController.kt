package chat.tigersnews.controller.user

import chat.tigersnews.controller.user.request.UserCreateRequest
import chat.tigersnews.controller.user.request.UserModifyRequest
import chat.tigersnews.service.MailService
import chat.tigersnews.service.UserService
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping("/rest")
class UserInfoController(
    private val userService: UserService,
    private val mailService: MailService
)  {

    @PostMapping("/user")
    fun createUser(@RequestBody userCreateRequest: UserCreateRequest) : ResponseEntity<Any> {

        userService.createUser(userCreateRequest)

        return ResponseEntity.ok("success")
    }

    @PatchMapping("/user")
    fun disableUser(@RequestBody userModifyRequest: UserModifyRequest): ResponseEntity<Any> {

        userService.disableUser(userModifyRequest)

        return ResponseEntity.ok("success")
    }

    @GetMapping("/mail-test")
    fun sendMail() : ResponseEntity<Any> {

        mailService.sendMail()

        return ResponseEntity.ok("success")
    }
}