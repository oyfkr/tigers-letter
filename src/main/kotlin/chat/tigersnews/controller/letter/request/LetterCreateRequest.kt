package chat.tigersnews.controller.letter.request

import java.time.ZonedDateTime

class LetterCreateRequest(
    val sendDate: ZonedDateTime,
    val content: String
) {
}