package ru.psu.techjava.rating.controllers

import org.springframework.beans.factory.annotation.Autowired
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*
import ru.psu.techjava.rating.model.CStudent
import ru.psu.techjava.rating.model.IStudentWithCounter
import ru.psu.techjava.rating.services.IServiceStudents
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
    @GetMapping(params = ["name"])
    fun getById(@RequestParam name: String) : Iterable<CStudent>
    {
        return serviceStudents.getByName(name)
    }
    @GetMapping(params = ["mark_value"])
    fun getByMarkValue(@RequestParam mark_value: Double) : Iterable<CStudent>
    {
        return serviceStudents.getByMarksValueLessEqual(mark_value)
    }
    @GetMapping(path = ["/problems"])
    fun getWithProblems() : Iterable<CStudent>
    {
        return serviceStudents.getWithProblems()
    }
    @GetMapping(path = ["/problems/max"])
    fun getWithMaxProblems() : Iterable<CStudent>
    {
        return serviceStudents.getWithMaxProblems()
    }
    @GetMapping(path = ["/problems/maxinfo"])
    fun getInfoWithMaxProblems() : Iterable<IStudentWithCounter>
    {
        return serviceStudents.getInfoWithMaxProblems()
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