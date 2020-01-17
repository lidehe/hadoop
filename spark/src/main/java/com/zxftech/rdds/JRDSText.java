package com.zxftech.rdds;

import com.zxftech.entiy.Order;
import org.apache.spark.SparkConf;
import org.apache.spark.api.java.JavaRDD;
import org.apache.spark.api.java.JavaSparkContext;
import org.apache.spark.api.java.function.MapFunction;
import org.apache.spark.api.java.function.PairFunction;
import scala.Tuple2;

import java.io.Serializable;

public class JRDSText implements Serializable {

    public static void main(String[] args) throws InterruptedException {
        String appName = "java rdd";
//        String master = "local";
        SparkConf conf = new SparkConf().setAppName(appName).setMaster("local");
//        SparkConf conf = new SparkConf().setAppName(appName);//.setMaster("local");
        JavaSparkContext sc = new JavaSparkContext(conf);

        // 路径可以指明完整hdfsl路径，也可以不指定，因为hdfs-site.xml放在了resources目录下
        // hdfs://zx162:9000/zz.txt.txt
        // /zz.txt.txt
        JavaRDD<String> lines = sc.textFile("hdfs://vm156:9000/zz.txt");

        lines.mapToPair(record->{
            return new Tuple2<>(record.split(",")[0],record);
        }).reduceByKey((pre,next)->{
            return next;
        }).map(result -> {
            return result._2;
        }).foreach(r->{
            System.out.println(r.toString());
        });

     /*   lines.filter(x -> !x.split(",")[0].equals("109"))
                .mapToPair(r -> new MyMapToPair().call(r))
                .reduceByKey((v1, v2) -> v1 + v2)
                .map(m -> m._2)
                .take(100000)
                .forEach(a -> {
                    System.out.println(a.toString());
                });*/
           /*     .forEach(x -> {
            try {
                Thread.sleep(30000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println(x.toString());
        });*/
//        lines.map(line -> new MyMap().call(line)).groupBy(u -> u.getAddress()).foreach(u -> System.out.println(u));
    }

    static class MyMapToPair implements PairFunction<String, String, Integer> {
        @Override
        public Tuple2<String, Integer> call(String r) throws Exception {
            return new Tuple2(r.split(",")[0], Integer.parseInt(r.split(",")[1]));
        }
    }

    static class MyMap implements MapFunction<String, Order> {
        @Override
        public Order call(String s) throws Exception {
            String[] ss = s.split(";");
            Order order = new Order();
            order.setPhone(ss[1]);
            order.setSocial_no(ss[3]);
            order.setName(ss[0]);
            order.setAddress(ss[2]);
            return order;
        }
    }
}
