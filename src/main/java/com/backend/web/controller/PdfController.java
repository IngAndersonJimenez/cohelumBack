package com.backend.web.controller;

import com.backend.domain.service.Impl.PdfService;
import com.backend.web.dto.Generic.ResponseDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

@RestController
@RequestMapping("/pdf")
public class PdfController {

    @Autowired
    private PdfService pdfService;

    @PostMapping("/uploadPdf")
    public ResponseEntity<String> createPdf(
            @RequestPart(value = "pdf", required = false) MultipartFile file,
            @RequestParam(value = "folder", required = false) String folder) throws IOException {

        try {
            String storagePath = this.pdfService.storePdf(file, folder);
            return ResponseEntity.ok("PDF almacenado exitosamente en: " + storagePath);
        } catch (IOException ioException) {
            ioException.printStackTrace();
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Error al almacenar el PDF");
        }
    }

    @GetMapping
    public ResponseEntity<ResponseDTO> getPdfsByList(
            @RequestParam(value = "folder", required = false) String folder
    ) throws Exception {
        return ResponseEntity.ok(ResponseDTO
                .builder()
                .responseDTO(this.pdfService.getPdfs(folder))
                .build());
    }
}
