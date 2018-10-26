package com.example.backend

import javax.persistence.Entity
import javax.persistence.GeneratedValue
import javax.persistence.Id

@Entity
data class Employee (
        val firstName: String = "",
        val lastName: String = "",
        val description: String = ""
){
    @Id @GeneratedValue var id:Long = 0
}

