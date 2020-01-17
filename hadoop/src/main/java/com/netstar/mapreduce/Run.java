package com.netstar.mapreduce;

import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.fs.FileSystem;
import org.apache.hadoop.fs.Path;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Job;
import org.apache.hadoop.mapreduce.lib.input.FileInputFormat;
import org.apache.hadoop.mapreduce.lib.output.FileOutputFormat;

import java.io.IOException;

public class Run {

    public static void main(String[] args) throws IOException, ClassNotFoundException, InterruptedException {
        Configuration configuration = new Configuration();
        // 如果是提交到集群，这句就不用写了
        configuration.set("fs.default.name", "hdfs://vm156:9000");
        Path outPath = new Path("/xz.txt");
        Job job = Job.getInstance(configuration, "test");
        job.setJarByClass(Run.class);
        job.setMapperClass(MyMap.class);
        // 这里要和map里对应起来，要么都用默认的，要么都写明，否则会不一致
        job.setOutputKeyClass(Text.class);
        job.setOutputValueClass(Text.class);
        FileInputFormat.addInputPath(job, new Path("/xx.txt"));
        FileOutputFormat.setOutputPath(job, outPath);

        job.waitForCompletion(true);
    }
}
