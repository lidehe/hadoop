package com.netstar.mapreduce.dataType;

import org.apache.hadoop.io.Writable;

import java.io.DataInput;
import java.io.DataOutput;
import java.io.IOException;

public class MyWritable implements Writable {
    private String bs;

    public String getBs() {
        return bs;
    }

    public void setBs(String bs) {
        this.bs = bs;
    }

    @Override
    public void write(DataOutput out) throws IOException {
        out.writeBytes(bs);
    }

    // 这一步是必须的，否则传来的数据用不了
    // 搞不懂，既然有了set()，为什么还要这个
    @Override
    public void readFields(DataInput in) throws IOException {
        this.bs = in.readLine();
    }

    // 最终输出的数据，在这里作格式化
    @Override
    public String toString() {
        return "MyWritable{" + "bs=" + bs + '}';
    }
}
