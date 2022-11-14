package ru.psu.techjava.rating.repositories

import org.springframework.data.jpa.repository.Query
import org.springframework.data.repository.CrudRepository
import org.springframework.stereotype.Repository
import ru.psu.techjava.rating.model.CStudent
import ru.psu.techjava.rating.model.IStudentWithCounter
import java.util.*

/********************************************************************************************************
 * Интерфейс с заголовками методов для доступа к данным в СУБД.                                         *
 * @author Селетков И.П. 2022 1114                                                                      *
 *******************************************************************************************************/
@Repository
interface IRepositoryStudents               : CrudRepository<CStudent, UUID>
{
    //Типовые методы наследуются из интерфйса CrudRepository

    /****************************************************************************************************
     * Список студентов с именем name (реализуется средствами Spring по наименованию метода).           *
     * @param name - имя для фильтрации студентов.                                                      *
     * @return список отфильтрованных студентов.                                                        *
     ***************************************************************************************************/
    fun findByName(
        name                                : String
    )                                       : List<CStudent>


    /****************************************************************************************************
     * Список студентов с именем name (через запрос JPQL).                                              *
     * @param name - имя для фильтрации студентов.                                                      *
     * @return список отфильтрованных студентов.                                                        *
     ***************************************************************************************************/
    @Query("SELECT s FROM CStudent s WHERE s.name = ?1")
    fun findByNameCustom(
        name                                : String
    )                                       : List<CStudent>

    /****************************************************************************************************
     * Список студентов с именем name (через запрос на обычном SQL).                                    *
     * @param name - имя для фильтрации студентов.                                                      *
     * @return список отфильтрованных студентов.                                                        *
     ***************************************************************************************************/
    @Query("SELECT * FROM students s WHERE s.name = ?1",
        nativeQuery                         = true
    )
    fun findByNameNative(
        name                                : String
    )                                       : List<CStudent>

    /****************************************************************************************************
     * Список студентов с оценками, у которых значение меньше value.                                    *
     * @param value - параметр для фильтрации студентов по оценкам.                                     *
     * @return список отфильтрованных студентов.                                                        *
     ***************************************************************************************************/
    fun findAllByMarksValueLessThanEqual(
        value                               : Double
    )                                       : List<CStudent>
    /****************************************************************************************************
     * Список студентов с максимальным количеством проблем (оценок с баллами <50%).                     *
     * @return список отфильтрованных студентов.                                                        *
     ***************************************************************************************************/
    @Query("""
        -- Запрос возвращает данные максимально проблемных студентов по идентификатору из внутреннего запроса
        select s1.* from students s1,
        (
            -- Запрос возвращает идентификаторы максимально проблемных студентов и количество проблем.
            select * from (
                -- Внутренний запрос возвращает таблицу соответствия студентов и количества их плохих оценок.
                select s.id, s.name, count(*) as count
                from students s
                -- Объединяем таблицу студентов и оценок по ключу - идентификатору студента.
                left join marks m on s.id=m.student_id
                -- Оставляем только записи с плохим значением
                where m.vll<50
                -- группируем по студенту
                group by s.id, s.name
            ) as t
            -- Сравнение количества проблем очередного студента с максимальным количеством проблем.
            where t.count in
            (
                -- Запрос возвращает максимальное количество проблем.
                select max(t1.cnt) from (
                    -- Внутренний запрос возвращает таблицу соответствия студентов и количества их плохих оценок.
                    select s.id, count(*) as cnt
                    from students s
                    left join marks m on s.id=m.student_id
                    where m.vll<50
                    group by s.id
                ) as t1
            ) 
        ) as t3
        where s1.id = t3.id
        """,
        nativeQuery                             = true
    )
    fun getStudentsWithMaxProblems()            : List<CStudent>

    /****************************************************************************************************
     * Список студентов с максимальным количеством проблем (оценок с баллами <50%) и количество этих    *
     * проблем. Комментарии к запросу см. в предыдущем методе.                                          *
     * @return список объектов типа данные студента + количество проблем.                               *
     ***************************************************************************************************/
    @Query("""
        select cast(t.id as varchar), t.name, t.count from (
            select s.id, s.name, count(*) as count
            from students s
            left join marks m on s.id=m.student_id
            where m.vll<50
            group by s.id, s.name
        ) as t
        where t.count in
        (
            select max(t1.cnt) from (
                select s.id, count(*) as cnt
                from students s
                left join marks m on s.id=m.student_id
                where m.vll<50
                group by s.id
            ) as t1
        )
        """,
        nativeQuery                         = true
    )
    fun getInfoWithMaxProblems()            : List<IStudentWithCounter>


}