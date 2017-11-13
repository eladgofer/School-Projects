package com.example.eladgofer.project.utils;


public class Utils {

    public String makeTheDots(String content) {
        String temp = null;

        String str = content;
        if (str.length() < 144 && str.length() > 0) {
            temp = str.substring(0, str.length());
            char[] newStr = temp.toCharArray();
            newStr[str.length() - 1] = '.';
            newStr[str.length() - 2] = '.';
            newStr[str.length() - 3] = '.';
            temp = String.valueOf(newStr);

        } else if (str.length() >= 144) {
            temp = str.substring(0, 143);
            char[] newStr = temp.toCharArray();
            newStr[140] = '.';
            newStr[141] = '.';
            newStr[142] = '.';
            temp = String.valueOf(newStr);

        } else if (str.length() == 0) {
            temp = "";
        }
        return temp;
    }
}
