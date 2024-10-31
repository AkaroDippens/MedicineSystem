package com.example.medicinesystemfrontend.controller

import com.example.medicinesystemfrontend.model.User
import com.example.medicinesystemfrontend.service.RoleService
import com.example.medicinesystemfrontend.service.UserService
import org.springframework.security.crypto.password.PasswordEncoder
import org.springframework.stereotype.Controller
import org.springframework.ui.Model
import org.springframework.web.bind.annotation.*

@Controller
class UserController(
    private val userService: UserService,
    private val roleService: RoleService,
    private val passwordEncoder: PasswordEncoder
) {

    @GetMapping("/users")
    fun listUsers(model: Model): String {
        val users = userService.getAllUsers()
        val roles = roleService.getAllRoles()
        model.addAttribute("users", users)
        model.addAttribute("roles", roles)
        model.addAttribute("newUser", User())
        model.addAttribute("editUser", User())
        return "users"
    }

    @GetMapping("/users/{id}")
    fun userDetails(@PathVariable id: Long, model: Model): String {
        val user = userService.getUserById(id)
        model.addAttribute("user", user)
        return "user-details"
    }

    @PostMapping("/users/add")
    fun addUser(@ModelAttribute user: User, model: Model): String {
        user.password = passwordEncoder.encode(user.password)
        userService.addUser(user)
        return "redirect:/users"
    }

    @PostMapping("/users/update")
    fun updateUser(@ModelAttribute user: User, model: Model): String {
        user.password = passwordEncoder.encode(user.password)
        user.idRole?.roleName = roleService.getRoleById(user.idRole?.id!!.toLong())!!.roleName
        userService.updateUser(user.id!!.toLong(), user)
        return "redirect:/users"
    }

    @PostMapping("/users/{id}")
    fun deleteUser(@PathVariable id: Long): String {
        userService.deleteUser(id)
        return "redirect:/users"
    }

    @GetMapping("/login")
    fun showLoginPage(): String {
        return "login"
    }

    @GetMapping("/register")
    fun showRegisterPage(model: Model): String {
        model.addAttribute("user", User())
        return "register"
    }

    @PostMapping("/register")
    fun registerUser(@ModelAttribute user: User): String {
        user.password = passwordEncoder.encode(user.password)
        userService.addUser(user)
        return "redirect:/login"
    }
}
