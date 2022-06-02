package com.company;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.*;

public class StripPool {
    static public int[][] start(Environment env, Image input) throws InterruptedException, ExecutionException, IndexOutOfBoundsException {
        int kernel_size = (int)Math.sqrt(env.kernel.length)/2;
        int [][] res = new int[input.getH()-2*kernel_size][input.getW()-2*kernel_size];

        float s = (input.getH()-2*kernel_size)/(float)env.threadCount;
        int strip_size = Math.round(s)+2*kernel_size;

        ExecutorService pool = Executors.newFixedThreadPool(env.threadCount);

        int start = 0;
        int end = Math.min(strip_size, input.getH()-start);

        if(end - start < kernel_size) throw new IndexOutOfBoundsException();
        while ((strip_size-2*kernel_size)*env.threadCount < input.getH()-2*kernel_size)
            strip_size += 1;

        List<Callable<int[][]>> tasks = new ArrayList<>();

        for (int i=0; i<env.threadCount;i++){
            StripThread r = new StripThread( input.getPartOfImg(start, end)
                    ,env.kernel, env.threshCount);
            tasks.add(r);
            start = end - 2 * kernel_size;
            end = start + Math.min(strip_size, input.getH()-start);
            if(end - start < kernel_size) break;
        }

        env.start = System.currentTimeMillis();
        List<Future<int[][]>> ans = pool.invokeAll(tasks);
        env.end = System.currentTimeMillis();

        int place = 0;
        for (Future<int[][]> an : ans) {
            for (int[] ints : an.get()) {
                res[place] = ints;
                place = place + 1;
            }
        }

        pool.shutdown();
        return res;
    }
}
