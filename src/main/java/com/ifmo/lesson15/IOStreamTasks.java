package com.ifmo.lesson15;

import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

/*
    Реализуйте все методы с использованием потоков ввода-вывода.
 */
public class IOStreamTasks {
    public static void main(String[] args) throws IOException {
        File text = new File("C:\\Users\\kozlo\\IdeaProjects\\test\\src\\resources\\wap.txt");
        File textCopy = new File("C:\\Users\\kozlo\\IdeaProjects\\test\\src\\resources\\wap_copy.txt");
        File textDe = new File("C:\\Users\\kozlo\\IdeaProjects\\test\\src\\resources\\wap_copyDe.txt");
        File textKey = new File("C:\\Users\\kozlo\\IdeaProjects\\test\\src\\resources\\key.txt");
        File dirCopy = new File("C:\\Users\\kozlo\\IdeaProjects\\test\\src\\resources\\");
//        try(InputStream in = new FileInputStream(text);
//            OutputStream out = new FileOutputStream(textCopy);){
//            copy(in, out);
//        }
//        split(text, dirCopy, 1440 * 1024);

//assembly(split(text, dirCopy, 1440 * 1024), textCopy);
//        try(InputStream in = new FileInputStream(text);
//            OutputStream out = new FileOutputStream(textCopy);){
//            encrypt(in, out, "key");
//        }
//        try(InputStream in = new FileInputStream(textCopy);
//            OutputStream out = new FileOutputStream(textDe);){
//            encrypt(in, out, "key");
//        }
//
//        encrypt(text, textCopy, textKey);
//        encrypt(textCopy, textDe, textKey);

        InputStream in = new RandomInputStream(new Random(), 30);
        int len = 0;
        while ((len = in.read()) != -1) {
            System.out.println(len);
        }
    }

    /**
     * Полностью копирует один поток в другой.
     *
     * @param src Входящий поток.
     * @param dst Выходящий поток.
     * @throws IOException Будет выброшен в случае ошибки.
     */
    public static void copy(InputStream src, OutputStream dst) throws IOException {
        // TODO implement
        int len;
        byte[] buf = new byte[1024];

        while ((len = src.read(buf)) > 0) {
            dst.write(buf, 0, len);
        }

    }

    /**
     * Последовательно разбивает файл на несколько более мелких, равных
     * указанному размеру. Последний файл может быть меньше задданого
     * размера.
     *
     * @param file   Файл, который будет разбит на несколько.
     * @param dstDir Директория, в которой будут созданы файлы меньшего размера.
     * @param size   Размер каждого файла-части, который будет создан.
     * @return Список файлов-частей в том порядке, в котором они должны считываться.
     * @throws IOException Будет выброшен в случае ошибки.
     */
    public static List<File> split(File file, File dstDir, int size) throws IOException {
        List<File> files = new ArrayList<>();

        try (InputStream in = new FileInputStream(file);) {

            byte[] buf = new byte[size];
            int len;
            int countFile = 1;
            File fileN;
            String[] fileName = file.getName().split("\\.");

            while ((len = in.read(buf)) > 0) {
                if (fileName.length > 1) {
                    fileN = new File(dstDir.getAbsolutePath() + "/" + fileName[0] + "_" + countFile + "." + fileName[1]);
                } else {
                    fileN = new File(dstDir.getAbsolutePath() + "/newCopyFile_" + countFile + ".txt");
                }

                try (OutputStream out = new FileOutputStream(fileN);) {
                    out.write(buf, 0, len);
                }

                countFile++;
                files.add(fileN);
            }
        }

        return files;
    }

    /**
     * Собирает из частей один файл.
     *
     * @param files Список файлов в порядке чтения.
     * @param dst   Файл, в который будут записаны все части.
     * @throws IOException Будет выброшен в случае ошибки.
     */
    public static void assembly(List<File> files, File dst) throws IOException {
        try (OutputStream out = new FileOutputStream(dst, true)) {
            for (File file : files) {
                byte[] buf = new byte[1024];
                int len;
                try (InputStream in = new FileInputStream(file);) {
                    while ((len = in.read(buf)) > 0) {
                        out.write(buf, 0, len);
                    }
                }
            }
        }
    }

    /**
     * Шифрует/дешифрует поток с помощью XOR. В качестве ключа используется пароль.
     *
     * @param src        Входящий поток, данные которого будут зашифрованы/расшифрованы.
     * @param dst        Выходящий поток, куда будут записаны зашифрованные/расшифрованные данные.
     * @param passphrase Пароль.
     * @throws IOException Будет выброшен в случае ошибки.
     */
    public static void encrypt(InputStream src, OutputStream dst, String passphrase) throws IOException {

        int len = 0;
        int index = 0;
        byte[] buf = new byte[1024];
        while ((len = src.read(buf)) > 0) {
            for (int i = 0; i < buf.length; i++) {
                buf[i] = (byte) (buf[i] ^ passphrase.charAt(index++));
                if (index == passphrase.length()) {
                    index = 0;
                }
            }

            dst.write(buf, 0, len);
        }
    }

    /**
     * Шифрует/дешифрует файл с помощью файла-ключа.
     *
     * @param src Файл, который должен быть зашифрован/расшифрован.
     * @param dst Файл, куда будут записаны зашифрованные/расшифрованные данные.
     * @param key Файл-ключ.
     * @throws IOException Будет выброшен в случае ошибки.
     */
    public static void encrypt(File src, File dst, File key) throws IOException {
        try (InputStream in = new FileInputStream(src);
             OutputStream out = new FileOutputStream(dst);
        ) {
            InputStream keyIN = new FileInputStream(key);
            int len = 0;
            byte[] buf = new byte[1024];
            while ((len = in.read(buf)) > 0) {

                for (int i = 0; i < buf.length; i++) {

                    int keyInt = keyIN.read();

                    if (keyInt == -1) {
                        keyIN.close();
                        keyIN = new FileInputStream(key);
                        keyInt = keyIN.read();
                    }

                    buf[i] = (byte) (buf[i] ^ keyInt);
                }

                out.write(buf, 0, len);
            }

        }
    }
}
