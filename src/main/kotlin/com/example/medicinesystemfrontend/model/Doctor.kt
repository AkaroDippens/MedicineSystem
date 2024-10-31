package com.example.medicinesystemfrontend.model

import java.time.LocalDate

data class Doctor(
    val id: Int? = null,
    val fullName: String = "",
    val experience: LocalDate? = null,
    var idSpecialization: Specialization? = Specialization(),
    var idBuilding: Building? = Building(),
)