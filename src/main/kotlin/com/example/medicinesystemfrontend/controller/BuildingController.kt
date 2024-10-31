package com.example.medicinesystemfrontend.controller

import com.example.medicinesystemfrontend.model.Building
import com.example.medicinesystemfrontend.service.BuildingService
import org.springframework.stereotype.Controller
import org.springframework.ui.Model
import org.springframework.web.bind.annotation.*

@Controller
class BuildingController(private val buildingService: BuildingService) {

    @GetMapping("/buildings")
    fun listBuildings(model: Model): String {
        val buildings = buildingService.getAllBuildings()
        model.addAttribute("buildings", buildings)
        model.addAttribute("newBuilding", Building())
        model.addAttribute("editBuilding", Building())
        return "buildings"
    }

    @GetMapping("/buildings/{id}")
    fun buildingDetails(@PathVariable id: Long, model: Model): String {
        val building = buildingService.getBuildingById(id)
        model.addAttribute("building", building)
        return "building-details"
    }

    @PostMapping("/buildings/add")
    fun addBuilding(@ModelAttribute building: Building): String {
        buildingService.addBuilding(building)
        return "redirect:/buildings"
    }

    @PostMapping("/buildings/update")
    fun updateBuilding(@ModelAttribute building: Building): String {
        buildingService.updateBuilding(building.id!!.toLong(), building)
        return "redirect:/buildings"
    }

    @PostMapping("/buildings/{id}")
    fun deleteBuilding(@PathVariable id: Long): String {
        buildingService.deleteBuilding(id)
        return "redirect:/buildings"
    }
}