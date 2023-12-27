package com.backend.domain.service.Impl;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.util.ArrayList;
import java.util.List;

@Service
@Slf4j
public class ImageService {


    private final String pathStorage = "/etc/nginx/images/";

    public List<String> getImages(String folder) {
        List<String> imagenes = new ArrayList<>();

        File carpeta = new File(pathStorage + folder);
        if (carpeta.exists()) {
            File[] files = carpeta.listFiles();
            if (files != null) {
                for (File file : files) {
                    imagenes.add(file.getName());
                }
            }
        }

        return imagenes;
    }


    public String storeImage(MultipartFile file, String folder) throws IOException {
        File directoryStorage = new File(this.pathStorage + folder);

        if (!directoryStorage.exists()) {
            directoryStorage.mkdirs();
        }

        String nameFile = StringUtils.cleanPath(file.getOriginalFilename());
        Path pathFull = Paths.get(this.pathStorage + folder, nameFile);
        Files.copy(file.getInputStream(), pathFull, StandardCopyOption.REPLACE_EXISTING);
        return pathFull.toString();
    }


}
