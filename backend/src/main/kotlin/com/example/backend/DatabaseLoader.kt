package com.example.backend

import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.CommandLineRunner
import org.springframework.stereotype.Component

@Component
class DatabaseLoader(@Autowired val repo:EmployeeRepository)
    : CommandLineRunner {
    override fun run(vararg args: String?) {
        this.repo.save(Employee("Frodo", "Baggins", "ring bearer"))
        this.repo.save(Employee("Bilbo", "Baggins", "burglar"))
        this.repo.save(Employee("Gandalf", "the Grey", "wizard"))
        this.repo.save(Employee("Samwise", "Gamgee", "gardener"))
        this.repo.save(Employee("Meriadoc", "Brandybuck", "pony rider"))
        this.repo.save(Employee("Peregrin", "Took", "pipe smoker"))
    }
}

