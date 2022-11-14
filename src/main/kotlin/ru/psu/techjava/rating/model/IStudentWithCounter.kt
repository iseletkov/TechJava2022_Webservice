package ru.psu.techjava.rating.model

import org.hibernate.annotations.Type
import java.util.*

interface IStudentWithCounter {
    val id: UUID
    val name: String
    val count: Int
}