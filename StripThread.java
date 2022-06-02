package com.company;

import java.util.concurrent.Callable;

public class StripThread implements Callable<int[][]> {
    StripThread(int[][] inp, double[] fil, double lvl){
        input = inp;
        filter = fil;
        threshLvl = lvl;
    }
    int[][] input;
    double[] filter;
    double threshLvl;
    @Override
    public int[][] call() {
         return FilterProcessing.apply(input, filter, threshLvl);
    }
}
