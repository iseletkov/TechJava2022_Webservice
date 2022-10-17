package ru.psu.techjava.rating.services

import org.springframework.beans.factory.annotation.Autowired
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.stereotype.Service
import org.springframework.web.server.ResponseStatusException
import ru.psu.techjava.rating.model.CStudent
import ru.psu.techjava.rating.repositories.IRepositoryStudents
import java.util.*

@Service
class CServiceStudents : IServiceStudents
{
    @Autowired
    lateinit var repositoryStudents: IRepositoryStudents

    override fun getAll():Iterable<CStudent>
    {
        return repositoryStudents.findAll()
    }
    override fun getById(id: UUID) : ResponseEntity<CStudent>
    {
        return repositoryStudents.findById(id)
            .map { student -> ResponseEntity.ok(student) }
            .orElse(ResponseEntity.notFound().build())
    }
    override fun save(student: CStudent)
    {
        repositoryStudents.save(student)
    }
    override fun delete(student: CStudent)
    {
        repositoryStudents.delete(student)
    }

    override fun deleteById(id : UUID)
    {
        if (repositoryStudents.existsById(id)) {
            repositoryStudents.deleteById(id)
            return
        }
        throw ResponseStatusException(
            HttpStatus.NOT_FOUND, "entity not found"
        )
    }
}