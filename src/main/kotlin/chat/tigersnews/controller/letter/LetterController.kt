package chat.tigersnews.controller.letter

import chat.tigersnews.controller.letter.request.LetterCreateRequest
import chat.tigersnews.service.LetterService
import chat.tigersnews.service.MailService
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RequestMapping("/rest")
@RestController()
class LetterController(
    private val mailService: MailService,
    private val letterService: LetterService
) {

    @GetMapping("/letter/send")
    fun sendLetter() : ResponseEntity<String> {

        mailService.sendMail()
        return ResponseEntity.ok("success")
    }

    @PostMapping("/letter")
    fun createLetter(@RequestBody letterCreateRequest: LetterCreateRequest): ResponseEntity<String> {
        letterService.createLetter(letterCreateRequest)
        return ResponseEntity.ok("success")
    }
}