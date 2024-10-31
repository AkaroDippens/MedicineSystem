package com.example.medicinesystemfrontend.service

import com.example.medicinesystemfrontend.model.Record
import org.springframework.stereotype.Service
import org.springframework.web.client.RestTemplate

@Service
class RecordService(private val restTemplate: RestTemplate) {

    private val baseUrl = "http://localhost:8080/api/records"

    fun getAllRecords(): List<Record> {
        val response = restTemplate.getForEntity(baseUrl, Array<Record>::class.java)
        return response.body?.toList() ?: emptyList()
    }

    fun getRecordById(id: Long): Record? {
        val url = "$baseUrl/$id"
        val response = restTemplate.getForEntity(url, Record::class.java)
        return response.body
    }

    fun addRecord(record: Record): Record? {
        val response = restTemplate.postForEntity(baseUrl, record, Record::class.java)
        return response.body
    }

    fun updateRecord(id: Long, record: Record): Record? {
        val url = "$baseUrl/$id"
        restTemplate.put(url, record)
        return getRecordById(id)
    }

    fun deleteRecord(id: Long) {
        val url = "$baseUrl/$id"
        restTemplate.delete(url)
    }
}