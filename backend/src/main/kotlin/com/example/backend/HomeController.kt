package com.example.backend

import org.springframework.stereotype.Controller
import org.springframework.web.bind.annotation.RequestMapping

@Controller
class HomeController {
    @RequestMapping(value = ["/"])
    fun index() = "index"
}

