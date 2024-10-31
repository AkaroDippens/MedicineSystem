package com.example.medicinesystemfrontend.service

import com.example.medicinesystemfrontend.model.Building
import org.springframework.stereotype.Service
import org.springframework.web.client.RestTemplate
import org.springframework.web.util.UriComponentsBuilder

@Service
class BuildingService(private val restTemplate: RestTemplate) {

    private val baseUrl = "http://localhost:8080/api/buildings"

    fun getAllBuildings(): List<Building> {
        val response = restTemplate.getForEntity(baseUrl, Array<Building>::class.java)
        return response.body?.toList() ?: emptyList()
    }

    fun getBuildingById(id: Long): Building? {
        val url = "$baseUrl/$id"
        val response = restTemplate.getForEntity(url, Building::class.java)
        return response.body
    }

    fun getBuildingByAddress(address: String?): List<Building> {
        val url = UriComponentsBuilder.fromHttpUrl("$baseUrl/byaddress/$address").toUriString()
        val response = restTemplate.getForEntity(url, Array<Building>::class.java)
        return response.body?.toList() ?: emptyList()
    }

    fun addBuilding(building: Building): Building? {
        val response = restTemplate.postForEntity(baseUrl, building, Building::class.java)
        return response.body
    }

    fun updateBuilding(id: Long, building: Building): Building? {
        val url = "$baseUrl/$id"
        restTemplate.put(url, building)
        return getBuildingById(id)
    }

    fun deleteBuilding(id: Long) {
        val url = "$baseUrl/$id"
        restTemplate.delete(url)
    }
}