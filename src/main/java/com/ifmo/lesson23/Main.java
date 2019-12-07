package com.ifmo.lesson23;


import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.*;


public class Main {


    public static Map<String, Integer> res = new HashMap<>();

    public static void main(String[] args) throws IOException, InterruptedException {
        File text = new File("C:\\Users\\ADMnet\\IdeaProjects\\test\\src\\resources\\wap.txt");

        int countProcess = Runtime.getRuntime().availableProcessors();

        ExecutorService es = Executors.newFixedThreadPool(countProcess);

        Map<String, Integer> allCount = new HashMap<>();
        // Вычитываем все строки из файла


        List<List<String>> param = new ArrayList<>();
        List<Future<Map<String, Integer>>> futures = new ArrayList<>();

        for (int i = 0; i < countProcess; i++) {
            param.add(new ArrayList<>());
        }
        int count = 0;
        for (String line : Files.readAllLines(text.toPath())) {
            // Для каждой строки
            param.get(count++).add(line);

            if(count == 3){
                count = 0;
            }
        }

        for(List<String> str : param){
            futures.add(es.submit(() -> countWords(str)));
        }
        try {
            for (Future<Map<String, Integer>> res : futures){
                Map<String, Integer> tempRes = res.get();
                if(allCount == null){
                    allCount = tempRes;
                } else {
                    for (Map.Entry<String, Integer> map : tempRes.entrySet()) {
                        if (allCount.containsKey(map.getKey())) {
                            allCount.merge(map.getKey(), map.getValue(), (i1, i2) -> i1 + i2);
                        } else {
                            allCount.put(map.getKey(), map.getValue());
                        }
                    }
                }
            }
        } catch (ExecutionException e) {
            e.printStackTrace();
        }


        for (int i = 0; i < 10; i++) {
            Map.Entry<String, Integer> max = null;
            for (Map.Entry<String, Integer> temp : allCount.entrySet()){
                if(max == null) {
                    max = temp;
                } else if (max.getValue() < temp.getValue()) {
                    max = temp;
                }
            }

            allCount.remove(max.getKey());
            res.put(max.getKey(), max.getValue());

        }

        System.out.println(res);

        es.shutdown();

    }

    private static Map<String, Integer> countWords(List<String> listStr) {

            Map<String, Integer> tempMap = new HashMap<>();
            for(String line  : listStr) {
                String[] wordSplit =
                        line.toLowerCase() // Переводим в нижний регистр
                                .replaceAll("\\p{Punct}", " ") // Заменяем все знаки на пробел
                                .trim() // Убираем пробелы в начале и конце строки.
                                .split("\\s"); // Разбиваем строки на слова

                for (String s : wordSplit)
                    if (s.length() > 0)
                        if (tempMap.containsKey(s)) {
                            tempMap.merge(s, 1, (i1, i2) -> i1 + i2);
                        } else {
                            tempMap.put(s, 1);
                        }
            }
            return tempMap;
        }
}
