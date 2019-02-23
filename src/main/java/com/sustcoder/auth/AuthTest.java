package com.sustcoder.blog.Blog09.auth;


import org.apache.commons.codec.digest.DigestUtils;

import java.io.*;
import java.security.PrivateKey;
import java.security.PublicKey;
import java.util.HashMap;
import java.util.Map;

import static com.sustcoder.blog.Blog09.auth.ZipUtil.unzip;
import static com.sustcoder.blog.Blog09.auth.ZipUtil.zipDir;

public class AuthTest {

    public  static  final String CHAR_SET = "UTF-8";

    public static void main(String[] args) throws Exception {

        //-------------------------------- step 1 生成机器码-------------------------------
        String localMACWithSHA256=getLocalMACWithSHA256();

        //-------------------------------- step 2 基于机器码生成密钥对和license---------------------
        String filePath="D:\\file\\系统授权\\license\\";
        Map<String,String> params=new HashMap<String, String>();
        params.put("localMACWithSHA256",localMACWithSHA256);
        params.put("clientCode","DYCW001");
        params.put("expiredDateStr","2017-10-26");
        params.put("validDateStr","2017-10-26");
        params.put("filePath",filePath);
        saveLicense(params);
        // 压缩
        zipDir(filePath+"pubKey\\", filePath, "license", "UTF-8");
        //-------------------------------- step 2  进行延签---------------------

        boolean[] b=validLicense(filePath,"DYCW001");
        System.out.println("verified1："+ b[0]+"\n verified2："+ b[1]);

    }

    public static void saveLicense(Map<String,String> params){
        try {
            Map<String, Object> keyMap = RSACoder.initKeys(params.get("localMACWithSHA256"));
            PublicKey publicKey = (PublicKey) keyMap.get("publicKey");
            PrivateKey privateKey = (PrivateKey) keyMap.get("privateKey");
            byte[] expiredLicense = RSACoder.sign((params.get("clientCode") + params.get("localMACWithSHA256")).getBytes(CHAR_SET),
                    params.get("expiredDateStr"), privateKey);
            byte[] validLicense = RSACoder.sign((params.get("clientCode") + params.get("localMACWithSHA256")).getBytes(CHAR_SET),
                    params.get("validDateStr"), privateKey);

            // 保存公钥、私钥和license
            saveFile(publicKey.getEncoded(),params.get("filePath")+"pubKey/pubKey");
            saveFile(privateKey.getEncoded(),params.get("filePath")+"priKey/priKey");
            saveFile(expiredLicense,params.get("filePath")+"pubKey/license1");
            saveFile(validLicense,params.get("filePath")+"pubKey/license2");
            saveFile(params.get("clientCode").getBytes() ,params.get("filePath")+"pubKey/clientCode");
            // 压缩
            zipDir(params.get("filePath")+"pubKey/",params.get("filePath"), "license", "UTF-8");
        }catch (Exception e){
            e.printStackTrace();
        }
    }

    public static String getLocalMACWithSHA256(){
       try{
           String localMAC = MACUtils.getLocalMAC();
           String localMACWithSHA256 = DigestUtils.sha256Hex(localMAC.getBytes(CHAR_SET));
           System.out.println("本机MAC：" + localMAC);
           System.out.println("本机MAC SHA256摘要后：" + localMACWithSHA256);
           return localMACWithSHA256;
       }catch (Exception e){
           e.printStackTrace();
           return "";
       }
    }
    public static boolean[] validLicense(String filePath,String clientCode){
        try{
            Map<String,byte[]> map=unzip(filePath+"license");
            byte[] pubKeyData = map.get("pubKey");
            byte[] expiredLicenseData =map.get("license1");
            byte[] validLicenseData =map.get("license2");
            // byte[] pubKeyData = readFileByBytes(filePath+"pubKey/pubKey");
            // byte[] expiredLicenseData = readFileByBytes(filePath+"pubKey/license1");
            // byte[] validLicenseData = readFileByBytes(filePath+"pubKey/license2");
            PublicKey clientPublicKey = RSACoder.loadPubKey(pubKeyData);
            String localMAC = MACUtils.getLocalMAC();
            String localMACWithSHA256 = DigestUtils.sha256Hex(localMAC.getBytes(CHAR_SET));
            boolean verified1 = RSACoder.verify((clientCode + localMACWithSHA256).getBytes(CHAR_SET),
                    expiredLicenseData, clientPublicKey);
            boolean verified2 = RSACoder.verify((clientCode + localMACWithSHA256).getBytes(CHAR_SET),
                    validLicenseData, clientPublicKey);
            return new boolean[]{verified1,verified2};
        }catch (Exception e){
            return new boolean[]{false,false};
        }
    }


    public static  void saveFile(byte[] bytes,String filePath){
        try{
            OutputStream os=new FileOutputStream(filePath);
            os.write(bytes);
            os.close();
        }catch (Exception e){
            e.printStackTrace();
        }

    }
    public static byte[] readFileByBytes(String filePath) throws IOException {
        File file = new File(filePath);
        if(!file.exists()) {
            throw new FileNotFoundException(filePath);
        } else {
            ByteArrayOutputStream bos = new ByteArrayOutputStream((int)file.length());
            BufferedInputStream in = null;
            try {
                in = new BufferedInputStream(new FileInputStream(file));
                short bufSize = 1024;
                byte[] buffer = new byte[bufSize];
                boolean len = false;

                int len1;
                while(-1 != (len1 = in.read(buffer, 0, bufSize))) {
                    bos.write(buffer, 0, len1);
                }

                byte[] var7 = bos.toByteArray();
                return var7;
            } finally {
                try {
                    if(in != null) {
                        in.close();
                    }
                } catch (IOException var14) {
                    var14.printStackTrace();
                }
                bos.close();
            }
        }
    }

}
