package com.example.medicinesystemfrontend.model

import java.time.LocalDateTime

data class Record(
    val id: Int? = null,
    val appointmentDate: LocalDateTime? = null,
    val idUser: User? = null,
    val idDoctor: Doctor? = null
)