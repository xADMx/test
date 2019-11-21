package com.ifmo.lesson16.print;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.OutputStream;
import java.net.*;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Enumeration;
import java.util.Map;
import java.util.Scanner;

public class PrintClient {

    private SocketAddress serverAddr;
    private SimpleDateFormat format = new SimpleDateFormat("dd.MM.yyyy HH:mm:ss.SSS");
    private String name;

    private Scanner scanner;

    public PrintClient(SocketAddress serverAddr, Scanner scanner) {
        this.serverAddr = serverAddr;
        this.scanner = scanner;
    }

    private void start() throws IOException {
        System.out.println("Enter your name: ");

        name = scanner.nextLine();

        while (true) {
            System.out.println("Enter message to send: ");

            String msg = scanner.nextLine();

            if ("/exit".equals(msg))
                break;

            switch (msg){
                case "/nick":
                    System.out.println("Enter new name:");
                    name = scanner.nextLine();
                    buildAndSendMessage(msg);
                    break;
                case "/myaddr":
                    printAddresses();
                    buildAndSendMessage(msg);
                    break;
                case "/ping":
                    try {
                        sendEchoMessage(msg);
                    } catch (ClassNotFoundException e) {
                        e.printStackTrace();
                    }
                    break;
                case "/server_time":
                    serverTime(msg);
                    break;
                case "/list_users":
                    userList(msg);
                    break;
            }

            if(msg.contains("/ban") || msg.contains("/unban")){
                buildAndSendMessage(msg);
            }
        }
    }

    private void printAddresses() throws SocketException {
        Enumeration e = NetworkInterface.getNetworkInterfaces();

        while (e.hasMoreElements()) {
            NetworkInterface n = (NetworkInterface) e.nextElement();

            Enumeration ee = n.getInetAddresses();

            while (ee.hasMoreElements()) {
                InetAddress i = (InetAddress) ee.nextElement();

                System.out.println(i.getHostAddress());
            }
        }
    }

    private void buildAndSendMessage(String msg) throws IOException {
        Message message = new Message(System.currentTimeMillis(), name, msg);

        sendPrintMessage(message);

        System.out.println("Sent: " + message);
    }

    private void sendPrintMessage(Message msg) throws IOException {
        try (Socket sock = new Socket()) {
            sock.connect(serverAddr);

            try (OutputStream out = sock.getOutputStream()) {
                ObjectOutputStream objOut = new ObjectOutputStream(out);
                objOut.writeObject(msg);
                objOut.flush();
            }
        }
    }

    private void serverTime(String msg) throws IOException {
        Message messageStart = new Message(System.currentTimeMillis(), name, msg);

        Message messageResult = null;
        try {
            messageResult = (Message) sendAndResultMessage(messageStart);
            System.out.printf("Время сервера: %s\n", format.format(new Date(messageResult.getTimestamp())));
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    private void userList(String msg) throws IOException {
        Message messageStart = new Message(System.currentTimeMillis(), name, msg);

        Map<String, User> messageResults = null;
        System.out.println("Список пользователей:");
        try {
            messageResults = (Map<String, User>) sendAndResultMessage(messageStart);
            for(Map.Entry<String, User> messageResult : messageResults.entrySet()) {
                System.out.println(messageResult.getValue().toString());
            }
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    private Object sendAndResultMessage(Message msg) throws IOException, ClassNotFoundException {
        try (Socket sock = new Socket()) {
            sock.connect(serverAddr);

            try (OutputStream out = sock.getOutputStream()) {
                ObjectOutputStream objOut = new ObjectOutputStream(out);
                objOut.writeObject(msg);
                objOut.flush();

                ObjectInputStream objIn = new ObjectInputStream(sock.getInputStream());
                return objIn.readObject();
            }
        }
    }

    private void sendEchoMessage(String msg) throws IOException, ClassNotFoundException {
        System.out.printf("Обмен пакетами с %s:\n", serverAddr);
        for (int i = 0; i < 5; i++) {
            try (Socket sock = new Socket()) {
                sock.connect(serverAddr);
                try (ObjectOutputStream objOut = new ObjectOutputStream(sock.getOutputStream())) {
                    String senderAddr = sock.getInetAddress().getHostAddress();
                    objOut.writeObject(new Message(System.currentTimeMillis(), name, msg));
                    objOut.flush();

                    ObjectInputStream objIn = new ObjectInputStream(sock.getInputStream());
                    Message resMsg = (Message) objIn.readObject();
                    System.out.printf("Ответ от %s: время=%sмс \n", senderAddr, Math.abs(System.currentTimeMillis() - resMsg.getTimestamp()), resMsg.getText());
                }
            }
        }
    }

    private static SocketAddress parseAddress(String addr) {
        String[] split = addr.split(":");
        return new InetSocketAddress(split[0], Integer.parseInt(split[1]));
    }

    public static void main(String[] args) throws IOException {
        String addr = null;

        if (args != null && args.length > 0)
            addr = args[0];

        Scanner scanner = new Scanner(System.in);

        if (addr == null) {
            System.out.println("Enter server address");

            addr = scanner.nextLine();
        }

        PrintClient client = new PrintClient(parseAddress(addr), scanner);

        client.start();
    }
}
