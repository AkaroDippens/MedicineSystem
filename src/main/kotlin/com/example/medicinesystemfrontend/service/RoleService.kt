package com.example.medicinesystemfrontend.service

import com.example.medicinesystemfrontend.model.Role
import org.springframework.stereotype.Service
import org.springframework.web.client.RestTemplate

@Service
class RoleService(private val restTemplate: RestTemplate) {

    private val baseUrl = "http://localhost:8080/api/roles"

    fun getAllRoles(): List<Role> {
        val response = restTemplate.getForEntity(baseUrl, Array<Role>::class.java)
        return response.body?.toList() ?: emptyList()
    }

    fun getRoleById(id: Long): Role? {
        val url = "$baseUrl/$id"
        val response = restTemplate.getForEntity(url, Role::class.java)
        return response.body
    }

    fun addRole(role: Role): Role? {
        val response = restTemplate.postForEntity(baseUrl, role, Role::class.java)
        return response.body
    }

    fun updateRole(id: Long, role: Role): Role? {
        val url = "$baseUrl/$id"
        restTemplate.put(url, role)
        return getRoleById(id)
    }

    fun deleteRole(id: Long) {
        val url = "$baseUrl/$id"
        restTemplate.delete(url)
    }
}