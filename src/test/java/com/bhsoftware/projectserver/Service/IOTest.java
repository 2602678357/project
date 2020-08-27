package com.bhsoftware.projectserver.Service;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.io.*;
import java.util.ArrayList;

@RunWith(SpringRunner.class)
@SpringBootTest
public class IOTest {

    @Test
    public void Test1()throws IOException {
        FileOutputStream fos=new FileOutputStream("fos.txt");
        fos.write("hello".getBytes());
        fos.close();
    }

    @Test
    public void Test2()throws IOException{
        //创建字节流输入对象
        FileInputStream fis=new FileInputStream("fos.txt");
//        int by=0;
        //调用read方法
//        while ((by=fis.read())!=-1){
//            System.out.println((char)by);
//        }
        byte[] bys=new byte[1024];
        int len=0;
        while ((len=fis.read(bys))!=-1){
            System.out.print(new String(bys,0,len));
        }
        //释放资源
        fis.close();
    }



    //记得修改记事本编码格式
    @Test
    public void Test3()throws IOException{
        String srcString="F:\\IOtest\\a.txt";
        String destString="F:\\IOtest\\b.txt";
        //readChar(srcString,destString);
        //readCharList(srcString,destString);
        charOne(srcString,destString);
    }
    //字符缓冲流一次读写一个字符串
    private static void readChar(String srcString,String destString) throws IOException{
        BufferedReader br=new BufferedReader(new InputStreamReader(new FileInputStream(srcString), "UTF-8"));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(destString), "UTF-8"));
        String line = null;
        while ((line=br.readLine())!=null){
            bw.write(line);
            bw.newLine();
            bw.flush();
        }
        br.close();
        bw.close();
    }
    //字符缓冲流一次读写一个字符数组
    private static void readCharList(String srcString,String destString)throws IOException{
        BufferedReader br = new BufferedReader(new FileReader(srcString));
        BufferedWriter bw = new BufferedWriter(new FileWriter(destString));
        char[] chs=new char[1024];
        int len=0;
        while((len=br.read(chs))!=-1){
            bw.write(chs,0,len);
        }
        bw.close();
        br.close();
    }
    //字符缓冲流一次读写一个字符
    private static void charOne(String srcString, String destString)throws IOException{
        BufferedReader br=new BufferedReader(new FileReader(srcString));
        BufferedWriter bw=new BufferedWriter(new FileWriter(destString));
        int ch=0;
        while ((ch=br.read())!=-1){
            bw.write(ch);
        }
        bw.close();
        br.close();
    }
    //把 ArrayList 集合中的字符串数据存储到文本文件
    @Test
    public void Test4()throws IOException{
        ArrayList<String> list=new ArrayList<>();
        list.add("你好");
        list.add("他好");
        list.add("大家好");
        //封装目的地
        BufferedWriter bw=new BufferedWriter(new FileWriter("writer.txt"));
        for (String s:
             list) {
            bw.write(s);
            bw.newLine();
            bw.flush();
        }
        bw.close();
    }


}
