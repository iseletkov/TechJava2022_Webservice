package ru.psu.techjava.rating.controllers

import org.springframework.web.bind.annotation.*
import ru.psu.techjava.rating.services.IServiceWord
import javax.servlet.http.HttpServletResponse

@RestController
@RequestMapping("/download")
class CControllerDownload(
        val serviceWord: IServiceWord
) {

    @GetMapping("/")
    fun getFile(
     response: HttpServletResponse
    ) {
        serviceWord.downloadWord(response)
    }
}