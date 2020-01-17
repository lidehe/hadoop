package com.zxftech.test;

import java.io.*;
import java.util.*;

public class OA {

    public static String date = "";
    public static String fileName = "";

    /**
     * 虽然单个文件内还是有序的，合并才能无序
     * 重复率，随着数据量增大而急速增加，在2千万附近重复率达到50%
     *
     * @throws IOException
     */
    public static void almostSorted() throws IOException {
        Writer writer = new FileWriter(new File("d://car.txt"));
        BufferedWriter bufferedWriter = new BufferedWriter(writer);
        for (int i = 0; i < 1000000; i++) {
            // 随机数据的获取，会导致下面System.nanoTime()取到相同的值，且循环数量越大，重复比重越大
            // 正好可以用来测试去重功能
            // 至于排序，把两个文件合并就乱序了
            Random random = new Random();
            int value = random.nextInt(200);
//            long id=System.currentTimeMillis();
            long id = System.nanoTime();
            String key = String.valueOf(id);
            String o = "remarks" + value;
            String t = "descriptions" + value;

            String line = key + "," + value + "," + o + "," + t;

            bufferedWriter.write(line);
            // 调用这个方法最安全，因不同机器行分隔符可能不一样
            bufferedWriter.newLine();
        }
        bufferedWriter.flush();
    }


    /**
     * 完全随机，且无序，重复率 百万：0.2%  千万:2%
     *
     * @throws IOException
     */
    public static void completlyUnsorted() throws IOException {
//        DecimalFormat dF = new DecimalFormat("0.00000000");
//        System.out.println(dF.format((float) 237936 / 10000000));
//        System.exit(9);
        Writer writer = new FileWriter(new File("d://car.txt"));
        BufferedWriter bufferedWriter = new BufferedWriter(writer);
        for (int i = 0; i < 10000000; i++) {
            Random random = new Random();
            int left = random.nextInt(20000);
            int right = random.nextInt(20000);
            int plus = random.nextInt(20000);
            long id = left * right + plus;
            String key = String.valueOf(id);
            String o = "remarks" + id;
            String t = "descriptions" + id;

            String line = key + "," + id + "," + o + "," + t;

            bufferedWriter.write(line);
            // 调用这个方法最安全，因不同机器行分隔符可能不一样
            bufferedWriter.newLine();
        }
        bufferedWriter.flush();
    }

    /**
     * 随机数生成器
     *
     * @param n
     * @return
     */
    public static long randomGenerator(int n) {
        if (n < 1) {
            throw new IllegalArgumentException("随机数位数必须大于0");
        }
        return (long) (Math.random() * 9 * Math.pow(10, n - 1)) + (long) Math.pow(10, n - 1);
    }

    /**
     * 5位随机，1千万条记录，按照比例每条数据数据重复率在100/条
     * 通过预埋数据的方式进行测试
     * 取10条数据，每条追加50条 0-6点的记录即可，
     * 执行测试，看看是否有这10条
     *
     * @throws IOException
     */
    public static void recordGenerator() throws IOException {
        Writer writer = new FileWriter(new File(fileName));
        BufferedWriter bufferedWriter = new BufferedWriter(writer);
        String[] type = {"POBS", "ABCS", "PWAP", "NBS"};

        for (int i = 0; i < 10000000; i++) {
//        for (int i = 0; i < 100; i++) {
            long merch_no = randomGenerator(5);
            String merch_name = "merchant" + merch_no;
            String term_no = "terminal" + merch_no;
            String card_no = "1000" + merch_no;
            Random random = new Random();
            int factor = random.nextInt(61);
            int hour = factor % 24;
            int minute = factor % 60;
            String time = date + hour + ":" + minute + ":00";
            String rate = 0 + ".00" + factor;
            String trn_amt = String.valueOf(merch_no / (factor + 1));
            String fee = String.valueOf(Float.parseFloat(rate) * Float.parseFloat(trn_amt));
            String trn_type = type[factor % 4];
            String line = merch_no + "," + merch_name + "," + term_no + "," + card_no + "," + time + "," + trn_amt + "," + Float.parseFloat(rate) * 100 + "%" + "," + fee + "," + trn_type;

            bufferedWriter.write(line);
            // 调用这个方法最安全，因不同机器行分隔符可能不一样
            bufferedWriter.newLine();
        }
        bufferedWriter.flush();
    }

    /**
     * 重复数据生成
     *
     * @throws IOException
     */
    public static void repeatRecordGenerator(long merch_no) throws IOException {
        Writer writer = new FileWriter(new File(fileName), true);
        BufferedWriter bufferedWriter = new BufferedWriter(writer);
        String[] type = {"POBS", "ABCS", "PWAP", "NBS"};

        for (int i = 0; i < 50; i++) {
            String merch_name = "merchant" + merch_no;
            String term_no = "terminal" + merch_no;
            String card_no = "1000" + merch_no;
            Random random = new Random();
            int factor = random.nextInt(61);
            int hour = factor % 6;
            int minute = factor % 60;
            String time = date + hour + ":" + minute + ":00";
            String rate = 0 + ".00" + factor;
            String trn_amt = String.valueOf(merch_no / (factor + 1));
            String fee = String.valueOf(Float.parseFloat(rate) * Float.parseFloat(trn_amt));
            String trn_type = type[factor % 4];
            String line = merch_no + "," + merch_name + "," + term_no + "," + card_no + "," + time + "," + trn_amt + "," + Float.parseFloat(rate) * 100 + "%" + "," + fee + "," + trn_type;

            bufferedWriter.write(line);
            // 调用这个方法最安全，因不同机器行分隔符可能不一样
            bufferedWriter.newLine();
        }
        bufferedWriter.flush();
    }


    public static void main(String[] args) throws IOException, InterruptedException {
        date = "2020-01-08 ";
        fileName="d://trade_info_20200108.txt";

//        recordGenerator();
        // 从上一步生成的数据中拿
        long[] repeats = {17752, 80698, 85469, 99141, 39483, 78175, 82817, 43152, 19738, 61178};
        for (int i = 0; i < repeats.length; i++) {
            repeatRecordGenerator(repeats[i]);
        }
    }
}
