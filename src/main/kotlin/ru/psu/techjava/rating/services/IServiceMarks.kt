package ru.psu.techjava.rating.services

import ru.psu.techjava.rating.model.CMark

interface IServiceMarks {
    fun save(mark : CMark)
}