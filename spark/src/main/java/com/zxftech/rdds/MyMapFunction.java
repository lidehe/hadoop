package com.zxftech.rdds;

import org.apache.spark.api.java.function.FlatMapFunction;
import org.apache.spark.api.java.function.MapFunction;
import org.apache.spark.api.java.function.PairFunction;
import scala.Tuple2;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class MyMapFunction {

    static class MyMap implements MapFunction {
        @Override
        public Object call(Object value) throws Exception {
            return (Integer) value * 100;
        }
    }

    static class myFlatMap implements FlatMapFunction {
        @Override
        public Iterator call(Object o) throws Exception {
            List<Integer> list = new ArrayList<>();
            return null;
        }
    }

    static class MyPair implements PairFunction {
        @Override
        public Tuple2 call(Object o) throws Exception {
            Integer k = (Integer) o;
            String v = k + "`value";
            Tuple2<Integer, String> tuple2 = new Tuple2<>(k, v);
            return tuple2;
        }
    }

    static class MyFlatMapFuncton implements FlatMapFunction<Iterator<Integer>, Iterator> {
        @Override
        public Iterator call(Iterator<Integer> integerIterator) {
            List<Integer> list = new ArrayList<>();
            while (integerIterator.hasNext()) {
                int it = integerIterator.next();
                list.add(it);
            }
            return list.iterator();
        }
    }
}
