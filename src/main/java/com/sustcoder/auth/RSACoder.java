package com.sustcoder.auth;

import org.bouncycastle.jce.provider.BouncyCastleProvider;
import org.bouncycastle.util.encoders.Base64;

import javax.crypto.Cipher;
import java.math.BigInteger;
import java.security.*;
import java.security.interfaces.RSAPrivateKey;
import java.security.interfaces.RSAPublicKey;
import java.security.spec.InvalidKeySpecException;
import java.security.spec.RSAPrivateKeySpec;
import java.security.spec.RSAPublicKeySpec;
import java.security.spec.X509EncodedKeySpec;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

public class RSACoder {
    public static final String CHAR_SET = "UTF-8";
    public static final String KEY_ALGORITHM = "RSA";
    public static final String KEY_PROVIDER = "BC";
    public static final String SIGNATURE_ALGORITHM = "SHA256WithRSA";
    public static final SimpleDateFormat form = new SimpleDateFormat("yyyy-MM-dd");

    public static Map<String, Object> initKeys(String seed)
            throws Exception {
        Map keyMap = new HashMap();
        Security.addProvider(new BouncyCastleProvider());
        KeyPairGenerator keyPairGenerator = KeyPairGenerator.getInstance("RSA", "BC");

        keyPairGenerator.initialize(1024, new SecureRandom(seed.getBytes()));
        KeyPair pair = keyPairGenerator.generateKeyPair();
        RSAPublicKey rsaPublicKey = (RSAPublicKey) pair.getPublic();
        RSAPrivateKey rsaPrivateKey = (RSAPrivateKey) pair.getPrivate();

        KeyFactory factory = KeyFactory.getInstance("RSA", "BC");

        RSAPublicKeySpec pubKeySpec = new RSAPublicKeySpec(new BigInteger(rsaPublicKey.getModulus().toString()),
                new BigInteger(rsaPublicKey.getPublicExponent
                        ().toString()));

        RSAPrivateKeySpec priKeySpec = new RSAPrivateKeySpec(new BigInteger(rsaPrivateKey.getModulus().toString()),
                new BigInteger(rsaPrivateKey.getPrivateExponent
                        ().toString()));

        PublicKey publicKey = factory.generatePublic(pubKeySpec);
        PrivateKey privateKey = factory.generatePrivate(priKeySpec);

        keyMap.put("publicKey", publicKey);
        keyMap.put("privateKey", privateKey);

        return keyMap;
    }

    public static byte[] encryptRSA(byte[] data, PrivateKey privateKey)
            throws Exception {
        Cipher cipher = Cipher.getInstance("RSA", "BC");
        cipher.init(1, privateKey);
        return cipher.doFinal(data);
    }

    public static byte[] decryptRSA(byte[] data, PublicKey publicKey)
            throws Exception {
        Cipher cipher = Cipher.getInstance("RSA", "BC");
        cipher.init(2, publicKey);
        return cipher.doFinal(data);
    }

    public static byte[] sign(byte[] encoderData, String dateStr, PrivateKey privateKey)
            throws Exception {
        Signature sig = Signature.getInstance("SHA256WithRSA", "BC");
        sig.initSign(privateKey);
        sig.update(encoderData);
        byte[] license = Base64.encode(sig.sign());

        Date dateVal = form.parse(dateStr);
        byte[] timeStamp = encryptRSA(String.valueOf(dateVal.getTime()).getBytes("UTF-8"), privateKey);

        byte[] finalSign = new byte[timeStamp.length + license.length];
        System.arraycopy(timeStamp, 0, finalSign, 0, timeStamp.length);
        System.arraycopy(license, 0, finalSign, timeStamp.length, license.length);
        return finalSign;
    }

    public static boolean verify(byte[] encoderData, byte[] sign, PublicKey publicKey)
            throws Exception {
        Signature sig = Signature.getInstance("SHA256WithRSA", "BC");
        sig.initVerify(publicKey);
        sig.update(encoderData);

        byte[] timeStamp = new byte[128];
        byte[] license = new byte[sign.length - 128];
        System.arraycopy(sign, 0, timeStamp, 0, 128);
        System.arraycopy(sign, 128, license, 0, sign.length - 128);

        String timeStampVal = new String(decryptRSA(timeStamp, publicKey), "UTF-8");
        if (Long.valueOf(timeStampVal).longValue() < System.currentTimeMillis()) {
            return false;
        }

        return sig.verify(Base64.decode(license));
    }

    public static PublicKey loadPubKey(byte[] data)
            throws NoSuchProviderException, NoSuchAlgorithmException, InvalidKeySpecException {
        KeyFactory factory = KeyFactory.getInstance("RSA", "BC");
        X509EncodedKeySpec bobPubKeySpec = new X509EncodedKeySpec(data);
        return factory.generatePublic(bobPubKeySpec);
    }
}
