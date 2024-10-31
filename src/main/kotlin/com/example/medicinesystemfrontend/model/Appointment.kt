package com.example.medicinesystemfrontend.model

data class Appointment(
    val id: Int? = null,
    val idRecipe: Recipe? = null,
    val idMedicine: Medicine? = null,
    val reason: String = "",
    val diagnosis: String = "",
    val usageMethod: String = "",
    val recommendations: String = ""
)