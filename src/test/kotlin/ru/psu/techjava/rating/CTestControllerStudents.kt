package ru.psu.techjava.rating

import io.mockk.every
import io.mockk.mockk
import org.junit.jupiter.api.Assertions
import org.junit.jupiter.api.Test
import org.springframework.boot.test.context.SpringBootTest
import ru.psu.techjava.rating.controllers.CControllerStudents
import ru.psu.techjava.rating.services.CServiceStudents
import java.util.*

/********************************************************************************************************
 * Класс с автоматическими тестами для проверки функционала контроллера студентов.                      *
 * @author Селетков И.П. 2022 1107.                                                                     *
 *******************************************************************************************************/
@SpringBootTest
class CTestControllerStudents {
    //Продуктивный сервис использовать не будем,
    //его функционал тестируется в отдельном классе,
    //здесь его имитируем с помощью метода mockk()
    private val serviceStudents             : CServiceStudents
                                            = mockk()

    //А вот контроллер студентов продуктивный,
    //но будет вызывать методы имитируемого сервиса.
    private val controllerStudents          = CControllerStudents(serviceStudents)

    /****************************************************************************************************
     * Проверка обработки запроса на удаление студента по идентификатору.                               *
     ***************************************************************************************************/
    @Test
    fun testDeleteStudentById()
    {
        val id                              = UUID.randomUUID()
        every {serviceStudents.deleteById(id)} returns "Объект удалён"

        val result                          = controllerStudents.deleteById(id)
        Assertions.assertEquals("Объект удалён", result)
    }
}