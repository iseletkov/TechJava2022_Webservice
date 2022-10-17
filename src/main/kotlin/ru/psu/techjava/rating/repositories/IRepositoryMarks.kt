package ru.psu.techjava.rating.repositories

import org.springframework.data.repository.CrudRepository
import org.springframework.stereotype.Repository
import ru.psu.techjava.rating.model.CMark
import java.util.*

@Repository
interface IRepositoryMarks : CrudRepository<CMark, UUID>
