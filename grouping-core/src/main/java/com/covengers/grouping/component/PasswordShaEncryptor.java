package com.covengers.grouping.component;

import com.sun.org.apache.xerces.internal.impl.dv.util.HexBin;
import org.springframework.stereotype.Component;

import java.security.MessageDigest;

@Component
public class PasswordShaEncryptor {

    private String encryptedPassword;

    public String encryptPassword(String password) {

        try {
            final MessageDigest messageDigest = MessageDigest.getInstance("SHA-256");
            messageDigest.update(password.getBytes());
            encryptedPassword = new String(HexBin.encode(messageDigest.digest()));
        } catch (Exception e) {
            e.printStackTrace();
        }

        return encryptedPassword;
    }
}
