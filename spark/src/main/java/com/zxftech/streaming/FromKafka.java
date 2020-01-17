package com.zxftech.streaming;

import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.apache.kafka.common.serialization.StringDeserializer;
import org.apache.spark.SparkConf;
import org.apache.spark.streaming.Durations;
import org.apache.spark.streaming.api.java.JavaInputDStream;
import org.apache.spark.streaming.api.java.JavaStreamingContext;
import org.apache.spark.streaming.kafka010.ConsumerStrategies;
import org.apache.spark.streaming.kafka010.KafkaUtils;
import org.apache.spark.streaming.kafka010.LocationStrategies;


import java.io.Serializable;
import java.util.*;

public class FromKafka implements Serializable {
    static Map<String, Object> kafkaParams = new HashMap<>();
    static Collection<String> topics = Arrays.asList("kettle_test");// 可以订阅多个哟
    static {
        kafkaParams.put("bootstrap.servers", "10.204.145.155:9092,10.204.145.156:9092,10.204.145.157:9092");
        kafkaParams.put("key.deserializer", StringDeserializer.class);
        kafkaParams.put("value.deserializer", StringDeserializer.class);
        kafkaParams.put("group.id", "use_a_separate_group_id_for_each_stream");
        kafkaParams.put("auto.offset.reset", "latest");
        kafkaParams.put("enable.auto.commit", false);
    }

    public static void main(String[] args) throws InterruptedException {
        SparkConf conf = new SparkConf().setMaster("local[4]").setAppName("streaming"); // "spark://vm156:7077"

        JavaStreamingContext jssc = new JavaStreamingContext(conf, Durations.milliseconds(200));
        // 可以设置检查点，如果应用中用到了 有状态的转换，或者应用是失败恢复
//        jssc.checkpoint("");

        JavaInputDStream<ConsumerRecord<String, String>> stream =
                KafkaUtils.createDirectStream(jssc, LocationStrategies.PreferConsistent(),
                        ConsumerStrategies.<String, String>Subscribe(topics, kafkaParams)
                );

        // common
/*        stream.mapToPair(record -> {

            return new Tuple2<>(record.key(), record.value());
        }).map(x -> x._1 + x._2).print();

        stream.map(r -> (r.offset() + " : " + r.key() + " : " + r.value())).foreachRDD(o -> o.collect().forEach(a -> {
            System.out.println(a);
        }));

        stream.map(r -> r.key().split("y")[1]).count().foreachRDD(x -> {
            x.foreach(m -> System.out.println(m));
        });*/

        // reducebykey
 /*       stream.mapToPair(r -> {
            Tuple2<String, Integer> tuple2;
            if (Integer.parseInt(r.key().split("y")[1]) % 2 == 0) {
                tuple2 = new Tuple2<>("even", 1);
            } else {
                tuple2 = new Tuple2<>("odd", 1);
            }
            return tuple2;
        }).reduceByKey((a, b) -> a + b).foreachRDD(q -> {
            q.foreach(x -> {
                System.out.println(x._1 + " " + x._2);
            });
        });*/

        // reducebykeyandwindow
/*        stream.mapToPair(r -> {
            Tuple2<String, Integer> tuple2;
            if (Integer.parseInt(r.key().split("y")[1]) % 2 == 0) {
                tuple2 = new Tuple2<>("even", 1);
            } else {
                tuple2 = new Tuple2<>("odd", 1);
            }
            return tuple2;
        }).reduceByKeyAndWindow((x,y)-> x+y, Durations.seconds(4),Durations.seconds(4)).foreachRDD(x->{
            x.foreach(b->{
                System.out.println(b._1+" "+b._2);
            });
        });*/

        // print data in window
        stream.map(r->r.key()+r.value()).window(Durations.seconds(4),Durations.seconds(4)).print();


        // 还可以用sql
        // SparkSession sparkSession=SparkSession.builder().config(jssc.sparkContext().getConf()).getOrCreate();
        // sparkSession

        jssc.start();
        jssc.awaitTermination();
    }
}

