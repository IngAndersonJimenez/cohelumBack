package com.backend.domain.service.Impl;

import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@Service
public class ImageService {

    private static final String CARPETA_ESCRITORIO = System.getProperty("user.home") + File.separator + "Desktop" + File.separator + "local";

    public List<String> getImages() {
        List<String> imagenes = new ArrayList<>();

        File carpeta = new File(CARPETA_ESCRITORIO);
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

    public void createImage(MultipartFile archivo, String nombrePersonalizado) throws IOException {
        File carpeta = new File(CARPETA_ESCRITORIO);
        if (!carpeta.exists()) {
            carpeta.mkdirs();
        }

        String nombreArchivo = StringUtils.cleanPath(nombrePersonalizado + ".png");
        File file = new File(carpeta.getAbsolutePath() + File.separator + nombreArchivo);

        try (FileOutputStream fos = new FileOutputStream(file)) {
            fos.write(archivo.getBytes());
        }
    }
}
