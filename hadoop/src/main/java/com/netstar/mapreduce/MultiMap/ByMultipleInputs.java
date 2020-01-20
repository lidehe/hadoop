package com.netstar.mapreduce.MultiMap;

import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.fs.Path;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapred.TextOutputFormat;
import org.apache.hadoop.mapreduce.Job;
import org.apache.hadoop.mapreduce.Mapper;
import org.apache.hadoop.mapreduce.lib.input.MultipleInputs;
import org.apache.hadoop.mapreduce.lib.input.TextInputFormat;
import org.apache.hadoop.mapreduce.lib.output.FileOutputFormat;

import java.io.IOException;

public class ByMultipleInputs {
    // 按逗号分割
    static class SpliteByComma extends Mapper{
        @Override
        protected void map(Object key, Object value, Context context) throws IOException, InterruptedException {
            value.toString().split(",");
        }
    }

    static class SpliteBySpace extends Mapper{
        @Override
        protected void map(Object key, Object value, Context context) throws IOException, InterruptedException {
            value.toString().split(" ");
        }
    }

    public static void main(String[] args) throws IOException, ClassNotFoundException, InterruptedException {
        Configuration configuration=new Configuration();
        Job job=Job.getInstance(configuration,"split file");
        job.setJarByClass(ByMultipleInputs.class);
        job.setOutputKeyClass(Text.class);
        job.setOutputValueClass(Text.class);

        // 不同的路径使用不同的mapper处理
        MultipleInputs.addInputPath(job,new Path("path1"), TextInputFormat.class,SpliteByComma.class);
        MultipleInputs.addInputPath(job,new Path("path2"), TextInputFormat.class,SpliteBySpace.class);

        // 放到相同的路径下，所以只能处理输出格式一样的多个转换
        FileOutputFormat.setOutputPath(job, new Path("output"));

        job.waitForCompletion(true);
    }
}
