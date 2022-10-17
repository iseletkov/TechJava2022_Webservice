package ru.psu.techjava.rating.services

import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service
import ru.psu.techjava.rating.model.CMark
import ru.psu.techjava.rating.repositories.IRepositoryMarks

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