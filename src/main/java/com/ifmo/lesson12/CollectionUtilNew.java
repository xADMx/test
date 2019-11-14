package com.ifmo.lesson12;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class CollectionUtilNew {

    public static class MyIterable<T, R> implements Iterable<R>{
        private Iterable<T>[] temp;
        Predicate<T> p;
        Transofrmer<T, R> t;

        public Predicate<T> getP() {
            return p;
        }

        public void setP(Predicate<T> p) {
            this.p = p;
        }

        public Transofrmer<T, R> getT() {
            return t;
        }

        public void setT(Transofrmer<T, R> t) {
            this.t = t;
        }

        public void setTemp(Iterable<T>[] temp) {
            this.temp = temp;
        }

        @Override
        public Iterator<R> iterator() {
            return new Iterator<R>() {
                private Iterator<T> tempIterator = null;
                int index = 0;
                private boolean hasNextToNext = false;
                private boolean lastHasNext = false;

                @Override
                public boolean hasNext() {
                    if(hasNextToNext) {
                        return lastHasNext;
                    }
                    hasNextToNext = true;
                    if(tempIterator == null)
                        tempIterator = temp[index++].iterator();

                    if(p == null){
                        lastHasNext = hasNextEl(tempIterator);
                    } else {
                        Iterator<T> localTempIterator = tempIterator;
                        while (hasNextEl(localTempIterator)){
                            if(p.filter(localTempIterator.next())){
                                lastHasNext = true;
                                return lastHasNext;
                            }
                        }
                        lastHasNext = false;
                    }
                    return lastHasNext;
                }

                private boolean hasNextEl(Iterator<T> tempIterator){
                    if(tempIterator.hasNext()){
                        return true;
                    } else {
                        if(temp.length == index){
                            return false;
                        } else {
                            tempIterator = temp[index++].iterator();
                            return tempIterator.hasNext();
                        }
                    }
                }

                @Override
                public R next() {
                    hasNextToNext = false;
                    if(p == null){
                        return (t == null) ? (R) tempIterator.next() : t.transform(tempIterator.next());
                    } else {
                        while (true) {
                            T res = tempIterator.next();
                            if (p.filter(res)) {
                                return (t == null) ? (R) res : t.transform(res);
                            }
                        }
                    }
                }

            };
        }
    };

    public static <T, R> Iterable<R> view(
            Predicate<T> p,
            Transofrmer<T, R> t,
            Iterable<T>... iterables){

        MyIterable<T, R> ny = new MyIterable<>();
        ny.setTemp(iterables);
        ny.setT(t);
        ny.setP(p);

        return ny;
    }

    public static void main(String[] args) {
        List<String> list1 = new ArrayList<>();
        List<String> list2 = new ArrayList<>();
        List<String> list3 = new ArrayList<>();

        list1.add("1");
        list1.add("2");

        list2.add("3");
        list2.add("4");

        list3.add("5");
        list3.add("6");


        Iterable<Integer> view = view(p -> !p.equals("1"), t -> Integer.valueOf(t), list1, list2, list3);

        for(Integer item : view){
            System.out.println(item);
        }

    }

}
