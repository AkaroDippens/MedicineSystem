package com.example.medicinesystemfrontend.service

import com.example.medicinesystemfrontend.model.User
import org.springframework.stereotype.Service
import org.springframework.web.client.RestTemplate
import org.springframework.web.reactive.function.client.WebClient
import org.springframework.web.util.UriComponentsBuilder
import reactor.core.publisher.Flux
import reactor.core.publisher.Mono

@Service
class UserService(private val restTemplate: RestTemplate) {

    private val baseUrl = "http://localhost:8080/api/users"

    fun getAllUsers(): List<User> {
        val response = restTemplate.getForEntity(baseUrl, Array<User>::class.java)
        return response.body?.toList() ?: emptyList()
    }

    fun getUserById(id: Long): User? {
        val url = "$baseUrl/$id"
        val response = restTemplate.getForEntity(url, User::class.java)
        return response.body
    }

    fun getUserByMhiPolicy(mhiPolicy: String?): User? {
        val url = UriComponentsBuilder.fromHttpUrl("$baseUrl/mhipolicy/$mhiPolicy").toUriString()
        val response = restTemplate.getForEntity(url, User::class.java)
        return response.body
    }

    fun addUser(user: User): User? {
        val response = restTemplate.postForEntity(baseUrl, user, User::class.java)
        return response.body
    }

    fun updateUser(id: Long, user: User): User? {
        val url = "$baseUrl/$id"
        restTemplate.put(url, user)
        return getUserById(id)
    }

    fun deleteUser(id: Long) {
        val url = "$baseUrl/$id"
        restTemplate.delete(url)
    }
}