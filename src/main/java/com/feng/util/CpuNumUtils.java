package com.feng.util;

public class CpuNumUtils {
    public static int getCpuNum(){
        int n = Runtime.getRuntime().availableProcessors();
        return n;
    }
}
