package ru.psu.techjava.rating.services

import org.springframework.http.ResponseEntity
import ru.psu.techjava.rating.model.CStudent
import ru.psu.techjava.rating.model.IStudentWithCounter
import java.util.*

interface IServiceStudents {
    fun getAll():Iterable<CStudent>
    fun getById(id: UUID) : ResponseEntity<CStudent>

    fun getByName(name: String) : Iterable<CStudent>

    fun getByMarksValueLessEqual(
        value                               : Double
    )                                       : List<CStudent>
    fun getWithProblems()                   : List<CStudent>
    fun getWithMaxProblems()                : List<CStudent>
    fun getInfoWithMaxProblems()            : List<IStudentWithCounter>
    fun save(student: CStudent)
    fun delete(student: CStudent)
    fun deleteById(id : UUID)
}