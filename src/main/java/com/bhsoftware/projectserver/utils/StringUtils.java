package com.bhsoftware.projectserver.utils;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Random;

public class StringUtils {

    public static String getRandomFileName() {
        StringBuffer sb = new StringBuffer();
        SimpleDateFormat simpleDateFormat=new SimpleDateFormat("yyyyMMdd");
        Date date = new Date();
        String str = simpleDateFormat.format(date);
        Random random = new Random();
        int rannum = (int) (random.nextDouble() * (99999 - 10000 + 1)) + 10000;// 获取5位随机数
        String name= rannum + str;// 当前时间
        sb.append(name);
        return sb.toString();
    }

}
