package ru.psu.techjava.rating

import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Test
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.context.SpringBootTest
import ru.psu.techjava.rating.model.CStudent
import ru.psu.techjava.rating.repositories.IRepositoryStudents
import ru.psu.techjava.rating.services.IServiceStudents
import java.util.*

@SpringBootTest
class Lab2ApplicationTests {
    @Autowired
    private lateinit var repositoryStudents: IRepositoryStudents



    @Test
    fun testMath() {
        assertEquals(1+2, 3);

    }
    @Test
    fun testRepositoryStudents() {
        val students = repositoryStudents.findAll()

        assertEquals(4, students.count());
        print("PASSED testRepositoryStudents")
    }

    @Test
    fun createStudent(){
        val id = UUID.randomUUID()
        val name = "Test"
        val student = CStudent(id, name)
        repositoryStudents.save(student)

        val students = repositoryStudents.findAll()
        assertEquals(1, students.count());

        assertEquals(id, students.first().id);
        assertEquals(name, students.first().name);
    }



}
