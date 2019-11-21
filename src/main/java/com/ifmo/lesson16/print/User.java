package com.ifmo.lesson16.print;

import java.io.Serializable;
import java.text.SimpleDateFormat;
import java.util.Date;

public class User implements Serializable {
    private long timestamp;
    private String name;
    private boolean block;
    private SimpleDateFormat format = new SimpleDateFormat("dd.MM.yyyy HH:mm:ss.SSS");

    public User(long timestamp, String name, boolean block) {
        this.timestamp = timestamp;
        this.name = name;
        this.block = block;
    }

    public long getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(long timestamp) {
        this.timestamp = timestamp;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public boolean isBlock() {
        return block;
    }

    public void setBlock(boolean block) {
        this.block = block;
    }

    @Override
    public String toString() {
        return "Пользователь{" +
                "Логин = '" + name + '\'' +
                ", Дата последнего входа=" + format.format(new Date(timestamp)) +
                ", Блокирован? =" + block +
                '}';
    }
}
