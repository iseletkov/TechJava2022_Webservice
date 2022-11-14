package ru.psu.techjava.rating.services

import ru.psu.techjava.rating.model.CMark
import java.util.UUID

interface IServiceMarks {
    fun save(mark : CMark)

    fun getAllByStudentId(
        id                                  : UUID
    )                                       : List<CMark>
}