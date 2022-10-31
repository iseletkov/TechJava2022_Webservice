package ru.psu.techjava.rating.services

import javax.servlet.http.HttpServletResponse

interface IServiceWord {
    fun downloadWord(response : HttpServletResponse)
}