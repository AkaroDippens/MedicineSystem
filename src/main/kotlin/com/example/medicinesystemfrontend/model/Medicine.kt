package com.example.medicinesystemfrontend.model

data class Medicine(
    val id: Int? = null,
    val medicineName: String = "",
    val manufacturer: String = "",
    val idBuilding: Building? = null,
    val idDoctor: Doctor? = null
)