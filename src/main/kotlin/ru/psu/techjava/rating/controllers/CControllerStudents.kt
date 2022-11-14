package ru.psu.techjava.rating.controllers

import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*
import ru.psu.techjava.rating.model.CStudent
import ru.psu.techjava.rating.model.IStudentWithCounter
import ru.psu.techjava.rating.services.IServiceStudents
import java.util.*

/********************************************************************************************************
 * Контроллер REST запросов к справочнику студентов.                                                    *
 * @author Селетков И.П. 2022 1107.                                                                     *
 *******************************************************************************************************/
@RestController
@RequestMapping("/students") //Относительный путь, по которому должны поступать запросы.
class CControllerStudents
/********************************************************************************************************
 * Конструктор.                                                                                         *
 * @param serviceStudents - сервис с логикой для работы со списком студентов (устанавливается Spring-ом)*
 *******************************************************************************************************/
(
    val serviceStudents                     : IServiceStudents
)
{
    /****************************************************************************************************
     * Список всех студентов.                                                                           *
     ***************************************************************************************************/
    @GetMapping
    fun getAll()                            : Iterable<CStudent>
    {
        return serviceStudents.getAll()
    }
    /****************************************************************************************************
     * Поиск студента по идентификатору.                                                                *
     * @param id - идентификатор студента для поиска в формате UUID.                                    *
     ***************************************************************************************************/
    @GetMapping(
        //Етой строкой говорим, что метод вызывать только тогда,
        //когда в параметрах запроса есть параметр id.
        params                              = ["id"]
    )
    fun getById(
        //Значение перменной id заполняется содержимым из параметра запроса id
        @RequestParam id                    : UUID
    )                                       : ResponseEntity<CStudent>
    {
        return serviceStudents.getById(id)
    }
    @GetMapping(params = ["name"])
    fun getById(@RequestParam name: String) : Iterable<CStudent>
    {
        return serviceStudents.getByName(name)
    }
    @GetMapping(params = ["mark_value"])
    fun getByMarkValue(@RequestParam mark_value: Double) : Iterable<CStudent>
    {
        return serviceStudents.getByMarksValueLessEqual(mark_value)
    }
    @GetMapping(path = ["/problems"])
    fun getWithProblems() : Iterable<CStudent>
    {
        return serviceStudents.getWithProblems()
    }
    @GetMapping(path = ["/problems/max"])
    fun getWithMaxProblems() : Iterable<CStudent>
    {
        return serviceStudents.getWithMaxProblems()
    }
    @GetMapping(path = ["/problems/maxinfo"])
    fun getInfoWithMaxProblems() : Iterable<IStudentWithCounter>
    {
        return serviceStudents.getInfoWithMaxProblems()
    }


    /****************************************************************************************************
     * Создание/изменение студента.                                                                     *
     * @param student - данные студента.                                                                *
     ***************************************************************************************************/
    @PostMapping
    fun save(
        //Данные студента должны быть в теле запроса.
        @RequestBody student                : CStudent
    )
    {
        serviceStudents.save(student)
    }
    /****************************************************************************************************
     * Удаление студента.                                                                               *
     * @param student - данные студента.                                                                *
     ***************************************************************************************************/
    @DeleteMapping
    fun delete(
        //Данные студента должны быть в теле запроса.
        @RequestBody student                : CStudent
    )
    {
        serviceStudents.delete(student)
    }
    /****************************************************************************************************
     * Удаление студента по идентификатору.                                                             *
     * @param id - идентификатор студента для удаления.                                                 *
     ***************************************************************************************************/
    @DeleteMapping(
        params                              = ["id"]
    )
    fun deleteById(
        @RequestParam id                    : UUID
    )                                       : String
    {
        return serviceStudents.deleteById(id)
    }

}