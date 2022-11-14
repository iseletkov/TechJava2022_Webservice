package ru.psu.techjava.rating.controllers

import org.springframework.beans.factory.annotation.Autowired
import org.springframework.web.bind.annotation.*
import ru.psu.techjava.rating.model.CMark
import ru.psu.techjava.rating.services.IServiceMarks
import java.util.*

@RestController
@RequestMapping("/marks")
class CControllerMarks (
    var serviceMarks                        : IServiceMarks
)
{

    @PostMapping
    fun save(@RequestBody mark: CMark)
    {
        serviceMarks.save(mark)
    }
    @GetMapping(params = ["id"])
    fun getAllByStudentId(
        @RequestParam id: UUID
    )                                       : List<CMark>
    {
        return serviceMarks.getAllByStudentId(id)
    }
}