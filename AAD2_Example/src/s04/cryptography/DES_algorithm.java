/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package s04.cryptography;

import java.io.UnsupportedEncodingException;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.crypto.BadPaddingException;
import javax.crypto.Cipher;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.KeyGenerator;
import javax.crypto.NoSuchPaddingException;
import javax.crypto.SecretKey;

/**
 *
 * @author SinhNX <sinhnx@fpt.edu.vn>
 */
public class DES_algorithm {

    public static void main(String[] args) {
        String msg = "Cộng hòa xã hội chủ nghĩa Việt Nam";
        try {
            SecretKey sKey = KeyGenerator.getInstance("DES").generateKey();
            Cipher ci = Cipher.getInstance("DES");

            ci.init(Cipher.ENCRYPT_MODE, sKey);
            byte encrypted[] = ci.doFinal(msg.getBytes("UTF8"));
            System.out.println("Message encrypted: " + new String(encrypted, "UTF-8"));

            ci.init(Cipher.DECRYPT_MODE, sKey);
            byte decrypted[] = ci.doFinal(encrypted);
            System.out.println("Message decrypted: " + new String(decrypted, "UTF-8"));
        } catch (NoSuchAlgorithmException | NoSuchPaddingException | InvalidKeyException | UnsupportedEncodingException | IllegalBlockSizeException | BadPaddingException ex) {
            Logger.getLogger(DES_algorithm.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
