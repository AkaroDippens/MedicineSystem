package com.example.medicinesystemfrontend.model

import java.time.LocalDateTime

data class Analyze(
    val id: Int? = null,
    val analyzeResult: String = "",
    val userInformation: String = "",
    val receiveTime: LocalDateTime? = null,
    val idUser: User? = null,
    val idDoctor: Doctor? = null,
    val idAppointment: Appointment? = null
)