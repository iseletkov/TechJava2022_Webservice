package ru.psu.techjava.studentmarks.techjava2022_lab2.controllers

import org.springframework.beans.factory.annotation.Autowired
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*
import org.springframework.web.server.ResponseStatusException
import ru.psu.techjava.studentmarks.techjava2022_lab2.model.CStudent
import ru.psu.techjava.studentmarks.techjava2022_lab2.repositories.IRepositoryStudents
import ru.psu.techjava.studentmarks.techjava2022_lab2.services.IServiceStudents
import java.util.*


@RestController
@RequestMapping("/students")
class CControllerStudents
{
    @Autowired
    lateinit var serviceStudents: IServiceStudents

    @GetMapping
    fun getAll() : Iterable<CStudent>
    {
        return serviceStudents.getAll()
    }
    @GetMapping(params = ["id"])
    fun getById(@RequestParam id: UUID) : ResponseEntity<CStudent>
    {
        return serviceStudents.getById(id)
    }
    @PostMapping
    fun save(@RequestBody student: CStudent)
    {
        serviceStudents.save(student)
    }
    @DeleteMapping
    fun delete(@RequestBody student: CStudent)
    {
        serviceStudents.delete(student)
    }
    @DeleteMapping(params = ["id"])
    fun deleteById(@RequestParam id: UUID)
    {
        serviceStudents.deleteById(id)
    }

}