package com.ifmo.lesson21;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Main {

    public static Map<String, Integer> words = new HashMap<>();
    public static Object monitor = new Object();

    public static void main(String[] args) throws IOException, InterruptedException {
        File text = new File("C:\\Users\\ADMnet\\IdeaProjects\\test\\src\\resources\\wap.txt");
        Map<String, Integer> res = new HashMap<>();
        List<List<String>> param = new ArrayList<>();
        List<Thread> threads = new ArrayList<>();

        // Вычитываем все строки из файла
        List<String> lines = Files.readAllLines(text.toPath());
        int countProcess = Runtime.getRuntime().availableProcessors();

        for (int i = 0; i < countProcess; i++) {
            param.add(new ArrayList<>());
        }
        int count = 0;
        for (String line : lines) {
            // Для каждой строки
            param.get(count).add(line);

            if(count == 3){
                count = 0;
            }
        }

        for (int i = 0; i < countProcess; i++) {
            Thread thread = new Thread(new CountWords(param.get(i)));
            thread.start();
            threads.add(thread);
        }

        for (int i = 0; i <threads.size() ; i++) {
            threads.get(i).join();
        }

        for (int i = 0; i < 10; i++) {
            Map.Entry<String, Integer> max = null;
            for (Map.Entry<String, Integer> temp : words.entrySet()){
                if(max == null) {
                    max = temp;
                } else if (max.getValue() < temp.getValue()) {
                    max = temp;
                }
            }

            words.remove(max.getKey());
            res.put(max.getKey(), max.getValue());

        }

        System.out.println(res);
    }

    private static class CountWords implements Runnable{

        List<String> lines;

        public CountWords(List<String> lines) {
            this.lines = lines;
        }

        @Override
        public void run() {
            Map<String, Integer> tempMap = new HashMap<>();

            for (String line : lines) {
                // Выбираем только непустые слова.

                String[] wordSplit =
                        line.toLowerCase() // Переводим в нижний регистр
                                .replaceAll("\\p{Punct}", " ") // Заменяем все знаки на пробел
                                .trim() // Убираем пробелы в начале и конце строки.
                                .split("\\s"); // Разбиваем строки на слова

                for(String s : wordSplit)
                if (s.length() > 0)
                    if(tempMap.containsKey(s)) {
                        tempMap.replace(s, tempMap.get(s) + 1);
                    } else {
                        tempMap.put(s, 1);
                    }
            }

            synchronized (monitor){
                    for(Map.Entry<String, Integer> map : tempMap.entrySet()){
                        if(words.containsKey(map.getKey())) {
                            words.replace(map.getKey(), words.get(map.getKey()) + map.getValue());
                        } else {
                            words.put(map.getKey(), map.getValue());
                        }
                    }
            }
        }
    }
}
