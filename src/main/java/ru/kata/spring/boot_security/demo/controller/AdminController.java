package ru.kata.spring.boot_security.demo.controller;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import ru.kata.spring.boot_security.demo.model.Role;
import ru.kata.spring.boot_security.demo.model.User;
import ru.kata.spring.boot_security.demo.service.RoleService;
import ru.kata.spring.boot_security.demo.service.UserService;

import javax.validation.Valid;
import java.security.Principal;
import java.util.List;

@Controller
public class AdminController {

    private UserService userService;
    private RoleService roleService;


    @Autowired
    public AdminController(UserService userService, RoleService roleService) {
        this.userService = userService;
        this.roleService = roleService;
    }

    @GetMapping("/admin/allUsers")
    public String users(Model model) {
        model.addAttribute("users", userService.showUsers());
        return "/allUsers";
    }


    @GetMapping("/admin/{id}")
    public String showById(@PathVariable("id") long id, Model model) {
        model.addAttribute("user", userService.showById(id));
        return "/showById";
    }


    @GetMapping("/admin/{id}/editUser")
    public String edit(ModelMap model, @PathVariable("id") int id) {
        model.addAttribute("user", userService.showById(id));
        return "/editUser";
    }


    @GetMapping("/admin/newUser")
    public String newUser(@ModelAttribute("user") User user) {
        return "/newUser";
    }


    @PostMapping("/admin")
    public String create(@ModelAttribute("user") User user) {
        userService.saveUser(user);
        return "redirect:/admin/allUsers";
    }

    @PatchMapping("/admin/{id}")
    public String update(@ModelAttribute("user") User user, @PathVariable("id") long id) {
        userService.update(id, user);
        return "redirect:/admin/allUsers";
    }

    
    @DeleteMapping("admin/{id}")
    public String delete(@PathVariable("id") Long id) {
        userService.delete(id);
        return "redirect:/admin/allUsers";
    }



   /* @GetMapping("/admin")
    public String pageRedirect(Principal principal) {
        return "redirect:/admin";
    }

    */



}