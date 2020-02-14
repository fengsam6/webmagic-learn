package com.feng.util;

public class ThreadSleepUtils {
    public static void sleepNs(int n) {
        try {
            //休息3s，等待Tomcat容器启动完成
            Thread.sleep(n * 1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }


    public static void sleepNsAndYield(int n) {
        Thread.yield();
        sleepNs(n);
    }
}
