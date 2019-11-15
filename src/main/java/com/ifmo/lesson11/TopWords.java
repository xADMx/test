package com.ifmo.lesson11;


import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.util.*;

public class TopWords {
    public static void main(String[] args) throws IOException {
        // Создаем файл, указывая путь к текстовому файлу на диске
        File text = new File("C:\\Users\\ADMnet\\IdeaProjects\\test\\src\\resources\\wap.txt");

        // Вычитываем все строки из файла
        List<String> lines = Files.readAllLines(text.toPath());

        // Создаем пустую коллекцию для слов.
        List<String> words = new ArrayList<>();

        for (String line : lines) {
            // Для каждой строки
            String[] wordSplit =
                    line.toLowerCase() // Переводим в нижний регистр
                            .replaceAll("\\p{Punct}", " ") // Заменяем все знаки на пробел
                            .trim() // Убираем пробелы в начале и конце строки.
                            .split("\\s"); // Разбиваем строки на слова

            for (String s : wordSplit) {
                // Выбираем только непустые слова.
                if (s.length() > 0)
                    words.add(s.trim());
            }
        }

        System.out.println(top10Words(words));
        System.out.println(top10Phrases(words));
        System.out.println(charactersFrequency(words));
    }

    private static class MyHash{
        private final String word;
        private Integer count;

        public MyHash(String word, Integer count) {
            this.word = word;
            this.count = count;
        }

        public MyHash(String word) {
            this.word = word;
            this.count = 1;
        }

        public String getWord() {
            return word;
        }

        public void deincrement(){
            this.count--;
        }

        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (o == null || getClass() != o.getClass()) return false;
            MyHash myHash = (MyHash) o;
            return Objects.equals(word, myHash.word);
        }

        @Override
        public int hashCode() {
            return count;
        }
    }

    public static Map<String, Integer> top10Words(List<String> words) {
        // todo implement
        Map<String, Integer> tempMap = new HashMap<>();
        Map<String, Integer> res = new HashMap<>();

        for(String word : words){
            if(tempMap.containsKey(word)) {
                tempMap.replace(word, tempMap.get(word) + 1);
            } else {
                tempMap.put(word, 1);
            }
        }

        for (int i = 0; i < 10; i++) {
            Map.Entry<String, Integer> max = null;
            for (Map.Entry<String, Integer> temp : tempMap.entrySet()){
                if(max == null) {
                    max = temp;
                } else if (max.getValue() < temp.getValue()) {
                    max = temp;
                }
            }

            tempMap.remove(max.getKey());
            res.put(max.getKey(), max.getValue());

        }

        return res;
    }

    public static Map<String, Integer> top10Phrases(List<String> words) {
        // todo implement
        return Map.of();
    }

    public static Map<Character, Integer> charactersFrequency(List<String> words) {
        // todo implement
        return Map.of();
    }
}
