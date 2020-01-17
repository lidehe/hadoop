package com.zxftech.test;

import java.io.*;
import java.util.Random;

public class OD {
    public static void main(String[] args) throws IOException {
        Writer writer = new FileWriter(new File("d://car.txt"));
        BufferedWriter bufferedWriter = new BufferedWriter(writer);

        for (int i = 0; i < 200; i++) {
            Random random = new Random();
            int value = random.nextInt(200);
//            long id=System.currentTimeMillis();
            long id = System.nanoTime();
            String key = String.valueOf(id);
            String o = "remarks" + value;
            String t = "descriptions" + value;
            String line = key + "," + value + "," + o + "," + t;
            bufferedWriter.write(line);
            bufferedWriter.newLine();
        }
        bufferedWriter.flush();
    }
}
