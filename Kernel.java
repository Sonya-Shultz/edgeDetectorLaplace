package com.company;

public class Kernel{
    public static double[] positive3X3small = new double[]{0,1,0,1,-4,1,0,1,0};
    public static double[] positive3X3 = new double[]{1,1,1,1,-8,1,1,1,1};
    public static double[] positiveLoG1n4 = new double[]{
                    0,1,1,2,2,2,1,1,0,
                    1,2,4,5,5,5,4,2,1,
                    1,4,5,3,0,3,5,4,1,
                    2,5,3,-12,-24,-12,3,5,2,
                    2,5,0,-24,-40,-24,0,5,2,
                    2,5,3,-12,-24,-12,3,5,2,
                    1,4,5,3,0,3,5,4,1,
                    1,2,4,5,5,5,4,2,1,
                    0,1,1,2,2,2,1,1,0,
            };
    public static double[] positive5X5 = new double[]{
            0,0,1,0,0,
            0,1,2,1,0,
            1,2,-16,2,1,
            0,1,2,1,0,
            0,0,1,0,0,
    };
}