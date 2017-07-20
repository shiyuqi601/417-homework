package com.exercise_6;

import java.io.*;

/**
 * Created by yuqishi on 4/1/17.
 */
public class IO {

    public static void main(String []argus){
        //open a file
        File f = new File("/Users/yuqishi/Google Drive/2nd Quarter/CEE 584/" +
                "Assignment/3/NFL Super Bowl Description.txt");
        //get the path of the file
        f.getAbsolutePath();
        //get the  size of the file, which represented as byte
        f.length();

        //create a new file
        File d = new File("//Users/yuqishi/Desktop/src/com/exercise_6/aaa.txt");
        if (!d.exists()){
            try {
                d.createNewFile();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }else {
            System.out.println("the file already exists!");
        }

        //create a new folder
        File q = new File("//Users/yuqishi/Desktop/src/com/exercise_6/abc");
        if (q.isDirectory()){
            System.out.println("the folder already exists!");
        }else {
            f.mkdir();
        }

        //list all file of a folder
        File p = new File("//Users/yuqishi/Desktop/src/com/exercise_6");
        //if abc is a folder?
        if (p.isDirectory()){
            //p.list return an array of file names in the folder,
            // we need to create an array use File class
            File list[] = p.listFiles();
            for (int i = 0; i < list.length; i++){
                System.out.println("file name is: "+list[i].getName());
            }
        }

        //read the file
        File k = new File("//Users/yuqishi/Desktop/src/com/exercise_6/aaa.txt");
        FileInputStream fis = null;
        try {
            fis = new FileInputStream(k);
            byte[] bytes = new byte[1024];  //byte[] is a 字节数组
            int n = fis.read(bytes);

            while (n != 156){
                String s = new String(bytes, 0, n);     //从字节数组byte的第0位开始，数n个字节写入字符串S
//                System.out.println(n);
                System.out.println(s);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }finally {      //don't forget to close the file inside "finally"
            try {
                fis.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

        //write content to a file
        //the file must be a new one, if it already exist, this one will overlay the existed one
        File g = new File("//Users/yuqishi/Desktop/src/com/exercise_6/bbb.txt");
        FileOutputStream fos = null;
        try {
            fos = new FileOutputStream(g);
            String s = "Hello World! \n I love Java! \n Java is the best language!\n";
            fos.write(s.getBytes());
        } catch (Exception e) {
            e.printStackTrace();
        }finally {
            try {
                fos.close();
            }catch (IOException e){
                e.printStackTrace();
            }
        }


    }
}
