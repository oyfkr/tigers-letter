package chat.tigersnews.domain

import jakarta.persistence.Column
import jakarta.persistence.Entity
import jakarta.persistence.GeneratedValue
import jakarta.persistence.GenerationType
import jakarta.persistence.Id
import java.time.ZonedDateTime

@Entity
class Letter(

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    var id: Long?,

    @Column(name = "send_date", nullable = false)
    var sendDate: ZonedDateTime,

    @Column(name = "content", nullable = false)
    var content: String,

    @Column(name = "created_at", nullable = false)
    var createdAt: ZonedDateTime,
) {
}