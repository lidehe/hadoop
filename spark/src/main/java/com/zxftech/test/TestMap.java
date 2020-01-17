package com.zxftech.test;

import java.util.HashMap;

public class TestMap {
    private static HashMap<Integer, String> map = new HashMap<Integer, String>(2, 0.75f);

    public static void de(int length, int blockNum) {

        int binSize = length / blockNum;
        if (binSize < length % blockNum) {
            de(length, blockNum - 1);
//            return;
        }
        System.out.println(blockNum + "\t" + binSize + "\t" + length % blockNum);
       /* if (length % blockNum == 0) {// 刚好每组都满
            for (int block = 0; block < blockNum; block++) {
                System.out.println((block * binSize + 1) + "  " + (block * binSize + binSize));
            }
        } else { // 最后一组不满
            for (int block = 0; block < blockNum; block++) {
                System.out.println((block * binSize + 1) + "  " + (block * binSize + binSize));
            }
            System.out.println(binSize * blockNum + " " + length);
        }*/
    }

    public static void de2(int length, int blockNum) {

        int binSize = length / blockNum;
        if (binSize < length % blockNum) {
            de2(length, blockNum - 1);
            return;
        }
    }

    public static void main(String[] args) {
        int length = 254;
        int block = 53;
        de(length, block);
        System.exit(9);
    }
}
