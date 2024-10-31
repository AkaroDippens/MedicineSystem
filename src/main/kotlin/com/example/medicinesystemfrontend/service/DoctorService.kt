package com.example.medicinesystemfrontend.service

import com.example.medicinesystemfrontend.model.Doctor
import org.springframework.stereotype.Service
import org.springframework.web.client.RestTemplate
import org.springframework.web.util.UriComponentsBuilder

@Service
class DoctorService(private val restTemplate: RestTemplate) {

    private val baseUrl = "http://localhost:8080/api/doctors"

    fun getAllDoctors(): List<Doctor> {
        val response = restTemplate.getForEntity(baseUrl, Array<Doctor>::class.java)
        return response.body?.toList() ?: emptyList()
    }

    fun getDoctorById(id: Long): Doctor? {
        val url = "$baseUrl/$id"
        val response = restTemplate.getForEntity(url, Doctor::class.java)
        return response.body
    }

    fun getDoctorByMhlPolicy(mhlPolicy: String?): Doctor? {
        val url = UriComponentsBuilder.fromHttpUrl("$baseUrl/mhlpolicy/$mhlPolicy").toUriString()
        val response = restTemplate.getForEntity(url, Doctor::class.java)
        return response.body
    }

    fun addDoctor(doctor: Doctor): Doctor? {
        val response = restTemplate.postForEntity(baseUrl, doctor, Doctor::class.java)
        return response.body
    }

    fun updateDoctor(id: Long, doctor: Doctor): Doctor? {
        val url = "$baseUrl/$id"
        restTemplate.put(url, doctor)
        return getDoctorById(id)
    }

    fun deleteDoctor(id: Long) {
        val url = "$baseUrl/$id"
        restTemplate.delete(url)
    }
}