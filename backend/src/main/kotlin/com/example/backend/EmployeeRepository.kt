package com.example.backend

import org.springframework.data.repository.CrudRepository
import org.springframework.web.bind.annotation.CrossOrigin

@CrossOrigin
interface EmployeeRepository: CrudRepository<Employee, Long>