package app.smartalerts.model

import java.time.LocalDateTime

data class Event(val dateTime: LocalDateTime, val name: String, val enabled: Boolean)
