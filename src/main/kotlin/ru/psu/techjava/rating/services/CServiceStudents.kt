package ru.psu.techjava.rating.services

import org.springframework.http.ResponseEntity
import org.springframework.stereotype.Service
import ru.psu.techjava.rating.model.CStudent
import ru.psu.techjava.rating.model.IStudentWithCounter
import ru.psu.techjava.rating.repositories.IRepositoryStudents
import java.util.*
/********************************************************************************************************
 * Сервис с бизнес-логикой для работы со списком студентов.                                             *
 * @author Селетков И.П. 2022 1107.                                                                     *
 *******************************************************************************************************/
@Service
class CServiceStudents
/********************************************************************************************************
 * Конструктор.                                                                                         *
 * @param repositoryStudents - репозиторий с запросами к таблице студентов в СУБД                       *
 * (устанавливается Spring-ом).                                                                         *
 *******************************************************************************************************/
(
    val repositoryStudents                  : IRepositoryStudents
)                                           : IServiceStudents
{
    /****************************************************************************************************
     * Список всех студентов.                                                                           *
     ***************************************************************************************************/
    override fun getAll()                   :Iterable<CStudent>
    {
        return repositoryStudents.findAll()
    }
    /****************************************************************************************************
     * Поиск студента по идентификатору.                                                                *
     * @param id - идентификатор студента для поиска в формате UUID.                                    *
     ***************************************************************************************************/
    override fun getById(
        id                                  : UUID
    )                                       : ResponseEntity<CStudent>
    {
        return repositoryStudents.findById(id)
            .map { student -> ResponseEntity.ok(student) }
            .orElse(ResponseEntity.notFound().build())
    }
    override fun getByName(name: String) : Iterable<CStudent>
    {
        return repositoryStudents.findByNameNative(name)
    }
    override fun getByMarksValueLessEqual(
        value                               : Double
    )                                       : List<CStudent>
    {
        return repositoryStudents.findAllByMarksValueLessThanEqual(value)
    }

    override fun getWithProblems()                   : List<CStudent>
    {
        return repositoryStudents.findAllByMarksValueLessThanEqual(50.0)
    }
    override fun getWithMaxProblems()                : List<CStudent>
    {
        return repositoryStudents.getStudentsWithMaxProblems()
    }
    override fun getInfoWithMaxProblems()            : List<IStudentWithCounter>
    {
        return repositoryStudents.getInfoWithMaxProblems()
    }

    /****************************************************************************************************
     * Создание/изменение студента.                                                                     *
     * @param student - данные студента.                                                                *
     ***************************************************************************************************/
    override fun save(
        student                             : CStudent
    )
    {
        repositoryStudents.save(student)
    }
    /****************************************************************************************************
     * Удаление студента.                                                                               *
     * @param student - данные студента.                                                                *
     ***************************************************************************************************/
    override fun delete(
        student                             : CStudent
    )
    {
        repositoryStudents.delete(student)
    }
    /****************************************************************************************************
     * Удаление студента по идентификатору.                                                             *
     * @param id - идентификатор студента для удаления.                                                 *
     ***************************************************************************************************/
    override fun deleteById(
        id                                  : UUID
    )                                       : String
    {
        return if (repositoryStudents.existsById(id)) {
            repositoryStudents.deleteById(id)
             "Элемент удалён"
        }
        else
            "Элемент не найден"
    }
}