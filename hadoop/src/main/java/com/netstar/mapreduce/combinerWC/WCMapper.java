package com.netstar.mapreduce.combinerWC;

import org.apache.hadoop.io.IntWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapred.Counters;
import org.apache.hadoop.mapreduce.Counter;
import org.apache.hadoop.mapreduce.Mapper;

import java.io.IOException;

public class WCMapper extends Mapper<Object, Text, Text, IntWritable> {

//    Counter counter=new Counters.Counter();
    // 在这里写，不要在context.write里每次new一个
    private static final IntWritable one=new IntWritable(1);
    @Override
    protected void map(Object key, Text value, Context context) throws IOException, InterruptedException {
        String[] words = value.toString().split(" ");
        for (int i = 0; i < words.length; i++) {
//            counter.increment(9);
            context.write(new Text(words[i]), one);
        }
    }
}
