package ru.psu.techjava.rating.model

import java.time.LocalDate
import java.util.*

/********************************************************************************************************
 * Контрольная точка.                                                                                   *
 * @author Селетков И.П. 2022 1030.                                                                     *
 */
class CControl {
    /****************************************************************************************************
     * Идентификатор.                                                                                   *
     */
    var id: UUID? = null

    /****************************************************************************************************
     * Дата проведения.                                                                                 *
     */
    var date: LocalDate? = null

    /****************************************************************************************************
     * Наименование.                                                                                    *
     */
    var name: String? = null
}