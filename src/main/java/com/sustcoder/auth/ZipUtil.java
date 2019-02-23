package com.sustcoder.blog.Blog09.auth;

import org.apache.tools.ant.Project;
import org.apache.tools.ant.taskdefs.Zip;
import org.apache.tools.ant.types.FileSet;

import java.io.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.zip.*;

public class ZipUtil {
    private static final int BUFFER = 8192;

    private File zipFile;

    //构造
    public ZipUtil() {

    }

    public ZipUtil(String pathName) {
        zipFile = new File(pathName);
    }

    public void compress(String... pathName) {
        ZipOutputStream out = null;
        try {
            FileOutputStream fileOutputStream = new FileOutputStream(zipFile);
            CheckedOutputStream cos = new CheckedOutputStream(fileOutputStream, new CRC32());
            out = new ZipOutputStream(cos);
            String basedir = "";
            for (int i = 0; i < pathName.length; i++) {
                compress(new File(pathName[i]), out, basedir);
            }
            out.close();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    public void compress(String srcPathName) {
        File file = new File(srcPathName);
        if (!file.exists()) {
            throw new RuntimeException(srcPathName + "文件或目录 不存在！");
        }
        try {
            FileOutputStream fileOutputStream = new FileOutputStream(zipFile);
            CheckedOutputStream cos = new CheckedOutputStream(fileOutputStream, new CRC32());
            ZipOutputStream out = new ZipOutputStream(cos);
            String basedir = "";
            compress(file, out, basedir);
            out.close();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    private void compress(File file, ZipOutputStream out, String basedir) {     
        /* 判断是目录还是文件 */
        if (file.isDirectory()) {
            System.out.println("压缩文件夹：" + basedir + file.getName());
            this.compressDirectory(file, out, basedir);
        } else {
            System.out.println("压缩文件：" + basedir + file.getName());
            this.compressFile(file, out, basedir);
        }
    }

    /**
     * 压缩一个目录
     */
    private void compressDirectory(File dir, ZipOutputStream out, String basedir) {
        if (!dir.exists()) {
            return;
        }
        File[] files = dir.listFiles();
        for (int i = 0; i < files.length; i++) {     
            /* 递归 */
            compress(files[i], out, basedir + dir.getName() + "/");
        }
    }

    /**
     * 压缩一个文件
     */
    private void compressFile(File file, ZipOutputStream out, String basedir) {
        if (!file.exists()) {
            return;
        }
        try {
            BufferedInputStream bis = new BufferedInputStream(new FileInputStream(file));
            ZipEntry entry = new ZipEntry(basedir + file.getName());
            out.putNextEntry(entry);
            int count;
            byte data[] = new byte[BUFFER];
            while ((count = bis.read(data, 0, BUFFER)) != -1) {
                out.write(data, 0, count);
            }
            bis.close();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    /**
     * 压缩目录
     *
     * @param filePath
     * @param fileName
     * @return
     */
    public static boolean zipDir(String filePath, String outFilePath, String fileName, String encoding) {
        boolean bool = false;
        try {
            File srcdir = new File(filePath);

            if (!srcdir.exists()) {
                return false;
            }
            //过滤空文件夹
            String[] strs = srcdir.list();
            if (strs == null || strs.length <= 0) {
                return false;
            }


            File zipFile = new File(outFilePath + "/" + fileName);

            Project prj = new Project();
            Zip zip = new Zip();
            zip.setProject(prj);
            zip.setDestFile(zipFile);
            zip.setEncoding(encoding);

            FileSet fileSet = new FileSet();
            fileSet.setProject(prj);
            fileSet.setDir(srcdir);
            //fileSet.setIncludes("*/*.java"); 包括哪些文件或文件夹 eg:zip.setIncludes("*.java");
            fileSet.setExcludes("*.zip");    // 排除文件或文件夹
            fileSet.setExcludes("*.dat");    // 排除文件或文件夹

            zip.addFileset(fileSet);
            zip.execute();

            bool = true;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return bool;
    }



    /**
     * 解压缩
     *
     * @param sZipPathFile 要解压的文件
     * @param sDestPath    解压到某文件夹
     * @return
     */
    @SuppressWarnings("unchecked")
    public static ArrayList unzip(String sZipPathFile, String sDestPath) {
        ArrayList<String> allFileName = new ArrayList<String>();
        FileInputStream fins=null;
        ZipInputStream zins=null;
        FileOutputStream fouts=null;
        try {
            fins = new FileInputStream(sZipPathFile);
            zins = new ZipInputStream(fins);
            ZipEntry ze = null;
            byte[] ch = new byte[256];
            while ((ze = zins.getNextEntry()) != null) {
                File zfile = new File(sDestPath + ze.getName());
                File fpath = new File(zfile.getParentFile().getPath());
                if (ze.isDirectory()) {
                    if (!zfile.exists())
                        zfile.mkdirs();
                    zins.closeEntry();
                } else {
                    if (!fpath.exists())
                        fpath.mkdirs();
                    fouts = new FileOutputStream(zfile);
                    int i;
                    allFileName.add(zfile.getAbsolutePath());
                    while ((i = zins.read(ch)) != -1)
                        fouts.write(ch, 0, i);
                    zins.closeEntry();
                    fouts.close();
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }finally {
            try{
                if(fouts!=null){
                    fouts.close();
                }
            }catch (Exception e){
                e.printStackTrace();
            }
            try{
                if(zins!=null){
                    zins.closeEntry();
                    zins.close();
                }
            }catch (Exception e){
                e.printStackTrace();
            }
            try{
                if(fins!=null){
                    fins.close();
                }
            }catch (Exception e){
                e.printStackTrace();
            }
        }
        return allFileName;
    }

    /**
     * 解压缩
     *
     * @param sZipPathFile 要解压的文件
     * @return
     */
    @SuppressWarnings("unchecked")
    public static Map<String,byte[]> unzip(String sZipPathFile) {
        Map<String,byte[]> map=new HashMap<String, byte[]>();
        FileInputStream fins=null;
        ZipInputStream zins=null;
        try {
            fins = new FileInputStream(sZipPathFile);
            zins = new ZipInputStream(fins);
            ZipEntry ze = null;
            while ((ze = zins.getNextEntry()) != null) {
                if (ze.isDirectory()) {
                    zins.closeEntry();
                } else {
                    byte[] b=new byte[(int)ze.getSize()];
                    zins.read(b);
                    map.put(ze.getName(),b);
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }finally {
            try{
                if(zins!=null){
                    zins.closeEntry();
                    zins.close();
                }
            }catch (Exception e){
                e.printStackTrace();
            }
            try{
                if(fins!=null){
                    fins.close();
                }
            }catch (Exception e){
                e.printStackTrace();
            }
        }
        return map;
    }

    public static void main(String[] args) {
        // ZipUtil zc = new ZipUtil("D:/temp/img/2016-10-09/test.zip");
        // zc.compress("D:/temp/img/2016-10-09/test");

        // 压缩
        String path = "D:\\file\\系统授权\\license\\pubKey\\";
        String outFilePath = "D:\\file\\系统授权\\license\\";
        boolean bool1 = zipDir(path, outFilePath, "license", "UTF-8");
        System.out.println(bool1);

        // 解压
        Map<String,byte[]> map=unzip(outFilePath+"license");
        System.out.println(map.size());
        // ArrayList<String> a = unzip(outFilePath+"license", "D:\\file\\系统授权\\license\\unzip\\"); // 返回解压缩出来的文件列表
        // for (String s : a) {
        //     System.out.println(s);
        // }

    }

} 