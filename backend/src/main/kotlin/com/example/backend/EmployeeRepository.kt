package com.example.backend

import org.springframework.data.repository.PagingAndSortingRepository

interface EmployeeRepository: PagingAndSortingRepository<Employee, Long>


