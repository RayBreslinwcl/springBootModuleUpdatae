package com.ray.springbootoften.service;

import java.util.Random;

/**
 * @ClassDescription:
 * @Author:
 * @Created: 2024/6/28 16:36
 */
public class testonly {

    public static void main(String[] args) {
//        System.out.println(getRandAliasName(2));
        testonly tt=new testonly();
        System.out.println(tt.replace());

    }

    public String replace(){
        String originalString="(select * from %s where %s = '%s') %s";
        return String.format(originalString, "table1","oneid","4422",getRandAliasName(2));
    }

    public String getRandAliasName(int len){
        // 定义一个字符串，包含所有可能的字母
        String letters = "abcdefghijklmnopqrstuvwxyz";

        // 创建一个Length为1的随机字符串生成器
        Random rand = new Random();
        int length = len;

        // 生成随机字符串
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < length; i++) {
            int index = rand.nextInt(letters.length());
            char randomChar = letters.charAt(index);
            sb.append(randomChar);
        }

        // 输出随机生成的字母变量
        return sb.toString();

    }
}
