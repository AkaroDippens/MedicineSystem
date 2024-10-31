package com.example.medicinesystemfrontend.service

import com.example.medicinesystemfrontend.model.Recipe
import org.springframework.stereotype.Service
import org.springframework.web.client.RestTemplate

@Service
class RecipeService(private val restTemplate: RestTemplate) {

    private val baseUrl = "http://localhost:8080/api/recipes"

    fun getAllRecipes(): List<Recipe> {
        val response = restTemplate.getForEntity(baseUrl, Array<Recipe>::class.java)
        return response.body?.toList() ?: emptyList()
    }

    fun getRecipeById(id: Long): Recipe? {
        val url = "$baseUrl/$id"
        val response = restTemplate.getForEntity(url, Recipe::class.java)
        return response.body
    }

    fun addRecipe(recipe: Recipe): Recipe? {
        val response = restTemplate.postForEntity(baseUrl, recipe, Recipe::class.java)
        return response.body
    }

    fun updateRecipe(id: Long, recipe: Recipe): Recipe? {
        val url = "$baseUrl/$id"
        restTemplate.put(url, recipe)
        return getRecipeById(id)
    }

    fun deleteRecipe(id: Long) {
        val url = "$baseUrl/$id"
        restTemplate.delete(url)
    }
}