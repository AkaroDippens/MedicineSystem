package com.example.medicinesystemfrontend.controller

import com.example.medicinesystemfrontend.model.Doctor
import com.example.medicinesystemfrontend.service.BuildingService
import com.example.medicinesystemfrontend.service.DoctorService
import com.example.medicinesystemfrontend.service.SpecializationService
import org.springframework.stereotype.Controller
import org.springframework.ui.Model
import org.springframework.web.bind.annotation.*

@Controller
class DoctorController(private val doctorService: DoctorService,
    private val specializationService: SpecializationService,
    private val buildingService: BuildingService
) {

    @GetMapping("/doctors")
    fun listDoctors(model: Model): String {
        val doctors = doctorService.getAllDoctors()
        val specializations = specializationService.getAllSpecializations()
        val buildings = buildingService.getAllBuildings()
        model.addAttribute("specializations", specializations)
        model.addAttribute("buildings", buildings)
        model.addAttribute("doctors", doctors)
        model.addAttribute("newDoctor", Doctor())
        model.addAttribute("editDoctor", Doctor())
        return "doctors"
    }

    @GetMapping("/doctors/{id}")
    fun doctorDetails(@PathVariable id: Long, model: Model): String {
        val doctor = doctorService.getDoctorById(id)
        model.addAttribute("doctor", doctor)
        return "doctor-details"
    }

    @PostMapping("/doctors/add")
    fun addDoctor(@ModelAttribute doctor: Doctor): String {
        val specialization = specializationService.getSpecializationById(doctor.idSpecialization?.id!!.toLong())
        val building = buildingService.getBuildingById(doctor.idBuilding?.id!!.toLong())

        doctor.idSpecialization = specialization
        doctor.idBuilding = building

        doctorService.addDoctor(doctor)
        return "redirect:/doctors"
    }

    @PostMapping("/doctors/update")
    fun updateDoctor(@ModelAttribute doctor: Doctor): String {
        doctorService.updateDoctor(doctor.id!!.toLong(), doctor)
        return "redirect:/doctors"
    }

    @PostMapping("/doctors/{id}")
    fun deleteDoctor(@PathVariable id: Long): String {
        doctorService.deleteDoctor(id)
        return "redirect:/doctors"
    }
}