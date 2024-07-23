package com.example.VisitorManagementSystem.controller;

import com.example.VisitorManagementSystem.dto.UserDto;
import com.example.VisitorManagementSystem.entity.User;
import com.example.VisitorManagementSystem.service.AdminService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/admin")
public class AdminController
{
    @Autowired
    private AdminService adminService;

    @PostMapping("/createUser")
    ResponseEntity<Long> createUser(@RequestBody @Valid UserDto userDto){
        Long id = adminService.createUser(userDto);
        return ResponseEntity.ok(id);
    }
}
