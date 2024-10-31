package com.example.medicinesystemfrontend.model

import java.time.LocalDate

data class User(
    val id: Int? = null,
    val fullName: String = "",
    val contactNumber: String? = null,
    val mhiPolicy: String = "",
    val birthDate: LocalDate? = null,
    var password: String = "",
    val idRole: Role? = Role()
)
