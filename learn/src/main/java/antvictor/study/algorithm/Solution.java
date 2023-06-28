package antvictor.study.algorithm;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;

import java.util.Arrays;

/**
 * @author Antvictor
 * @date 2023/5/25
 **/
public class Solution {
    public static void main(String[] args) {
        System.out.println(canConstruct("ac", "abc"));
    }
    public static boolean canConstruct(String ransomNote, String magazine) {
        int[] num = new int[26];
        int temp;
        for (int i = 0; i < magazine.length(); i++) {
            temp = magazine.charAt(i) - 'a';
            num[temp]++;
        }

        for (int i = 0; i < ransomNote.length(); i++) {
            temp = ransomNote.charAt(i) - 'a';
            if (num[temp] > 0) {
                num[temp]--;
            } else {
                return false;
            }
        }
        return true;
    }
}
