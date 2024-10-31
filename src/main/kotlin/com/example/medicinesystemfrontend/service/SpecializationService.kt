package com.example.medicinesystemfrontend.service

import com.example.medicinesystemfrontend.model.Specialization
import org.springframework.stereotype.Service
import org.springframework.web.client.RestTemplate

@Service
class SpecializationService(private val restTemplate: RestTemplate) {

    private val baseUrl = "http://localhost:8080/api/specializations"

    fun getAllSpecializations(): List<Specialization> {
        val response = restTemplate.getForEntity(baseUrl, Array<Specialization>::class.java)
        return response.body?.toList() ?: emptyList()
    }

    fun getSpecializationById(id: Long): Specialization? {
        val url = "$baseUrl/$id"
        val response = restTemplate.getForEntity(url, Specialization::class.java)
        return response.body
    }

    fun addSpecialization(specialization: Specialization): Specialization? {
        val response = restTemplate.postForEntity(baseUrl, specialization, Specialization::class.java)
        return response.body
    }

    fun updateSpecialization(id: Long, specialization: Specialization): Specialization? {
        val url = "$baseUrl/$id"
        restTemplate.put(url, specialization)
        return getSpecializationById(id)
    }

    fun deleteSpecialization(id: Long) {
        val url = "$baseUrl/$id"
        restTemplate.delete(url)
    }
}