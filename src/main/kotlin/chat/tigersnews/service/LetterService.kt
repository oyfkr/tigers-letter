package chat.tigersnews.service

import chat.tigersnews.controller.letter.request.LetterCreateRequest
import chat.tigersnews.domain.Letter
import chat.tigersnews.repository.LetterRepository
import org.springframework.stereotype.Service
import java.time.ZonedDateTime

@Service
class LetterService(
    private val letterRepository: LetterRepository
) {

    fun createLetter(letterCreateRequest: LetterCreateRequest) {

        val letter = Letter(null, letterCreateRequest.sendDate, letterCreateRequest.content, ZonedDateTime.now())

        letterRepository.save(letter)
    }
}