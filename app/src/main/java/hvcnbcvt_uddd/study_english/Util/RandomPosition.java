package hvcnbcvt_uddd.study_english.Util;

import java.util.Random;

public class RandomPosition {
    public static int rdPosition(int size) {
        Random random = new Random();
        int rd = random.nextInt(size);
        return rd;
    }
}
