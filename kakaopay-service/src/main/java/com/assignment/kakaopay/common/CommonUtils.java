package com.assignment.kakaopay.common;

import java.util.Random;
import java.util.stream.LongStream;

import lombok.experimental.UtilityClass;

@UtilityClass
public final class CommonUtils {

    private static Random random = new Random();

    public static String generateToken() {
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < 3; i++) {
            int idx = random.nextInt(3);
            switch (idx) {
                case 0:
                    sb.append((char)(random.nextInt(26) + 65));
                    break;
                case 1:
                    sb.append((char)(random.nextInt(26) + 97));
                    break;
                case 2:
                    sb.append(random.nextInt(10));
                    break;
                default:
                    break;
            }
        }
        
        return sb.toString();
    }
    
    public static long[] divideMoney(long money, int peopleCount) {
        long avg = money / peopleCount;
        long[] bucket = LongStream.range(0, peopleCount).map(l -> l = avg).toArray();
        
        for (int i = 0; i < bucket.length; i++) {
            long currnt = bucket[i];
            int mvMoney = random.nextInt((int)currnt);
            int mvIdx = random.nextInt(peopleCount);
            bucket[i] -= mvMoney;
            bucket[mvIdx] += mvMoney;
        }
        
        return bucket;
    }
}
