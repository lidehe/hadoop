package com.netstar.mapreduce.MultiMap;

import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.fs.Path;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Job;
import org.apache.hadoop.mapreduce.lib.input.FileInputFormat;
import org.apache.hadoop.mapreduce.lib.jobcontrol.ControlledJob;
import org.apache.hadoop.mapreduce.lib.jobcontrol.JobControl;
import org.apache.hadoop.mapreduce.lib.output.FileOutputFormat;

import java.io.IOException;

public class ByJobcontrol {
    public static void main(String[] args) throws IOException {
        Configuration configuration = new Configuration();

        // job1，都只简单设置一下
        Job job1 = Job.getInstance(configuration, "job1");
        job1.setJarByClass(ByJobcontrol.class);
        job1.setMapOutputKeyClass(Text.class);
        FileInputFormat.setInputPaths(job1,new Path("")); // 设置本job的输入,如果多个路径文件格式一样，处理方式也一样，可都加进来
        FileOutputFormat.setOutputPath(job1, new Path(""));
        ControlledJob cjob1 = new ControlledJob(configuration);
        cjob1.setJob(job1);

        // job2，都只简单设置一下
        Job job2 = Job.getInstance(configuration, "job2");
        job2.setJarByClass(ByJobcontrol.class);
        job2.setMapOutputKeyClass(Text.class);
        FileInputFormat.setInputPaths(job2,new Path("")); // 设置本job的输入
        FileOutputFormat.setOutputPath(job2, new Path(""));
        ControlledJob cjob2 = new ControlledJob(configuration);
        cjob2.setJob(job2);

        // 设置多个job之间的执行顺序
        cjob2.addDependingJob(cjob1);

        // 封装成组
        String jobGroup = "test multi map";
        JobControl jobControl = new JobControl(jobGroup);
        jobControl.addJob(cjob1);
        jobControl.addJob(cjob2);

        while (true) {
            if (jobControl.allFinished()) {
                //如果作业成功完成，就打印成功作业的信息
                System.out.println(jobControl.getSuccessfulJobList());
                jobControl.stop();
                break;
            }
        }
    }
}
