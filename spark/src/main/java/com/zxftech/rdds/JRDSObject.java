package com.zxftech.rdds;

import com.zxftech.entiy.Record;
import org.apache.spark.SparkConf;
import org.apache.spark.api.java.JavaRDD;
import org.apache.spark.api.java.JavaSparkContext;

import java.util.ArrayList;
import java.util.List;

public class JRDSObject {

    public static void main(String[] args) {
        String appName = "java rdd";
        String master = "local";
        SparkConf conf = new SparkConf().setAppName(appName).setMaster(master);
        JavaSparkContext sc = new JavaSparkContext(conf);

        List<Record> list = new ArrayList<>();
        list.add(new Record("lian", "taibei", "male"));
        list.add(new Record("liudehua", "hongkong", "male"));
        list.add(new Record("guanzhilin", "hongkong", "female"));
        list.add(new Record("chenhuilin", "hongkong", "female"));
        list.add(new Record("bingqibu", "japan", "female"));
        list.add(new Record("hopkens", "english", "male"));
        list.add(new Record("robert", "america", "male"));

        JavaRDD<Record> records = sc.parallelize(list);
        records.groupBy(Record::getAddress).groupByKey().foreach(r -> {
            System.out.println(r);
            System.out.println();
        });

        JavaRDD<String> lines=sc.textFile("d://zz.txt.txt");

    }
}
