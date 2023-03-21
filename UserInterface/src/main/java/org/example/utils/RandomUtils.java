package org.example.utils;

import org.apache.commons.lang3.RandomStringUtils;
import org.example.forms.LoginForm;

import java.util.Random;

public class RandomUtils {
    private static char commonChar = 'a';

    public static Integer getNumber(int end) {
        Random rand = new Random();
        return rand.nextInt(end);
    }

    public static Integer getTldIndex() {
        Random rand = new Random();
        LoginForm loginForm = new LoginForm();
        return rand.nextInt(1, loginForm.getSizeOfTldDropdownList() - 1);
    }

    public static String getPasswordOrEmail(int start, int end) {
        return "" + commonChar + getDomain(start, end);
    }

    public static String getDomain(int start, int end) {
        Random random = new Random();
        int int_random = random.nextInt(start,end);
        String s = RandomStringUtils.randomAlphanumeric(start, int_random);
        while (!checkIfStringContainsNumbers(s)) {
            s = RandomStringUtils.randomAlphanumeric(start, int_random);
        }
        return s;
    }

    private static boolean checkIfStringContainsNumbers(String s) {
        char[] chars = s.toCharArray();
        boolean capital = false;
        boolean digit = false;
        for (char c : chars){
            if (Character.isDigit(c)){
                digit = true;
            }
            if (Character.isUpperCase(c)){
                capital = true;
            }
        }
        return digit && capital;
    }

}
