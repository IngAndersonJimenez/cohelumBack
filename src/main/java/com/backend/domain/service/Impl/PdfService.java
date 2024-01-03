package com.backend.domain.service.Impl;

import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.nio.file.FileSystems;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.StandardCopyOption;
import java.util.ArrayList;
import java.util.List;

@Service
public class PdfService {

    private final String pathStorage = "/etc/nginx/pdfs/";

    public List<String> getPdfs(String folder) {
        List<String> pdfs = new ArrayList<>();

        File pdfFolder = new File(pathStorage + folder);
        if (pdfFolder.exists()) {
            File[] files = pdfFolder.listFiles();
            if (files != null) {
                for (File file : files) {
                    pdfs.add(file.getName());
                }
            }
        }

        return pdfs;
    }

    public String storePdf(MultipartFile file, String folder) throws IOException {
        File directoryStorage = new File(this.pathStorage + folder);

        if (!directoryStorage.exists()) {
            directoryStorage.mkdirs();
        }

        String pdfFileName = StringUtils.cleanPath(file.getOriginalFilename());
        Path fullPath = FileSystems.getDefault().getPath(this.pathStorage + folder, pdfFileName);
        Files.copy(file.getInputStream(), fullPath, StandardCopyOption.REPLACE_EXISTING);
        return "/pdfs/" + folder + '/' + pdfFileName;
    }
}
