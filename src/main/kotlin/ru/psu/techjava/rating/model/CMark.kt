package ru.psu.techjava.rating.model

import com.fasterxml.jackson.annotation.JsonIncludeProperties
import java.util.*
import javax.persistence.Column
import javax.persistence.Entity
import javax.persistence.Id
import javax.persistence.JoinColumn
import javax.persistence.ManyToOne
import javax.persistence.Table

/********************************************************************************************************
 * Оценка за контрольную точку.                                                                         *
 * @author Селетков И.П. 2022 0926.                                                                     *
 */
@Entity
@Table(name = "marks")
data class CMark (
    /****************************************************************************************************
     * Идентификатор.                                                                                   *
     */
    @Id
    var id: UUID? = null,
    /****************************************************************************************************
     * Балл %.                                                                                            *
     */
    @Column(name="vll")
    var value : Double = 0.0,
    /****************************************************************************************************
     * Студент.                                                                                         *
     */
    @ManyToOne
    @JoinColumn(name="student_id", nullable = false)
    @JsonIncludeProperties(value = ["id"])
    var student: CStudent? = null
)
{
    override fun toString(): String {
        return "Студент: " + (if (student == null) "не указан" else student!!.name) + " Балл: " + value
    }

//    /****************************************************************************************************
//     * Контрольная точка.                                                                               *
//     */
//    var control: CControl? = null
}