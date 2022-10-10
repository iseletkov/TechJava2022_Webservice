package ru.psu.techjava.rating.controllers

import org.springframework.web.bind.annotation.*
import ru.psu.techjava.rating.model.CStudent
import java.util.*

@RestController
@RequestMapping("/students")
class CControllerStudents
{
    @GetMapping
    fun getAll() : List<CStudent>
    {
        val students = mutableListOf<CStudent>()
        students.add(CStudent(UUID.fromString("84b9e20b-cc32-4cc7-8086-094519d1f1ac"), "ФИО1"))
        students.add(CStudent(UUID.fromString("84b9e20b-cc32-4cc7-8086-094519d1f1ad"), "ФИО2"))
        return students
    }

    // Параметр запроса https://server:port/students?id=84b9e20b-cc32-4cc7-8086-094519d1f1ac
    @GetMapping(params=["id"])
    fun getById(@RequestParam id: UUID) : String
    {
        if (id==UUID.fromString("84b9e20b-cc32-4cc7-8086-094519d1f1ac"))
            return "Идентификаторы равны"
        return "Идентификаторы не равны"
    }

    //Данные берутся из части пути https://server:port/students/84b9e20b-cc32-4cc7-8086-094519d1f1ac
    @GetMapping("/{studentid}/marks")
    fun getByIdSecondWay(@PathVariable studentid: UUID) : String
    {
        if (studentid==UUID.fromString("84b9e20b-cc32-4cc7-8086-094519d1f1ac"))
            return "Идентификаторы равны"
        return "Идентификаторы не равны"
    }

    @PostMapping
    fun create(
            @RequestBody
            student : CStudent
    ){
        println(student)
    }

}