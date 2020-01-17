package com.zxftech.streaming;

import org.apache.spark.sql.SparkSession;

import java.util.HashMap;
import java.util.Map;

public class StructuredDemo {
    public static void main(String[] args) {
        Map map=new HashMap<String,Integer>();
        SparkSession ss=SparkSession.builder().master("local").appName("structured stream").getOrCreate();
        ss.streams();
    }
}
