package com.lztech.site.until;

import java.util.Random;

public class RandomNumber {
    private static int four = 4, tenThousand = 10000;

    /**
     * 产生4位随机数(0000-9999)
     *
     * @return 4位随机数
     */
    public static String getFourRandom() {
        Random random = new Random();
        String fourRandom = random.nextInt(tenThousand) + "";
        int randLength = fourRandom.length();
        if (randLength < four) {
            for (int i = 1; i <= four - randLength; i++) {
                fourRandom = "0" + fourRandom;
            }
        }
        return fourRandom;
    }

}
