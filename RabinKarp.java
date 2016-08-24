/**
 * Created by Administrator on 8/23/2016.
 */

import java.io.*;
import java.util.*;

public class RabinKarp {
    static long power(long x, long y) {
        long p = 1;
        while (y != 0) {
            if ( (y & 1) != 0) {
                p *= x;
                --y;
            }
            x *= x;
            y /= 2;
        }
        return p;
    }
    static long intMod (long a, long b) {
        return (a % b + b) % b;
    }

    static String rabinKarp (String text, String pattern) {
        int n = text.length();
        int m = pattern.length();
        int B = 29;
        long M = 10000007;

        if (n < m)
            return "No match found";
        int hp = 0;
        for (int i = 0; i < m; i++)
            hp = (int)intMod(hp * B + (pattern.charAt(i) - 'a'), M);
        int ht = 0;
        for (int i = 0; i < m; i++)
            ht = (int)intMod(ht * B + (text.charAt(i) - 'a'), M);
        if (ht == hp) {
            int i;
            for (i = 0; i < m; i++)
                if (text.charAt(i) != pattern.charAt(i))
                    break;
            if (i == m)
                return "Match found 1";

        }
        long E = power(B, m - 1) % M;
        for (int i = m; i < n; i++) {
            ht = (int)intMod(ht - intMod((text.charAt(i - m) - 'a') * E, M), M);
            ht = (int)intMod(ht * B, M);
            ht = (int)intMod(ht + (text.charAt(i) - 'a'), M);
            if (ht == hp) {
                int j, x = i - m + 1;
                for ( j = 0; j < m &&  x + j < n; j++) {
                    if (text.charAt(x + j) != pattern.charAt(j))
                        break;
                }
                if (j == m)
                    return "Match found 2";
            }
        }
        return "Not found";
    }

    public static void main(String[] args) {
        String text = "abdxababcyz";
        String pattern = "dx";
        String res = rabinKarp(text, pattern);
        System.out.println(res);
    }
}
