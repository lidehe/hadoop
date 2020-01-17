package com.netstar.hdfs;

import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.fs.FSDataOutputStream;
import org.apache.hadoop.fs.FileSystem;
import org.apache.hadoop.fs.Path;

import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;

public class WriteFile {
    public static void main(String[] args) throws IOException, URISyntaxException, InterruptedException {
        Configuration configuration = new Configuration();
//        configuration.set("fs.default.name", "hdfs://vm156:9000");
//        configuration.
//        URI uri=new URI("hdfs://vm156:9000");
//        FileSystem fs = FileSystem.newInstance(uri, configuration);
//        FileSystem fs = FileSystem.newInstance(configuration);
        FileSystem fs = FileSystem.get(new URI("hdfs://vm156:9000"), configuration, "spark");
        Path path = new Path("/xz.txt");
        boolean exists = fs.exists(path);
        System.out.println(exists);
        fs.delete(path,true);
//        FSDataOutputStream outputStream = fs.create(new Path("/xx.txt"));
//        byte[] b = "ajdslkfakdjsflas".getBytes();
//        outputStream.write(b);
//        outputStream.hflush();
//        outputStream.close();
//        fs.close();
    }
}
