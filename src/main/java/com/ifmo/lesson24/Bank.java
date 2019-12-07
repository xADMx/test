package com.ifmo.lesson24;

import com.ifmo.lesson13.StreamTasks;

import java.math.BigInteger;
import java.util.List;
import java.util.Map;
import java.util.Random;
import java.util.concurrent.*;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;
import java.util.stream.Stream;

public class Bank {
    private Map<Long, User> users = new ConcurrentHashMap<>();
    private Map<String, Object> await = new ConcurrentHashMap<>();
    private List<Account> accounts = new CopyOnWriteArrayList<>();
    public static final BlockingQueue<Transaction> log = new LinkedBlockingDeque<>();

    private class User {
        private final long id;
        private final String name;

        private User(long id, String name) {
            this.id = id;
            this.name = name;
        }
    }

    private class Account {
        private final long id;
        private final long userId;
        private long amount;
        private Lock lock = new ReentrantLock();

        private Account(long id, long userId, long amount) {
            this.id = id;
            this.userId = userId;
            this.amount = amount;
        }

        public Lock getLock() {
            return lock;
        }
    }

    private class Transaction {
        private final BigInteger transactionId;
        private final long fromAccountId;
        private final long toAccountId;
        private final long amount;
        private final boolean success;

        private Transaction(long fromAccountId, long toAccountId, long amount, boolean success) {
            this.success = success;
            this.transactionId = new BigInteger("" + System.currentTimeMillis() + fromAccountId + toAccountId + amount);
            this.fromAccountId = fromAccountId;
            this.toAccountId = toAccountId;
            this.amount = amount;
        }

        @Override
        public String toString() {
            return "Transaction{" +
                    "transactionId=" + transactionId +
                    ", fromAccountId=" + fromAccountId +
                    ", toAccountId=" + toAccountId +
                    ", amount=" + amount +
                    ", success=" + success +
                    '}';
        }
    }

    public static void main(String[] args) throws InterruptedException {
        // 1. Сгенерируйте пользователей и их аккаунты (все идентификаторы должны быть уникальны).
        // 2. Переводите деньги со случайного аккаунта на случайный в 100 потоках.
        // Другими словами, создайте 100 потоков или пул из 100 потоков, в которых
        // выполните перевод вызовом метода transferMoney().

        Thread thrLog = new Thread(new ViewLog());
        thrLog.start();
        Bank bank = new Bank();

        bank.generatePeople(10);
        Random rnd = new Random();
        ExecutorService es = Executors.newFixedThreadPool(100);

        for (int i = 0; i < 100 ; i++) {
            es.submit(() -> bank.transferMoney(
                    bank.getAccounts().get(rnd.nextInt(bank.getAccounts().size())),
                            bank.getAccounts().get(rnd.nextInt(bank.getAccounts().size())),
                            rnd.nextInt(10000) +8));
        }


        es.shutdown();
//        thrLog.interrupt();
    }

    public Map<Long, User> getUsers() {
        return users;
    }

    public List<Account> getAccounts() {
        return accounts;
    }

     public void generatePeople(int number) {
        // TODO implement.

        String[] name = new String[]{"Саша", "Маша", "Юля", "Катя", "Ваня", "Максим","Варлера", "Гена", "Коля"};

        long count = 1;

        Random rnd = new Random();

        for (int i = 0; i < number; i++) {

            users.put(count, new User(count, name[rnd.nextInt(8)]));
            accounts.add(new Account(count, count, rnd.nextInt(10000)));

            count++;

        }

    }

    // TODO Самая главная часть работы!
    public void transferMoney(Account from, Account to, long amount) {
        boolean result = false;

        from.getLock().lock();
        to.getLock().lock();
        try {
            if (from.amount - amount > 0) {
                to.amount += amount;
                result = true;
            }
        } finally {
            from.getLock().unlock();
            to.getLock().unlock();
        }

        log.add(new Transaction(from.id, to.id, amount, result));

        // 1. Атомарно и потокобезопасно перевести деньги в количестве 'amount' со счёта 'from' на счёт 'to'.
        // 2. Создать объект Transaction, содержащий информацию об операции и отправить в очередь
        // потоку Logger, который проснётся и напечатает её.
    }

    private static class ViewLog implements Runnable {

        @Override
        public void run() {
            int count = 1;
            boolean exit = false;
            while (!Thread.currentThread().isInterrupted()){
                try {
                    System.out.println(log.take().toString());
                    count++;
                } catch (InterruptedException e) {
                    e.printStackTrace();
                    Thread.currentThread().interrupt();
                }
                System.out.println(count);
            }



        }
    }
}
