package chat.tigersnews.repository

import chat.tigersnews.domain.Letter
import org.springframework.data.jpa.repository.JpaRepository
import java.time.ZonedDateTime

interface LetterRepository : JpaRepository<Letter, Long> {

    fun findBySendDateBetween(startDate: ZonedDateTime, endDate: ZonedDateTime): Letter?
}