package com.example.medicinesystemfrontend.controller

import com.example.medicinesystemfrontend.model.Medicine
import com.example.medicinesystemfrontend.service.MedicineService
import org.springframework.stereotype.Controller
import org.springframework.ui.Model
import org.springframework.web.bind.annotation.*

@Controller
class MedicineController(private val medicineService: MedicineService) {

    @GetMapping("/medicines")
    fun listMedicines(model: Model): String {
        val medicines = medicineService.getAllMedicines()
        model.addAttribute("medicines", medicines)
        model.addAttribute("newMedicine", Medicine())
        model.addAttribute("editMedicine", Medicine())
        return "medicines"
    }

    @GetMapping("/medicines/{id}")
    fun medicineDetails(@PathVariable id: Long, model: Model): String {
        val medicine = medicineService.getMedicineById(id)
        model.addAttribute("medicine", medicine)
        return "medicine-details"
    }

    @PostMapping("/medicines/add")
    fun addMedicine(@ModelAttribute medicine: Medicine): String {
        medicineService.addMedicine(medicine)
        return "redirect:/medicines"
    }

    @PostMapping("/medicines/update")
    fun updateMedicine(@ModelAttribute medicine: Medicine): String {
        medicineService.updateMedicine(medicine.id!!.toLong(), medicine)
        return "redirect:/medicines"
    }

    @PostMapping("/medicines/{id}")
    fun deleteMedicine(@PathVariable id: Long): String {
        medicineService.deleteMedicine(id)
        return "redirect:/medicines"
    }
}