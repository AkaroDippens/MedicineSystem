package com.example.medicinesystemfrontend.service

import com.example.medicinesystemfrontend.model.Appointment
import org.springframework.stereotype.Service
import org.springframework.web.client.RestTemplate

@Service
class AppointmentService(private val restTemplate: RestTemplate) {

    private val baseUrl = "http://localhost:8080/api/appointments"

    fun getAllAppointments(): List<Appointment> {
        val response = restTemplate.getForEntity(baseUrl, Array<Appointment>::class.java)
        return response.body?.toList() ?: emptyList()
    }

    fun getAppointmentById(id: Long): Appointment? {
        val url = "$baseUrl/$id"
        val response = restTemplate.getForEntity(url, Appointment::class.java)
        return response.body
    }

    fun addAppointment(appointment: Appointment): Appointment? {
        val response = restTemplate.postForEntity(baseUrl, appointment, Appointment::class.java)
        return response.body
    }

    fun updateAppointment(id: Long, appointment: Appointment): Appointment? {
        val url = "$baseUrl/$id"
        restTemplate.put(url, appointment)
        return getAppointmentById(id)
    }

    fun deleteAppointment(id: Long) {
        val url = "$baseUrl/$id"
        restTemplate.delete(url)
    }
}