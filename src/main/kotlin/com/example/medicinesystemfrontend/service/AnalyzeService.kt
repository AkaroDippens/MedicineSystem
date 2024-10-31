package com.example.medicinesystemfrontend.service

import com.example.medicinesystemfrontend.model.Analyze
import org.springframework.stereotype.Service
import org.springframework.web.client.RestTemplate

@Service
class AnalyzeService(private val restTemplate: RestTemplate) {

    private val baseUrl = "http://localhost:8080/api/analyzes"

    fun getAllAnalyzes(): List<Analyze> {
        val response = restTemplate.getForEntity(baseUrl, Array<Analyze>::class.java)
        return response.body?.toList() ?: emptyList()
    }

    fun getAnalyzeById(id: Long): Analyze? {
        val url = "$baseUrl/$id"
        val response = restTemplate.getForEntity(url, Analyze::class.java)
        return response.body
    }

    fun addAnalyze(analyze: Analyze): Analyze? {
        val response = restTemplate.postForEntity(baseUrl, analyze, Analyze::class.java)
        return response.body
    }

    fun updateAnalyze(id: Long, analyze: Analyze): Analyze? {
        val url = "$baseUrl/$id"
        restTemplate.put(url, analyze)
        return getAnalyzeById(id)
    }

    fun deleteAnalyze(id: Long) {
        val url = "$baseUrl/$id"
        restTemplate.delete(url)
    }
}