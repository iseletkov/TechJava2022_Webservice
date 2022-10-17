package ru.psu.techjava.studentmarks.techjava2022_lab2.services

import org.hibernate.PropertyValueException
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service
import ru.psu.techjava.studentmarks.techjava2022_lab2.model.CMark
import ru.psu.techjava.studentmarks.techjava2022_lab2.repositories.IRepositoryMarks

@Service
class CServiceMarks : IServiceMarks
{
    @Autowired
    lateinit var repositoryMarks: IRepositoryMarks

    override fun save(mark: CMark) {
        try {
            repositoryMarks.save(mark)
        }
        catch (e : Exception)
        {
            throw Exception("Не указаны обязательные данные для оценки!", e)
        }
    }
}