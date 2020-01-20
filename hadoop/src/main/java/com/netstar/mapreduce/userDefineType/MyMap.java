package com.netstar.mapreduce.userDefineType;


import com.netstar.mapreduce.userDefineType.MyWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Mapper;

import java.io.IOException;

// 类型有要求吗？不能随便写？
// 是的，valueout类型要实现writeable接口
public class MyMap extends Mapper<Object, Text, Text, MyWritable> {

    MyWritable myWritable = new MyWritable();

    @Override
    protected void map(Object key, Text value, Context context) throws IOException, InterruptedException {
        myWritable.setBs(value.toString().concat("zzzz"));
        context.write(value, myWritable);
    }
}
