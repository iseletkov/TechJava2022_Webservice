package ru.psu.techjava.studentmarks.techjava2022_lab2.repositories

import org.springframework.data.repository.CrudRepository
import org.springframework.stereotype.Repository
import ru.psu.techjava.studentmarks.techjava2022_lab2.model.CStudent
import java.util.*

@Repository
interface IRepositoryStudents : CrudRepository<CStudent, UUID>
{

}