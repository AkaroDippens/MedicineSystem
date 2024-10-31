package com.example.medicinesystemfrontend.service

import com.example.medicinesystemfrontend.model.Medicine
import org.springframework.stereotype.Service
import org.springframework.web.client.RestTemplate

@Service
class MedicineService(private val restTemplate: RestTemplate) {

    private val baseUrl = "http://localhost:8080/api/medicines"

    fun getAllMedicines(): List<Medicine> {
        val response = restTemplate.getForEntity(baseUrl, Array<Medicine>::class.java)
        return response.body?.toList() ?: emptyList()
    }

    fun getMedicineById(id: Long): Medicine? {
        val url = "$baseUrl/$id"
        val response = restTemplate.getForEntity(url, Medicine::class.java)
        return response.body
    }

    fun addMedicine(medicine: Medicine): Medicine? {
        val response = restTemplate.postForEntity(baseUrl, medicine, Medicine::class.java)
        return response.body
    }

    fun updateMedicine(id: Long, medicine: Medicine): Medicine? {
        val url = "$baseUrl/$id"
        restTemplate.put(url, medicine)
        return getMedicineById(id)
    }

    fun deleteMedicine(id: Long) {
        val url = "$baseUrl/$id"
        restTemplate.delete(url)
    }
}