
import java.io.*;
import java.util.*;

/*
ip = '192.168.0.1' true
ip = '192.168.123.456' false

split the string by '.' => Stirng[]
iterate the arr, all in the range 0~255

Time: O(n)
Space: O(4) ~ O(1)

1.2.3.4.5
12.34.56.oops

special: >4, string(not int)
*/

class prampValidateIPAddress {

    static boolean isNum(String s) {

        for (int i = 0; i < s.length(); i++) {
            char c = s.charAt(i);
            if (c < 48 || c > 57) return false;
        }
        return true;
    }


    static boolean validateIP(String ip) {
        String[] s = ip.split("\\.");

        if (s.length > 4) return false;

        for (String subString : s) {
            if (subString.equals("")) return false;
            if (!isNum(subString)) return false;
            int num = Integer.parseInt(subString);
            if (num < 0 || num > 255) return false;
        }
        return true;
    }

    public static void main(String[] args) {
        String s = ".168.123.456";
        validateIP(s);
    }
}
