package ru.psu.techjava.rating.model

import java.util.*
/********************************************************************************************************
 * Специальный интерфейс, позволяющий получить результаты запроса из СУБД, которые не укладываются      *
 * в сущность студента (здесь дополнительно есть поле - количество count).                              *
 * @author Селетков И.П. 2022 0926.                                                                     *
 *******************************************************************************************************/
interface IStudentWithCounter {
    val id: UUID
    val name: String
    val count: Int
}