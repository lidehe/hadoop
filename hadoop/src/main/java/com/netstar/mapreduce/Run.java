package com.netstar.mapreduce;

import com.netstar.mapreduce.dataType.MyWritable;
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
//        configuration.set("fs.default.name", "hdfs://vm156:9000");

        FileSystem fs = FileSystem.newInstance(configuration);
        Path outPath = new Path("/xz.txt");
        boolean exists = fs.exists(outPath);
        if (exists) {
            fs.delete(outPath, true);
        }
        Job job = Job.getInstance(configuration, "test");
        job.setJarByClass(Run.class);
        job.setMapperClass(MyMap.class);
        job.setOutputKeyClass(Text.class);
        job.setOutputValueClass(MyWritable.class);
        FileInputFormat.addInputPath(job, new Path("/xx.txt"));
        FileOutputFormat.setOutputPath(job, outPath);

        job.waitForCompletion(true);
    }
}
