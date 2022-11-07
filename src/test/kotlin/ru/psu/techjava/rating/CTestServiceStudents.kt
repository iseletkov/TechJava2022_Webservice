package ru.psu.techjava.rating

import io.mockk.every
import io.mockk.mockk
import org.junit.jupiter.api.Assertions
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import org.springframework.boot.test.context.SpringBootTest
import ru.psu.techjava.rating.model.CStudent
import ru.psu.techjava.rating.repositories.IRepositoryStudents
import java.util.*

import ru.psu.techjava.rating.services.CServiceStudents

/********************************************************************************************************
 * Класс с автоматическими тестами для проверки функционала сервиса студентов.                          *
 * @author Селетков И.П. 2022 1107.                                                                     *
 *******************************************************************************************************/
@SpringBootTest
class CTestServiceStudents {
    //Список пользователей будет играть роль виртуальной БД,
    //чтобы не затрагивать продуктивную БД и методы репозитория,
    //которые тестируются в другом файле.
    private val students                    : MutableList<CStudent>
                                            = mutableListOf()

    //Продуктивный репозиторий использовать не будем,
    //его функционал тестируется в отдельном классе,
    //здесь его имитируем с помощью метода mockk()
    private val repositoryStudents          : IRepositoryStudents
                                            = mockk()

    //А вот сервис студентов продуктивный,
    //но будет вызывать методы имитируемого репозитория.
    private val serviceStudents             = CServiceStudents(repositoryStudents)

    /****************************************************************************************************
     * Перед каждым тестом чистим список студентов, чтобы тесты не влияли друг на друга.                *
     ***************************************************************************************************/
    @BeforeEach
    fun cleanUp() {
        students.clear()
    }
    /****************************************************************************************************
     * Проверка логики создания студента и получения списка студентов.                                  *
     ***************************************************************************************************/
    @Test
    fun createSaveAndRetrieveStudent(){

        //Создаётся тестовый объект-студент в оперативной памяти.
        val id                              = UUID.fromString("d13a1260-64dc-4256-96eb-3663b5b77f83")
        val name                            = "Test2"
        val student                         = CStudent(id, name)

        //Говорим, что при каждом вызове метода save репозитория,
        //если в качестве параметра передаётся конкретный тестовый студент,
        //работу метода будем имитировать кодом, который в последних фигурных скобках.
        //Для произвольного студента работать не будет.
        every {repositoryStudents.save(student)} answers {
            //Добавляем студента в список.
            students.add(student)
            student
        }
        //При каждой попытке вызова метода findAll имитируем его результат
        //созданным выше списком студентов.
        every {repositoryStudents.findAll()} returns students

        //Собственно сам сценарий тестирования.
        //Вызываем логику сохранения студента.
        serviceStudents.save(student)
        //Вызываем логику чтения всех студентов
        val temp = serviceStudents.getAll()
        //Проверяем результат.
        Assertions.assertEquals(1, temp.count())
        Assertions.assertEquals(id, temp.first().id)
        Assertions.assertEquals(name, temp.first().name)

    }
    /****************************************************************************************************
     * Проверка логики удаления студента.                                                               *
     ***************************************************************************************************/
    @Test
    fun deleteNotExistedStudent(){

        //Какой бы идентификатор не передавали,
        //говорим, что его нет в БД.
        every {repositoryStudents.existsById(any())} returns false

        //Пытаемся удалить пользователя по идентификатору.
        val id                              = UUID.fromString("d13a1260-64dc-4256-96eb-3663b5b77f83")
        val result                          = serviceStudents.deleteById(id)
        //Проверяем, что метод действитеьно понял, что такого пользователя нет.
        assert(result=="Элемент не найден")
    }

}