package com.example.medicinesystemfrontend.service

import com.example.medicinesystemfrontend.model.MedicalRecord
import org.springframework.stereotype.Service
import org.springframework.web.client.RestTemplate

@Service
class MedicalRecordService(private val restTemplate: RestTemplate) {

    private val baseUrl = "http://localhost:8080/api/medicalrecords"

    fun getAllMedicalRecords(): List<MedicalRecord> {
        val response = restTemplate.getForEntity(baseUrl, Array<MedicalRecord>::class.java)
        return response.body?.toList() ?: emptyList()
    }

    fun getMedicalRecordById(id: Long): MedicalRecord? {
        val url = "$baseUrl/$id"
        val response = restTemplate.getForEntity(url, MedicalRecord::class.java)
        return response.body
    }

    fun addMedicalRecord(medicalRecord: MedicalRecord): MedicalRecord? {
        val response = restTemplate.postForEntity(baseUrl, medicalRecord, MedicalRecord::class.java)
        return response.body
    }

    fun updateMedicalRecord(id: Long, medicalRecord: MedicalRecord): MedicalRecord? {
        val url = "$baseUrl/$id"
        restTemplate.put(url, medicalRecord)
        return getMedicalRecordById(id)
    }

    fun deleteMedicalRecord(id: Long) {
        val url = "$baseUrl/$id"
        restTemplate.delete(url)
    }
}