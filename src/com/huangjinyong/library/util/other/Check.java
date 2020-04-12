package com.huangjinyong.library.util.other;

/**
 * @author huangjinyong
 */
public class Check {

    public static boolean checkStringLen(String s,int length){
        return s.getBytes().length>=length;
    }

    public static boolean checkNum(String s){
        return s.matches("[1-9][0-9]*");
    }

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

    public static boolean checkIsEmpty(String s){
        return s.matches("\\s*");
    }

    public static boolean checkPeriod(String from,String to){
        if(isEmpty(from)||isEmpty(to)){
            return false;
        }
        String reg="[0-23]";
        if((!from.matches(reg))||(!to.matches(reg))){
            return false;
        }
        int fromTime=Integer.parseInt(from);
        int toTime = Integer.parseInt(to);
        return fromTime<toTime;
    }

    public static boolean isEmpty(String s){
        return "".equals(s);
    }

}
