package ru.psu.techjava.rating.services

import org.springframework.web.multipart.MultipartFile

interface IServiceExcel {
    fun uploadExcel(file : MultipartFile)
}