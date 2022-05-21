package com.example.virtualcurrencywallet_sef.Model;

import javax.crypto.SecretKeyFactory;
import javax.crypto.spec.PBEKeySpec;
import java.nio.charset.StandardCharsets;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;
import java.security.spec.InvalidKeySpecException;
import java.util.Arrays;
import java.util.Base64;
import java.util.Random;

public class Encryptor {
    private static final int ITERATIONS=1000;
    private static final int KEY_LENGTH=256;


    public static byte[] hash(char[] password,byte[] salt) {
        PBEKeySpec spec = new PBEKeySpec(password, salt, ITERATIONS, KEY_LENGTH);
        Arrays.fill(password, Character.MIN_VALUE);
        try {
            SecretKeyFactory skf = SecretKeyFactory.getInstance("PBKDF2WithHmacSHA1");
            return skf.generateSecret(spec).getEncoded();
        } catch (NoSuchAlgorithmException | InvalidKeySpecException e) {
            throw new AssertionError("Error while hashing a password: " + e.getMessage(), e);
        } finally {
            spec.clearPassword();
        }
    }

    public static String encrypt(String string,String salt){
        String returnValue=null;
        byte[] encryptedString=hash(string.toCharArray(),salt.getBytes());
        returnValue= Base64.getEncoder().encodeToString(encryptedString);
        return returnValue;
    }

    public static boolean verify(String providedString,String securedString,String salt){
        boolean returnValue=false;
        String newSecureString=encrypt(providedString,salt);
        returnValue=newSecureString.equals(securedString);
        return returnValue;
    }
}

