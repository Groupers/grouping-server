package com.covengers.grouping.component;

import com.covengers.grouping.vo.EncryptPasswordResultVo;
import org.springframework.stereotype.Component;

import java.security.MessageDigest;

@Component
public class PasswordShaEncryptor {

    public EncryptPasswordResultVo encrytPassword(String password) {
        String encryptedPassword = "";

        try {
            final MessageDigest md = MessageDigest.getInstance("SHA-256");
            md.update(password.getBytes());
            encryptedPassword = byteArraytoHex(md.digest());
        } catch (Exception e) {
            e.printStackTrace();
        }

        return EncryptPasswordResultVo.builder()
                .encrytedPassword(encryptedPassword)
                .build();
    }

    public String byteArraytoHex(byte byteArray[]) {
        StringBuffer stringBuffer = new StringBuffer();
        for(byte getByte : byteArray) {
            stringBuffer.append(String.format("%02x", getByte));
        }

        return stringBuffer.toString();
    }
}
