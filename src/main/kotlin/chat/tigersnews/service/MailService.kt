package chat.tigersnews.service

import org.springframework.mail.SimpleMailMessage
import org.springframework.mail.javamail.JavaMailSender
import org.springframework.mail.javamail.MimeMessageHelper
import org.springframework.stereotype.Component

@Component
class MailService(
    private val mailSender: JavaMailSender
) {

    fun sendMail() {
        val message = SimpleMailMessage().apply {
            from = "oyfkr2013@gmail.com"
            setTo("oyfkr@naver.com")
            setSubject("테스트")
            text = "테스트입니다."
        }
        mailSender.send(message)
    }
}