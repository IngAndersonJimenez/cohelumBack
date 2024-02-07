package com.backend.domain.service.Impl;

import org.jasypt.util.text.AES256TextEncryptor;
import org.springframework.stereotype.Service;

@Service
public class EncryptionService {
    private final AES256TextEncryptor textEncryptor;

    public EncryptionService() {
        this.textEncryptor = new AES256TextEncryptor();
        textEncryptor.setPassword("TuClaveSeguraParaEncriptacion");
    }

    public String decrypt(String encryptedText) {
        return textEncryptor.decrypt(encryptedText);
    }
}
