package ru.psu.techjava.studentmarks.techjava2022_lab2.repositories

import org.springframework.data.repository.CrudRepository
import org.springframework.stereotype.Repository
import ru.psu.techjava.studentmarks.techjava2022_lab2.model.CMark
import java.util.*

@Repository
interface IRepositoryMarks : CrudRepository<CMark, UUID>
