package com.example.medicinesystemfrontend.controller

import com.example.medicinesystemfrontend.model.Role
import com.example.medicinesystemfrontend.service.RoleService
import org.springframework.stereotype.Controller
import org.springframework.ui.Model
import org.springframework.web.bind.annotation.*

@Controller
class RoleController(private val roleService: RoleService) {

    @GetMapping("/roles")
    fun listRoles(model: Model): String {
        val roles = roleService.getAllRoles()
        model.addAttribute("roles", roles)
        model.addAttribute("editRole", Role())
        model.addAttribute("newRole", Role())
        return "roles"
    }

    @GetMapping("/roles/{id}")
    fun roleDetails(@PathVariable id: Long, model: Model): String {
        val role = roleService.getRoleById(id)
        model.addAttribute("role", role)
        return "role-details"
    }

    @PostMapping("/roles/add")
    fun addRole(@ModelAttribute role: Role): String {
        roleService.addRole(role)
        return "redirect:/roles"
    }

    @PostMapping("/roles/edit/{id}")
    fun updateRole(@PathVariable id: Long, @ModelAttribute role: Role): String {
        roleService.updateRole(id, role)
        return "redirect:/roles"
    }

    @GetMapping("/roles/delete/{id}")
    fun deleteRole(@PathVariable id: Long): String {
        roleService.deleteRole(id)
        return "redirect:/roles"
    }
}