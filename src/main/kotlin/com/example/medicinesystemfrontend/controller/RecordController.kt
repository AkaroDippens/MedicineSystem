package com.example.medicinesystemfrontend.controller

import com.example.medicinesystemfrontend.model.Record
import com.example.medicinesystemfrontend.service.RecordService
import org.springframework.stereotype.Controller
import org.springframework.ui.Model
import org.springframework.web.bind.annotation.*

@Controller
class RecordController(private val recordService: RecordService) {

    @GetMapping("/records")
    fun listRecords(model: Model): String {
        val records = recordService.getAllRecords()
        model.addAttribute("records", records)
        return "records"
    }

    @GetMapping("/records/{id}")
    fun recordDetails(@PathVariable id: Long, model: Model): String {
        val record = recordService.getRecordById(id)
        model.addAttribute("record", record)
        return "record-details"
    }

    @GetMapping("/records/add")
    fun showAddRecordForm(model: Model): String {
        model.addAttribute("record", Record())
        return "add-record"
    }

    @PostMapping("/records/add")
    fun addRecord(@ModelAttribute record: Record): String {
        recordService.addRecord(record)
        return "redirect:/records"
    }

    @GetMapping("/records/edit/{id}")
    fun showEditRecordForm(@PathVariable id: Long, model: Model): String {
        val record = recordService.getRecordById(id)
        model.addAttribute("record", record)
        return "edit-record"
    }

    @PostMapping("/records/edit/{id}")
    fun updateRecord(@PathVariable id: Long, @ModelAttribute record: Record): String {
        recordService.updateRecord(id, record)
        return "redirect:/records"
    }

    @GetMapping("/records/delete/{id}")
    fun deleteRecord(@PathVariable id: Long): String {
        recordService.deleteRecord(id)
        return "redirect:/records"
    }
}