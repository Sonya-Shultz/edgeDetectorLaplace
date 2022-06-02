package com.company;
import java.io.IOException;

public class Main {

    public static void main(String[] args) {
        Environment env = new Environment();
        env.parseInputData(args);
        //env.fileName="input2.jpg";
        //env.outputName="output2.jpg";
        env.showInput();
        try {
            Image input = new Image(env.fileName);
            int [][] tmp = input.getImg();
            long start = System.currentTimeMillis();
            int [][] res = FilterProcessing.apply(tmp,env.kernel,env.threshCount);
            long end = System.currentTimeMillis();
            System.out.println("Execution time is "+(end-start)+" milli seconds.");
            Image output = new Image(res);
            output.writeToFile(env.outputName);
        }catch (IOException e) {
            e.printStackTrace();
        }

    }


}
