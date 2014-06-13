/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package s04.cryptography;

import com.sun.org.apache.xerces.internal.impl.dv.util.Base64;
import java.io.UnsupportedEncodingException;
import java.security.InvalidAlgorithmParameterException;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.security.spec.InvalidKeySpecException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.crypto.BadPaddingException;
import javax.crypto.Cipher;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.NoSuchPaddingException;
import javax.crypto.SecretKey;
import javax.crypto.SecretKeyFactory;
import javax.crypto.spec.PBEKeySpec;
import javax.crypto.spec.PBEParameterSpec;

/**
 *
 * @author SinhNX <sinhnx@fpt.edu.vn>
 */
public class PBEWithMD5AndDES {

    public static void main(String[] args) {
        String msg = "Cộng hòa xã hội chủ nghĩa Việt Nam";
        //System.out.print("Enter your password:");
        //char[] password = System.console().readPassword();
        char[] password = "my password".toCharArray();
                
        //Init
        byte[] salt = {
            (byte) 0x12, (byte) 0x32, (byte) 0x12, (byte) 0x56,
            (byte) 0x12, (byte) 0x32, (byte) 0x12, (byte) 0x56
        };
        int count = 20;
        PBEParameterSpec parameterSpec = new PBEParameterSpec(salt, count);
        PBEKeySpec keySpec = new PBEKeySpec(password);
        try {
            SecretKeyFactory keyFactor = SecretKeyFactory.getInstance("PBEWithMD5AndDES");
            SecretKey sk = keyFactor.generateSecret(keySpec);
            
            //encrypt
            Cipher c = Cipher.getInstance("PBEWithMD5AndDES");
            c.init(Cipher.ENCRYPT_MODE, sk, parameterSpec);
            byte encrypted[] = c.doFinal(msg.getBytes("UTF-8"));
            String strEncrypted = Base64.encode(encrypted);
            System.out.println("Encrypted: " + strEncrypted);
            
            c.init(Cipher.DECRYPT_MODE, sk, parameterSpec);
            byte decrypted[] = c.doFinal(Base64.decode(strEncrypted));
            System.out.println("Decrypted: " + new String(decrypted, "UTF-8"));
        } catch (NoSuchAlgorithmException | InvalidKeySpecException | NoSuchPaddingException | InvalidKeyException | InvalidAlgorithmParameterException | UnsupportedEncodingException | IllegalBlockSizeException | BadPaddingException ex) {
            Logger.getLogger(PBEWithMD5AndDES.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

}
