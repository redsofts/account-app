package app.acctman.accountmanagerapp.util;

import java.util.Random;

public abstract class UtilityClass {

    static Random aRandom = new Random();

    public static String generateAccountNumber(){
        return String.valueOf((long)(Math.random()*100000 + 3333300000L));
    }

    public static double generateRandomAmount(){
        Random random = new Random();
        return random.nextDouble();
    }
}
