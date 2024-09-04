package chat.tigersnews.controller.basic

import chat.tigersnews.controller.user.request.UserCreateRequest
import chat.tigersnews.controller.user.request.UserModifyRequest
import org.springframework.stereotype.Controller
import org.springframework.ui.Model
import org.springframework.web.bind.annotation.GetMapping

@Controller
class BasicController {

    @GetMapping("/register")
    fun home(model: Model): String {

        model.addAttribute("apiUrl", "http://localhost:8080/rest/user")
        model.addAttribute("userCreateRequest", UserCreateRequest(null))
        return "registerEmail"
    }

    @GetMapping("/disable")
    fun disable(model: Model): String {

        model.addAttribute("apiUrl", "http://localhost:8080/rest/user")
        model.addAttribute("userModifyRequest", UserModifyRequest(null))

        return "disableEmail"
    }
}