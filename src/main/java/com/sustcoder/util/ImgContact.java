package com.sustcoder.util;

import sun.misc.BASE64Decoder;
import sun.misc.BASE64Encoder;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.*;

/**
 * Created by lenovo on 2017/10/11.
 */
public class ImgContact {

    public static void main(String[] args) {
        try {

            String path="E:\\opencv\\images\\idcard\\imgs\\";
            String img0=path+"111.jpg";
            String img1=path+"222.jpg";
            concat(img0,img1,path+"streamRst.jpg","jpg",1);

            String base641=imageToBase64(readFileByBytes(img0));
            String base642=imageToBase64(readFileByBytes(img1));
            String rst=concat(base641,base642,"jpg",1);
            FileOutputStream out=new FileOutputStream(path+"base64Rst.jpg");
            out.write(base64ToImage(rst));
            out.close();

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static String concat(String base641,String  base642,String formateName,int conType)
            throws IOException{
        byte[] byts1=base64ToImage(base641);
        byte[] byts2=base64ToImage(base642);
        return concat(byts1,byts2,formateName,conType);
    }


    public static void concat(String img0,String img1,String savePath,String formateName,int conType)
            throws IOException {
        InputStream in0=new FileInputStream(img0);
        InputStream in1=new FileInputStream(img1);
        byte[]byts=concat(in0,in1,formateName,conType);
        FileOutputStream out=new FileOutputStream(savePath);
        out.write(byts);
        out.close();
    }

    public static String concat(byte[] byts0,byte[] byts1,String formateName,int conType)
            throws IOException{
        InputStream in0=new ByteArrayInputStream(byts0);
        InputStream in1=new ByteArrayInputStream(byts1);
        byte[] byts=concat(in0,in1,formateName,conType);
        return imageToBase64(byts);
    }

    public static byte[]concat(InputStream in0, InputStream in1, String formateName, int conType)
            throws IOException {

        BufferedImage bi0= ImageIO.read(in0);
        BufferedImage bi1=ImageIO.read(in1);

        int width=0,height=0;
        if(conType==0){//左右拼接
            width=bi0.getWidth()+bi1.getWidth();
            height=bi0.getHeight()>=bi1.getHeight()?bi0.getHeight():bi1.getHeight();
        }else{//上下拼接
            width=bi0.getWidth()>=bi1.getWidth()?bi0.getWidth():bi1.getWidth();
            height=bi0.getHeight()+bi1.getHeight();
        }
        BufferedImage conc=new BufferedImage(width,height,BufferedImage.TYPE_INT_RGB);

        int x0=0,y0=0,widthX0=bi0.getWidth(),heightY0=bi0.getHeight();
        int x1=0,y1=0,widthX1=bi1.getWidth(),heightY1=bi1.getHeight();
        if(conType==0){
            x1=bi0.getWidth();
            y1=0;
        }else{
            x1=0;
            y1=bi0.getHeight();
        }
        Graphics graphics=conc.createGraphics();
        graphics.drawImage(bi0,x0,y0,widthX0,heightY0,null);
        graphics.drawImage(bi1,x1,y1,widthX1,heightY1,null);
        ByteArrayOutputStream output=new ByteArrayOutputStream();
        ImageIO.write(conc,formateName,output);
        output.close();
        in0.close();
        in1.close();
        return output.toByteArray();
    }

    public static String imageToBase64(byte[] data) {
        BASE64Encoder encoder = new BASE64Encoder();
        return encoder.encode(data);
    }
    public static byte[] base64ToImage(String base64) throws  IOException{
        BASE64Decoder decoder = new BASE64Decoder();
        return decoder.decodeBuffer(base64);
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
