package com.example.musjoy.utils;

import sun.misc.BASE64Decoder;
import sun.misc.BASE64Encoder;

import javax.crypto.Cipher;
import javax.crypto.SecretKey;
import javax.crypto.spec.SecretKeySpec;

public class AesUtil {
    private static  String passwordkey = "123";



    /**
     * 获取秘钥
     */
    private static String getKey() throws Exception{

           return SecurityUtil.parseMD5(passwordkey, "16");


    }

    /**
     * 加密
     */
    public static String aesEncode( String content )  {
        try {
            SecretKey key=new SecretKeySpec(getKey().getBytes(), "AES");
            Cipher cipher=Cipher.getInstance("AES");
            cipher.init(Cipher.ENCRYPT_MODE, key);
            byte [] byteEncode =content.getBytes("utf-8");
            byte [] byteAes =cipher.doFinal(byteEncode );
            return new BASE64Encoder().encode(byteAes).replaceAll("[\\s*\t\n\r]", "");
        } catch (Exception e) {
            e.printStackTrace();
        }
        //如果有错就返加nulll
        return null;
    }
    /**
     * 解密
     */
    public static String aesDecode( String content ){
        try {
            SecretKey key=new SecretKeySpec(getKey().getBytes(), "AES");
            Cipher cipher=Cipher.getInstance("AES");
            cipher.init(Cipher.DECRYPT_MODE, key);
            byte [] byteContent= new BASE64Decoder().decodeBuffer(content);
            byte [] byteDecode=cipher.doFinal(byteContent);
            return new String(byteDecode,"utf-8");
        } catch (Exception e) {
            e.printStackTrace();
        }
        //如果有错就返加nulll
        return null;
    }

    public static void main(String[] args) throws Exception{
        //String s = "U2FsdGVkX1/O/z5xS9E9XrVzMvVpfGFtzLDQpG7SGmXLlN2kGJf5N5i1mtUu9eTptHBaMJfvLFi9o3jAcnoFLw==";
        //System.out.println(aesDecode(s));
        System.out.println(getKey());
    }

}
