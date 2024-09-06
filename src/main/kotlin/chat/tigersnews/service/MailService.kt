package chat.tigersnews.service

import chat.tigersnews.repository.LetterRepository
import chat.tigersnews.repository.UserRepository
import org.springframework.mail.SimpleMailMessage
import org.springframework.mail.javamail.JavaMailSender
import org.springframework.mail.javamail.MimeMessageHelper
import org.springframework.stereotype.Component
import java.time.ZonedDateTime

@Component
class MailService(
    private val mailSender: JavaMailSender,
    private val userRepository: UserRepository,
    private val letterRepository: LetterRepository
) {

    fun sendMail() {

        val now = ZonedDateTime.now()
        val start = now.withHour(0).withMinute(0).withSecond(0).withNano(0)
        val end = start.plusDays(1)
        val letter = letterRepository.findBySendDateBetween(start, end) ?: throw IllegalArgumentException("letter가 존재하지 않습니다.")

        userRepository.findAllByEnabled(true).map {
            sendMailForeach(it.email, letter.content)
        }
    }

    private fun sendMailForeach(email : String, content : String) {
        val message = SimpleMailMessage().apply {
            from = "noreply@gmail.com"
            setTo(email)
            setSubject(content)
            text = "테스트입니다."
        }
        mailSender.send(message)
    }
}