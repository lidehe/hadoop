package com.zxftech.test;

import org.apache.spark.api.java.JavaRDD;
import org.apache.spark.api.java.JavaSparkContext;
import org.apache.spark.sql.SparkSession;
import scala.Tuple2;

public class HF {
    static String delimiter = ",";
    static SparkSession spark = SparkSession.builder().master("local").appName("RRMSRESULT-HBASE").getOrCreate();
    static JavaSparkContext sc = new JavaSparkContext(spark.sparkContext());
    //    static JavaRDD<String> rdd = sc.textFile("file:///d://trade_info_20200108.txt");
    static JavaRDD<String> rdd = sc.textFile("file:///d://result.txt");

    public static void deRepeat() {
        rdd.mapToPair(record -> {
            return new Tuple2<>(record.split(delimiter)[0], record);
        }).reduceByKey((pre, next) -> {
            return next;
        }).map(result -> {
            return result._2;
        }).sortBy(mresult -> {
            return mresult.split(",")[0];
        }, true, 1).foreach(fi -> {
            System.out.println(fi.toString());
        });
    }


    public static void topK() {
        rdd.mapToPair(record -> {
            return new Tuple2<>(record.split(delimiter)[0], record);
        }).countByKey();/*.forEach((k,v) -> {
            System.out.println(k+"   "+v);
        })*/

        rdd.map(line -> {
            return new Tuple2(line.split(delimiter), 1);
        });
    }

    public static void findRepeat() {
        rdd.mapToPair(record -> {
            return new Tuple2<String, Integer>(record.split(delimiter)[0], 1);
        }).reduceByKey((pre, next) -> pre + next).filter(kv -> kv._2 > 1).sortByKey().saveAsTextFile("file:///d://trade_info_result.txt");/*.count());*/
               /* .take(100).forEach(fkv -> {
            System.out.println(fkv._1 + " " + fkv._2);
        });*/
    }


    public static void sortIP() {
        rdd.mapToPair(line -> {
            return new Tuple2<Integer, String>(Integer.parseInt(line.split("\\.")[3]), line);
        }).sortByKey().mapToPair(l -> {
            return new Tuple2<String, String>(l._2.split(" ")[0], l._2);
        }).groupByKey()/*.saveAsTextFile("hdfs://vm156:9000/zz/zzzzzz.txt");*/.foreach(g -> {
            System.out.println("\n\nips from group : "+g._1);
            g._2.forEach(System.out::println);
        });
    }

    public static void main(String[] args) {
        sortIP();

//        System.out.println(base * (Math.pow((1 + 0.01), 200)));
//        findRepeat();
//        func();
    }
}
