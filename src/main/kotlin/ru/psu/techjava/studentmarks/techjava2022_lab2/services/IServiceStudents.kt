package ru.psu.techjava.studentmarks.techjava2022_lab2.services

import org.springframework.http.ResponseEntity
import ru.psu.techjava.studentmarks.techjava2022_lab2.model.CStudent
import java.util.*

interface IServiceStudents {
    fun getAll():Iterable<CStudent>
    fun getById(id: UUID) : ResponseEntity<CStudent>
    fun save(student: CStudent)
    fun delete(student: CStudent)
    fun deleteById(id : UUID)
}