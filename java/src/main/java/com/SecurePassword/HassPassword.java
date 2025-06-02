package com.SecurePassword;
import org.mindrot.jbcrypt.BCrypt;


public class HassPassword {

    public static String hashPassword(String plainPassword) {
        return BCrypt.hashpw(plainPassword, BCrypt.gensalt());
    }
    public static boolean checkPassword(String plainPassword, String hashedPassword) {
        if (hashedPassword == null || !hashedPassword.startsWith("$2a$")) {
            throw new IllegalArgumentException("Hash inválido para BCrypt");
        }
        return BCrypt.checkpw(plainPassword, hashedPassword);
    }
}