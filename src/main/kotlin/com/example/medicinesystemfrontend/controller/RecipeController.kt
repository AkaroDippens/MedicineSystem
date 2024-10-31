package com.example.medicinesystemfrontend.controller

import com.example.medicinesystemfrontend.model.Recipe
import com.example.medicinesystemfrontend.service.DoctorService
import com.example.medicinesystemfrontend.service.RecipeService
import com.example.medicinesystemfrontend.service.UserService
import org.springframework.stereotype.Controller
import org.springframework.ui.Model
import org.springframework.web.bind.annotation.*

@Controller
class RecipeController(
    private val recipeService: RecipeService,
    private val doctorService: DoctorService
) {

    @GetMapping("/recipes")
    fun listRecipes(model: Model): String {
        val recipes = recipeService.getAllRecipes()
        val doctors = doctorService.getAllDoctors()
        model.addAttribute("recipes", recipes)
        model.addAttribute("editRecipe", Recipe())
        model.addAttribute("newRecipe", Recipe())
        model.addAttribute("doctors", doctors)
        return "recipes"
    }

    @GetMapping("/recipes/{id}")
    fun recipeDetails(@PathVariable id: Long, model: Model): String {
        val recipe = recipeService.getRecipeById(id)
        model.addAttribute("recipe", recipe)
        return "recipe-details"
    }

    @PostMapping("/recipes/add")
    fun addRecipe(@ModelAttribute recipe: Recipe): String {
        val doctor = doctorService.getDoctorById(recipe.idDoctor?.id!!.toLong())
        recipe.idDoctor = doctor
        recipeService.addRecipe(recipe)
        return "redirect:/recipes"
    }

    @PostMapping("/recipes/update")
    fun updateRecipe(@PathVariable id: Long, @ModelAttribute recipe: Recipe): String {
        recipeService.updateRecipe(id, recipe)
        return "redirect:/recipes"
    }

    @GetMapping("/recipes/delete/{id}")
    fun deleteRecipe(@PathVariable id: Long): String {
        recipeService.deleteRecipe(id)
        return "redirect:/recipes"
    }
}