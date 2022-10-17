package ru.psu.techjava.rating.services

import org.springframework.http.ResponseEntity
import ru.psu.techjava.rating.model.CStudent
import java.util.*

interface IServiceStudents {
    fun getAll():Iterable<CStudent>
    fun getById(id: UUID) : ResponseEntity<CStudent>
    fun save(student: CStudent)
    fun delete(student: CStudent)
    fun deleteById(id : UUID)
}