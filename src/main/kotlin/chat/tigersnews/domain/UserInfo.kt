package chat.tigersnews.domain

import jakarta.persistence.Column
import jakarta.persistence.Entity
import jakarta.persistence.GeneratedValue
import jakarta.persistence.GenerationType
import jakarta.persistence.Id
import java.time.ZonedDateTime

@Entity
class UserInfo(

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    var id : Long?,

    @Column(name = "email", nullable = false)
    var email: String,

    @Column(name = "enabled", nullable = false)
    var enabled: Boolean,

    @Column(name = "disable_date", nullable = true)
    var disableDate: ZonedDateTime?,

    @Column(name = "created_at", nullable = false)
    var createdAt: ZonedDateTime
)