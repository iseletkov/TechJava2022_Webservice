package ru.psu.techjava.rating

import org.junit.jupiter.api.Assertions
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.context.SpringBootTest
import ru.psu.techjava.rating.model.CStudent
import ru.psu.techjava.rating.repositories.IRepositoryStudents
import java.util.*

/********************************************************************************************************
 * Класс с автоматическими тестами функционала репозитория студентов.                                   *
 * @author Селетков И.П. 2022 1107.                                                                     *
 *******************************************************************************************************/
@SpringBootTest
class CTestRepositoryStudents {
    @Autowired
    private lateinit var repositoryStudents : IRepositoryStudents

    /****************************************************************************************************
     * На всякий случай перед каждыйм тестом из этого файла очищаем таблицу студентов в виртуальной     *
     * СУБД, чтобы предыдущий тест не влиял на следующий.                                               *
     ***************************************************************************************************/
    @BeforeEach
    fun cleanUp() {
        repositoryStudents.deleteAll()
    }

    /****************************************************************************************************
     * Проверка создания пользователя в СУБД и чтения всех пользователей.                               *
     ***************************************************************************************************/
    @Test
    fun createAndRetrieveStudent(){
        val id                              = UUID.randomUUID()
        val name                            = "Test"
        val student                         = CStudent(id, name)
        repositoryStudents.save(student)

        val students                        = repositoryStudents.findAll()
        Assertions.assertEquals(1, students.count())

        Assertions.assertEquals(id, students.first().id)
        Assertions.assertEquals(name, students.first().name)
    }
}