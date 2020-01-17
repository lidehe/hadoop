package com.zxftech;

import org.apache.spark.sql.*;

/**
 * 这种方式可以直接在本地执行，可以提交到集群执行吗
 */
public class StandAlongConnect {


    public static void main(String[] args) throws InterruptedException {
        System.out.println("===========================client mode==================================");
        SparkSession spark = SparkSession.builder().
                master("spark://vm156:7077").
                appName("test20191114").
                enableHiveSupport().
                getOrCreate();
        dataSetDemo(spark);
    }

    public static void dataSetDemo(SparkSession spark) {

        // custinfo表结构为:cstnum name address gender datadate
        Dataset<Row> custinfo = spark.table("ldh.custinfo");

        // 筛选出 日期大于20191220的记录的cstnum、name两个字段，存储到临时表t_result中
        custinfo.filter(new Column("datadate").lt("20191220")).select(new Column("cstnum"), new Column("name")).registerTempTable("t_result");

        // 存储到result表中
        spark.sql("insert into table ldh.result select * from t_result");

    }

    public static void dataFrameDemo(SparkSession spark) {
        Dataset<Row> df = spark.sql("select * from ldh.user_info");
        df.printSchema();
        df.filter(new Column("id").gt(9523)).show();
    }
}
