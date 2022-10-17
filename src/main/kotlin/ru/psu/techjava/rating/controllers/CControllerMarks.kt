package ru.psu.techjava.rating.controllers

import org.springframework.beans.factory.annotation.Autowired
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController
import ru.psu.techjava.rating.model.CMark
import ru.psu.techjava.rating.services.IServiceMarks

@RestController
@RequestMapping("/marks")
class CControllerMarks {
    @Autowired
    lateinit var serviceMarks : IServiceMarks

    @PostMapping
    fun save(@RequestBody mark: CMark)
    {
        serviceMarks.save(mark)
    }
}