package com.codepulse.visitlogService.Common;

import org.apache.commons.codec.binary.Hex;
import org.apache.tomcat.util.codec.binary.Base64;

import javax.crypto.*;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.SecretKeySpec;
import java.nio.charset.StandardCharsets;
import java.security.InvalidAlgorithmParameterException;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;

public class AES256Chiper {
    private static volatile  AES256Chiper Instance;

    public static byte[] IV = { 0x00,0x00,0x00,0x00,0x00,0x00,0x00,0x00,0x00,0x00,0x00,0x00,0x00,0x00,0x00,0x00 };

    public static AES256Chiper getInstance() {
        if (Instance == null) {
            synchronized (AES256Chiper.class) {
                if (Instance == null) Instance = new AES256Chiper();
            }
        }
        return Instance;
    }

    private AES256Chiper() {

    }

    public static String getKey (String param1, String param2) {
        return String.format("%s%s%s%s", param1, param2, param2, param1);
    }

    //Encrypt plainText
    public static String encrypt(String encKey, String plainText) throws java.io.UnsupportedEncodingException, NoSuchAlgorithmException, NoSuchPaddingException, InvalidKeyException, InvalidAlgorithmParameterException, IllegalBlockSizeException, BadPaddingException {

        byte[] keyData = encKey.getBytes("UTF-8");

        SecretKey secretKey = new SecretKeySpec(keyData, "AES");

        Cipher cipher = Cipher.getInstance("AES/CBC/PKCS5Padding");
        cipher.init(Cipher.ENCRYPT_MODE, secretKey, new IvParameterSpec(IV));

        byte[] encData  = cipher.doFinal(plainText.getBytes("UTF-8"));

        return ConvertStringToHex(new String(Base64.encodeBase64(encData)));
    }

    //Decrypt encText
    public static String decrypt(String encKey, String encText) throws java.io.UnsupportedEncodingException, NoSuchAlgorithmException, NoSuchPaddingException, InvalidKeyException, InvalidAlgorithmParameterException, IllegalBlockSizeException, BadPaddingException {

        byte[] keyData = encKey.getBytes("UTF-8");

        SecretKey secretKey = new SecretKeySpec(keyData, "AES");

        Cipher cipher = Cipher.getInstance("AES/CBC/PKCS5Padding");
        cipher.init(Cipher.DECRYPT_MODE, secretKey, new IvParameterSpec(IV));

        byte[] decData = Base64.decodeBase64(ConvertHexToString(encText).getBytes());

        return new String(cipher.doFinal(decData), "UTF-8");
    }

    //String -> Hex
    public static String ConvertStringToHex(String str) {
        // display in lowercase, default
        char[] chars = Hex.encodeHex(str.getBytes(StandardCharsets.UTF_8));

        return String.valueOf(chars);
    }

    // Hex -> Decimal -> Char
    public static String ConvertHexToString(String hex) {

        StringBuilder result = new StringBuilder();

        // split into two chars per loop, hex, 0A, 0B, 0C...
        for (int i = 0; i < hex.length() - 1; i += 2) {

            String tempInHex = hex.substring(i, (i + 2));

            //convert hex to decimal
            int decimal = Integer.parseInt(tempInHex, 16);

            // convert the decimal to char
            result.append((char) decimal);
        }

        return result.toString();
    }
}
