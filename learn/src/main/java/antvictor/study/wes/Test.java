package antvictor.study.wes;

import java.util.Calendar;

public class Test {


    public static void main(String [] args) {

        String test = " /1/2/3";
        System.out.println(test.substring(test.lastIndexOf("/", test.lastIndexOf("/") - 1)));
    }
}
