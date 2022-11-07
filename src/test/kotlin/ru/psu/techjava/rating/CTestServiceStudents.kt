package ru.psu.techjava.rating

import org.junit.jupiter.api.Assertions
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import org.mockito.InjectMocks
import org.mockito.Mock
import org.mockito.Mockito
import org.mockito.Mockito.`when`
import org.springframework.boot.test.context.SpringBootTest
import ru.psu.techjava.rating.model.CStudent
import ru.psu.techjava.rating.repositories.IRepositoryStudents
import ru.psu.techjava.rating.services.IServiceStudents
import java.util.*

import ru.psu.techjava.rating.services.CServiceStudents


@SpringBootTest
class CTestServiceStudents {
    private val name = "Test"
    private val id = UUID.fromString("b2618d2d-fe35-4d0c-847e-b3ebfe6cad19")

    private val student1 = CStudent(id, name)
    private val students : MutableList<CStudent> = mutableListOf(student1)
    @Mock
    private lateinit var repositoryStudents : IRepositoryStudents

    @InjectMocks
    private lateinit var serviceStudents: CServiceStudents

    @BeforeEach
    fun setMockOutput() {

        `when`(repositoryStudents.save(Mockito.any(CStudent::class.java))).thenReturn (student1)
        `when`(repositoryStudents.findAll()).thenReturn(students)
    }
    @Test
    fun createStudent(){
        val id = UUID.fromString("d13a1260-64dc-4256-96eb-3663b5b77f83")
        val name = "Test2"
        val student = CStudent(id, name)
        serviceStudents.save(student)



    }
    @Test
    fun readStudents(){

        val students = serviceStudents.getAll()
        Assertions.assertEquals(1, students.count());

        Assertions.assertEquals(id, students.first().id);
        Assertions.assertEquals(name, students.first().name);
    }

}