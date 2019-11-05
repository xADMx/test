package com.ifmo.lesson10;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class CollectionUtil {

    public static class MyIterable<T> implements Iterable<T>{
        private Iterable<T>[] temp;

        public void setTemp(Iterable<T>[] temp) {
            this.temp = temp;
        }

        @Override
        public Iterator<T> iterator() {
            return new Iterator<T>() {
                private Iterator<T> tempIterator = null;
                int index = 0;

                @Override
                public boolean hasNext() {
                    if(tempIterator == null)
                        tempIterator = temp[index++].iterator();

                    if(tempIterator.hasNext()){
                        return  true;
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
                public T next() {
                    return tempIterator.next();
                }
            };
        }
    };


    public static <T> Iterable view(Iterable<T>... iterables){

        MyIterable<T> ny = new MyIterable<>();
        ny.setTemp(iterables);

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

        Iterable<String> view = view(list1, list2, list3);

        for(String item : view){
            System.out.println(item);
        }

    }

}
