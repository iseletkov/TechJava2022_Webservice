package ru.psu.techjava.rating.services

import org.apache.poi.xssf.usermodel.XSSFWorkbook
import org.springframework.stereotype.Service
import org.springframework.web.multipart.MultipartFile
import ru.psu.techjava.rating.model.CStudent
import ru.psu.techjava.rating.repositories.IRepositoryStudents
import java.util.*

@Service
class CServiceExcel(
        val repositoryStudents: IRepositoryStudents
) : IServiceExcel
{
    override fun uploadExcel(file: MultipartFile) {
        val wb = XSSFWorkbook(file.inputStream)
        val sheet = wb.getSheetAt(0)
        val row = sheet.getRow(0)
        val cell = row.getCell(3)
        val value = cell.rawValue
        val x = 123

        val st = CStudent(UUID.randomUUID(), "Тест")
        repositoryStudents.save(st)
    }

}