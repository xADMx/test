package com.ifmo.lesson10;

import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

public class El {

    private static class MyHash {

        private final String name;
        private Integer count;

        public MyHash(String name, Integer count) {
            this.name = name;
            this.count = count;
        }

        public MyHash(String name) {
            this.name = name;
            this.count = 1;
        }

        public void increment(){
            this.count++;
        }

        public String getName() {
            return name;
        }

        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (o == null || getClass() != o.getClass()) return false;
            MyHash myHash = (MyHash) o;
            return Objects.equals(name, myHash.name);
        }

        @Override
        public int hashCode() {
            return Objects.hash(count*-1);
        }
    }

    public static void main(String[] args) {

        MyHash myHash = new MyHash("word2");
        MyHash myHash2 = new MyHash("word3");
        MyHash myHash3 = new MyHash("word2");

        Map<MyHash, Integer> map = new HashMap<>();

        map.put(myHash2, null);
        map.put(myHash3, null);

        map.remove(myHash3);
        myHash3.increment();
        map.put(myHash3, null);

        for(Map.Entry<MyHash, Integer> t : map.entrySet()){
            System.out.println(t.getKey().getName());
        }
    }
}
