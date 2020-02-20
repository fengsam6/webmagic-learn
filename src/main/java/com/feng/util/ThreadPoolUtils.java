package com.feng.util;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

public class ThreadPoolUtils {
    public static ExecutorService creatIOsThreadPool(){
        return creatIOsThreadPool(0);
    }

    public static ExecutorService creatIOsThreadPool(int core){
        int CpuNum = CpuNumUtils.getCpuNum();
        ExecutorService executorService = new ThreadPoolExecutor(core, 2*CpuNum,
                60L, TimeUnit.SECONDS,
                new LinkedBlockingQueue<Runnable>());
        return executorService;
    }

    public static ExecutorService creatCpusThreadPool(int core){
        int CpuNum = CpuNumUtils.getCpuNum();
        if(core>CpuNum){
            core=CpuNum;
        }
        return new ThreadPoolExecutor(core, CpuNum+1,
                60L, TimeUnit.SECONDS,
                new LinkedBlockingQueue<Runnable>());
    }

    public static ExecutorService creatCpusThreadPool(){
        int CpuNum = CpuNumUtils.getCpuNum();
        return creatCpusThreadPool(CpuNum);
    }
}
