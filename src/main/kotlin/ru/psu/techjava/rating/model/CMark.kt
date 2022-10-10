package ru.psu.techjava.rating.model

import java.util.*

/********************************************************************************************************
 * Оценка за контрольную точку.                                                                         *
 * @author Селетков И.П. 2022 0926.                                                                     *
 */
class CMark {
    /****************************************************************************************************
     * Идентификатор.                                                                                   *
     */
    var id: UUID? = null

    /****************************************************************************************************
     * Балл.                                                                                            *
     */
    var value = 0.0

    /****************************************************************************************************
     * Студент.                                                                                         *
     */
    var student: CStudent? = null
    override fun toString(): String {
        return "Студент: " + (if (student == null) "не указан" else student!!.name) + " Балл: " + value
    }

    /****************************************************************************************************
     * Контрольная точка.                                                                               *
     */
    var control: CControl? = null
}