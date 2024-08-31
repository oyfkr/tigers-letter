package chat.tigersnews.controller

import chat.tigersnews.controller.request.UserCreateRequest
import chat.tigersnews.controller.request.UserModifyRequest
import chat.tigersnews.service.UserService
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*

@RestController("/rest")
class UserInfoController(
    private val userService: UserService
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
}