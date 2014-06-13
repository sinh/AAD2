/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package s04.cryptography;

import java.io.UnsupportedEncodingException;
import java.security.InvalidKeyException;
import java.security.KeyPair;
import java.security.KeyPairGenerator;
import java.security.NoSuchAlgorithmException;
import java.security.PrivateKey;
import java.security.PublicKey;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.crypto.BadPaddingException;
import javax.crypto.Cipher;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.NoSuchPaddingException;

/**
 *
 * @author SinhNX <sinhnx@fpt.edu.vn>
 */
public class RSA_algorithm {
    public static void main(String[] args) {
        try {
            String msg = "Cộng hòa xã hội chủ nghĩa Việt Nam";

            KeyPairGenerator kpg = KeyPairGenerator.getInstance("RSA");
            KeyPair kp = kpg.genKeyPair();
            PublicKey publicKey = kp.getPublic();
            PrivateKey privateKey = kp.getPrivate();

            //Encrypt
            Cipher ci = Cipher.getInstance("RSA");
            ci.init(Cipher.ENCRYPT_MODE, publicKey);

            //Xau da duoc ma hoa
            byte[] encrypted = ci.doFinal(msg.getBytes("UTF-8"));
            String strEncrypted = new String(encrypted, "UTF-8");
            System.out.println("ENCRYPT: " + strEncrypted);

            //Giai ma
            ci.init(Cipher.DECRYPT_MODE, privateKey);
            //Xau da duoc giai ma
            byte[] decrypted = ci.doFinal(encrypted);
            String strDecrypted = new String(decrypted, "UTF-8");
            System.out.println("DECRYPT: " + strDecrypted);
        } catch (NoSuchAlgorithmException | NoSuchPaddingException | InvalidKeyException | IllegalBlockSizeException | BadPaddingException | UnsupportedEncodingException ex) {
            Logger.getLogger(RSA_algorithm.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
