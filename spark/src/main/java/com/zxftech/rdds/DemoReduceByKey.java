package com.zxftech.rdds;

import org.apache.spark.SparkConf;
import org.apache.spark.api.java.JavaRDD;
import org.apache.spark.api.java.JavaSparkContext;
import scala.Tuple2;

import java.util.Arrays;
import java.util.List;

public class DemoReduceByKey {
    public static void main(String[] args) {
        List list = Arrays.asList("a", "b", "a", "c");
        String appName = "java rdd";
        String master = "local";
        SparkConf conf = new SparkConf().setAppName(appName).setMaster(master);
        JavaSparkContext sc = new JavaSparkContext(conf);
        JavaRDD<String> lines = sc.parallelize(list);
        lines.mapToPair(l -> {
            return new Tuple2<String, Integer>(l, 1);
        }).reduceByKey((a, b) -> a + b).foreach(r -> {
            System.out.println(r._1 + " " + r._2);
        });
    }
}
