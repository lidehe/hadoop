package com.netstar.mapreduce.combinerWC;

import com.netstar.mapreduce.userDefineType.Run;
import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.fs.FileSystem;
import org.apache.hadoop.fs.Path;
import org.apache.hadoop.io.IntWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Job;
import org.apache.hadoop.mapreduce.lib.input.FileInputFormat;
import org.apache.hadoop.mapreduce.lib.output.FileOutputFormat;

import java.io.IOException;

public class WCRun {

    public static void main(String[] args) throws IOException, ClassNotFoundException, InterruptedException {
        Configuration configuration = new Configuration();
//        configuration.set("fs.default.name", "hdfs://vm156:9000");

        FileSystem fs = FileSystem.newInstance(configuration);
        Path inPath = new Path("/article.txt");
        Path outPath = new Path("/wc.txt");
        boolean exists = fs.exists(outPath);
        if (exists) {
            fs.delete(outPath, true);
        }
        Job job = Job.getInstance(configuration, "wd mapred");
        job.setJarByClass(Run.class);
        job.setMapperClass(WCMapper.class);
        //2.1G文件
        //没有使用combine耗时： 2020-01-19 09:22:38,854   2020-01-19 09:27:49,018   =5分10秒
        //  有使用combine耗时： 2020-01-19 09:30:16,978   2020-01-19 09:31:32,375   =1分02秒
        job.setCombinerClass(WCReducer.class);
        job.setReducerClass(WCReducer.class);

        // 输出格式，当map和reduce的输出格式不一致时怎么办
        // 以下用于设置map和reduce的输出类型
        job.setOutputKeyClass(Text.class);
        job.setOutputValueClass(IntWritable.class);
        // 以下单独再设置map输出类型
        job.setMapOutputKeyClass(Text.class);
        job.setMapOutputValueClass(IntWritable.class);

        FileInputFormat.addInputPath(job, inPath);
        FileOutputFormat.setOutputPath(job, outPath);

        job.waitForCompletion(true);
    }
}
