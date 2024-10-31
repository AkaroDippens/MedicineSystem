package com.example.medicinesystemfrontend.controller

import com.example.medicinesystemfrontend.model.Doctor
import com.example.medicinesystemfrontend.model.Specialization
import com.example.medicinesystemfrontend.service.SpecializationService
import org.springframework.stereotype.Controller
import org.springframework.ui.Model
import org.springframework.web.bind.annotation.*

@Controller
class SpecializationController(private val specializationService: SpecializationService) {

    @GetMapping("/specializations")
    fun listSpecializations(model: Model): String {
        val specializations = specializationService.getAllSpecializations()
        model.addAttribute("specializations", specializations)
        model.addAttribute("newSpecialization", Specialization())
        model.addAttribute("editSpecialization", Specialization())
        return "specializations"
    }

    @GetMapping("/specializations/{id}")
    fun specializationDetails(@PathVariable id: Long, model: Model): String {
        val specialization = specializationService.getSpecializationById(id)
        model.addAttribute("specialization", specialization)
        return "specialization-details"
    }

    @PostMapping("/specializations/add")
    fun addSpecialization(@ModelAttribute specialization: Specialization): String {
        specializationService.addSpecialization(specialization)
        return "redirect:/specializations"
    }

    @PostMapping("/specializations/edit/{id}")
    fun updateSpecialization(@PathVariable id: Long, @ModelAttribute specialization: Specialization): String {
        specializationService.updateSpecialization(id, specialization)
        return "redirect:/specializations"
    }

    @GetMapping("/specializations/delete/{id}")
    fun deleteSpecialization(@PathVariable id: Long): String {
        specializationService.deleteSpecialization(id)
        return "redirect:/specializations"
    }
}