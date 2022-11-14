package ru.psu.techjava.rating.services

import org.springframework.http.ResponseEntity
import ru.psu.techjava.rating.model.CStudent
import ru.psu.techjava.rating.model.IStudentWithCounter
import java.util.*
/********************************************************************************************************
 * Интерфейс сервиса с бизнес-логикой для работы со списком студентов.                                  *
 * @author Селетков И.П. 2022 1107.                                                                     *
 *******************************************************************************************************/
interface IServiceStudents {
    /****************************************************************************************************
     * Список всех студентов.                                                                           *
     ***************************************************************************************************/
    fun getAll(
    )                                       : Iterable<CStudent>
    /****************************************************************************************************
     * Поиск студента по идентификатору.                                                                *
     * @param id - идентификатор студента для поиска в формате UUID.                                    *
     ***************************************************************************************************/
    fun getById(
        id                                  : UUID
    )                                       : ResponseEntity<CStudent>
    fun getByName(name: String) : Iterable<CStudent>
    fun getByMarksValueLessEqual(
        value                               : Double
    )                                       : List<CStudent>
    fun getWithProblems()                   : List<CStudent>
    fun getWithMaxProblems()                : List<CStudent>
    fun getInfoWithMaxProblems()
    /****************************************************************************************************
     * Создание/изменение студента.                                                                     *
     * @param student - данные студента.                                                                *
     ***************************************************************************************************/
    fun save(
        student                             : CStudent
    )
    /****************************************************************************************************
     * Удаление студента.                                                                               *
     * @param student - данные студента.                                                                *
     ***************************************************************************************************/
    fun delete(
        student                             : CStudent
    )
    /****************************************************************************************************
     * Удаление студента по идентификатору.                                                             *
     * @param id - идентификатор студента для удаления.                                                 *
     ***************************************************************************************************/
    fun deleteById(
        id                                  : UUID
    )                                       : String
}