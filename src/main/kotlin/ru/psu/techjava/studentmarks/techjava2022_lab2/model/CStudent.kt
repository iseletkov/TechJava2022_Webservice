package ru.psu.techjava.studentmarks.techjava2022_lab2.model

import com.fasterxml.jackson.annotation.JsonIncludeProperties
import java.util.*
import javax.persistence.Column
import javax.persistence.Entity
import javax.persistence.OneToMany
import javax.persistence.Table

/********************************************************************************************************
 * Студент.                                                                                             *
 * @author Селетков И.П. 2022 0926.                                                                     *
 */
@Entity
@Table(name = "students")
data class CStudent(
    /****************************************************************************************************
     * Идентификатор.                                                                                   *
     */
    @javax.persistence.Id
    var id: UUID? = null,
    /****************************************************************************************************
     * Ф.И.О.                                                                                           *
     */
    @Column
    var name: String? = "",

    /****************************************************************************************************
     * Список оценок.                                                                                   *
     */
    @OneToMany(mappedBy = "student")
    @JsonIncludeProperties(value = ["id","value"])
    var marks: MutableList<CMark> = mutableListOf()
)