package com.huangjinyong.library.util.other;

public class Check {


    public static String checkUsername(String username){
        String nameRegex="\\w{4,14}";
        boolean matches = username.matches(nameRegex);
        if(matches){
            return "";
        }
        return "用户名必须是4-14位之间的字母数字组合";
    }

    public static String checkPassword(String password){
        boolean matches = password.matches("\\S{8,14}");
        if(matches){
            return "";
        }
        return "密码必须是8-14非空白字符组合";
    }

}
