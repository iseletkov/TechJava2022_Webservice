package ru.psu.techjava.rating.services

import org.apache.poi.xwpf.usermodel.XWPFDocument
import org.springframework.stereotype.Service
import java.io.ByteArrayInputStream
import java.io.ByteArrayOutputStream
import java.io.IOException
import java.io.InputStream
import javax.servlet.http.HttpServletResponse

@Service
class CServiceWord : IServiceWord {
    override fun downloadWord(response: HttpServletResponse) {
        try {

            val doc = XWPFDocument()

            val b = ByteArrayOutputStream()
            doc.write(b) // doc should be a XWPFDocument

            val inputStream: InputStream = ByteArrayInputStream(b.toByteArray())
            // copy it to response's OutputStream
            org.apache.commons.io.IOUtils.copy(inputStream, response.outputStream);
            response.flushBuffer();
        } catch ( ex: IOException) {
            //log.info("Error writing file to output stream. Filename was '{}'", fileName, ex);
            //throw new RuntimeException("IOError writing file to output stream");
        }
    }
}