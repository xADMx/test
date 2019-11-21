package com.ifmo.lesson16.print;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.OutputStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

public class PrintServer {

    private int port;

    private SimpleDateFormat format = new SimpleDateFormat("HH:mm:ss.SSS");
    private Map<String, User> users = new HashMap<>();

    public PrintServer(int port) {
        this.port = port;
    }

    private void start() throws IOException {
        try (ServerSocket ssocket = new ServerSocket(port)) {
            System.out.println("Server started on " + ssocket);

            while (true) {
                Socket sock = ssocket.accept();

                try {
                    process(sock);
                } catch (ClassNotFoundException e) {
                    System.err.println("Wrong message was received");

                    e.printStackTrace();
                } finally {
                    sock.close();
                }
            }
        }
    }

    private void process(Socket sock) throws IOException, ClassNotFoundException {
        String host = sock.getInetAddress().getHostAddress();

        try (ObjectInputStream objIn = new ObjectInputStream(sock.getInputStream())) {
            Object obj = objIn.readObject();
            Message msg = (Message) obj;
            User currentUser = null;
            if (users.containsKey(msg.getSender())) {
                currentUser = users.get(msg.getSender());
                currentUser.setTimestamp(msg.getTimestamp());
            } else {
                currentUser = new User(msg.getTimestamp(), msg.getSender(), false);
                users.put(msg.getSender(), currentUser);
            }

            if (!currentUser.isBlock() || msg.getText().contains("/ban") || msg.getText().contains("/unban")) {
                printMessage(msg, sock);
            } else {
                msg.setText("blockCurrentUser");
                printMessage(msg, sock);
            }

        } catch (IOException | ClassNotFoundException | RuntimeException e) {
            System.err.println("Failed process connection from: " + host);

            e.printStackTrace();

            throw e;
        }
    }

    private void printMessage(Message msg, Socket sock) throws IOException {

        switch (msg.getText()) {
            case "/ping":
                try (ObjectOutputStream outputStream = new ObjectOutputStream(sock.getOutputStream())) {
                    Message message = new Message(System.currentTimeMillis(), null, null);
                    outputStream.writeObject(message);
                    outputStream.flush();
                }
                break;
            case "/server_time":
                try (ObjectOutputStream outputStream = new ObjectOutputStream(sock.getOutputStream())) {
                    Message message = new Message(System.currentTimeMillis(), null, null);
                    outputStream.writeObject(message);
                    outputStream.flush();
                }
                break;
            case "blockCurrentUser":
                try (ObjectOutputStream outputStream = new ObjectOutputStream(sock.getOutputStream())) {
                    Message message = new Message(System.currentTimeMillis(), "Server", "Current user is blocked.");
                    outputStream.writeObject(message);
                    outputStream.flush();
                }
                break;
            case "/list_users":
                try (ObjectOutputStream outputStream = new ObjectOutputStream(sock.getOutputStream())) {
                    outputStream.writeObject(users);
                    outputStream.flush();
                }
                break;
            default:
                if(msg.getText().contains("/ban") || msg.getText().contains("/unban")){
                    ban(msg);
                }

                String senderAddr = sock.getInetAddress().getHostAddress();
                System.out.printf("%s (%s) at %s wrote: %s\n", msg.getSender(), senderAddr, format.format(new Date(msg.getTimestamp())), msg.getText());
                break;
        }

    }

    private void ban(Message msg){
        String[] command = msg.getText().split(" ");
        if(command.length > 1){
            switch (command[0]){
                case "/ban":
                    if(users.containsKey(command[1])){
                        users.get(command[1]).setBlock(true);
                    }
                    break;
                case  "/unban":
                    if(users.containsKey(command[1])){
                        users.get(command[1]).setBlock(false);
                    }
                    break;
            }
        }
    }

    public static void main(String[] args) throws IOException {
        if (args == null || args.length == 0)
            throw new IllegalArgumentException("Port must be specified");

        int port = Integer.parseInt(args[0]);

        PrintServer printServer = new PrintServer(port);

        printServer.start();
    }
}
