package com.ifmo.lesson22;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingDeque;

public class Main {
    private static final String poison = new String();


    public static Map<String, Integer> res = new HashMap<>();
    public static final BlockingQueue<String> queLine = new LinkedBlockingDeque<>();
    public static final BlockingQueue<Map<String, Integer>> queRess = new LinkedBlockingDeque<>();

    public static void main(String[] args) throws IOException, InterruptedException {
        File text = new File("C:\\Users\\ADMnet\\IdeaProjects\\test\\src\\resources\\wap.txt");

        int countProcess = Runtime.getRuntime().availableProcessors();

        for (int i = 0; i < countProcess; i++) {
            Thread thread = new Thread(new CountWords());
            thread.start();
        }

        new Thread(new ReadLines(text, countProcess)).start();

    }

    private static class ReadLines implements Runnable{

        private List<String> lines = null;
        private int countProcess;

        public ReadLines(File text, int countProcess) throws IOException {
            this.countProcess = countProcess;
            lines = Files.readAllLines(text.toPath());

        }

        @Override
        public void run() {

            Map<String, Integer> allCount = new HashMap<>();
            // Вычитываем все строки из файла

            for (String line : lines) {
                // Для каждой строки
                queLine.add(line);
            }

            queLine.add("EnDStRiNgMy");

            try {
                for (int i = 0; i < countProcess; i++) {
                    Map<String, Integer> tempRes = queRess.take();
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
            } catch (InterruptedException e) {
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
        }
    }

    private static class CountWords extends Thread{

        @Override
        public void run() {
            Map<String, Integer> tempMap = new HashMap<>();
            boolean end = false;

            while (!isInterrupted() && !end) {
                try {
                    String line = queLine.take();

//                    line == poison
                    if (line.equals("EnDStRiNgMy")) {
                        queLine.add("EnDStRiNgMy");
//                        end = true;
                        interrupt();
                    } else {
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
                }catch(InterruptedException e){
                        e.printStackTrace();
                        interrupt();
                    }
                }

            queRess.add(tempMap);
        }
    }
}
