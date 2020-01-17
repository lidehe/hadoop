package com.zxftech;

import org.apache.spark.sql.Dataset;
import org.apache.spark.sql.Row;
import org.apache.spark.sql.SparkSession;

/**
 * 这种方式需要打包提交到集群里执行
 */
public class YarnConnect {
    public static void main(String[] args) {
        if (args.length!=1)
            return;
        System.out.println("===========================test run/deploy mode==================================");
        SparkSession sparkSession = SparkSession.builder().
                appName(args[0]).
//                master("yarn").
//                config("hive.metastore.uris", "thrift://10.204.145.162:9083").
                enableHiveSupport(). // HiveContext hiveContext;
                getOrCreate();


        Dataset<Row> ds = sparkSession.sql("show databases");
        ds.show();
//        ds.toDF().insertinto
//        ds.select(new Column("cstno"));

    }
}
