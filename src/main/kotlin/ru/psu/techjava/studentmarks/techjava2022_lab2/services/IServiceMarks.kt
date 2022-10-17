package ru.psu.techjava.studentmarks.techjava2022_lab2.services

import ru.psu.techjava.studentmarks.techjava2022_lab2.model.CMark

interface IServiceMarks {
    fun save(mark : CMark)
}