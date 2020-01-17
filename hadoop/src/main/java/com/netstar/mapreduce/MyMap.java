package com.netstar.mapreduce;


import org.apache.hadoop.io.IntWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Mapper;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

// 类型有要求吗？不能随便写？
// 是的，valueout类型要实现writeable接口
public class MyMap extends Mapper<Object, Text, Text, Text> {
    Text k = new Text();
    Text v = new Text();
    private final static IntWritable one = new IntWritable(1);
    @Override
    protected void map(Object key, Text value, Context context) throws IOException, InterruptedException {
        context.write(new Text(value), new Text(value.toString() + "zzzzzzzz"));
    }
}
