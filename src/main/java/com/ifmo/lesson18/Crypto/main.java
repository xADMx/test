package com.ifmo.lesson18.Crypto;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;

public class main {
    public static void main(String[] args) throws IOException {
        File text = new File("C:\\Users\\ADMnet\\IdeaProjects\\test\\src\\resources\\crypt.txt");

        String str = "text sec";

        byte[] key = new String("kyes").getBytes();

        try(InputStream in = new CryptoInputStream(
                new FileInputStream(text), key);
        OutputStream out = new CryptoOutputStream(new FileOutputStream(text), key)){

            System.out.println("text: " + str);

            out.write(str.getBytes());
            out.flush();

            int len = 0;
            StringBuilder sb = new StringBuilder();
            byte[] buf = new byte[1024];
            while ((len = in.read(buf)) > 0) {
                sb.append(new String(buf, 0, len));
            }

            System.out.println("decode: "+ sb.toString());


        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
