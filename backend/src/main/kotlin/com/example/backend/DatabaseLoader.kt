package com.example.backend

import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.CommandLineRunner
import org.springframework.stereotype.Component

@Component
class DatabaseLoader(@Autowired val repo:EmployeeRepository)
    : CommandLineRunner {
    override fun run(vararg args: String?) {
        this.repo.save(Employee("Frodo", "Baggins", "ring bearer"))
    }
}