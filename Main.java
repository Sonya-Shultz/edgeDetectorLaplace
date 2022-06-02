package com.company;
import java.io.IOException;
import java.util.concurrent.ExecutionException;

public class Main {

    public static void main(String[] args) {
        Environment env = new Environment();
        env.parseInputData(args);
        env.showInput();
        try {
            Image input = new Image(env.fileName);
            System.out.println("Size of input img: "+input.getH()+"X"+input.getW());
            env.threadCount=3;
            int[][] res = StripPool.start(env,input);
            env.showExecutionTime("parallel");
            Image output = new Image(res);
            output.writeToFile(env.outputName);
            int [][] tmp = input.getImg();
            env.start = System.currentTimeMillis();
            res = FilterProcessing.apply(tmp,env.kernel,env.threshCount);
            env.end = System.currentTimeMillis();
            env.showExecutionTime("linear");
            output = new Image(res);
            output.writeToFile(env.outputName);
        }catch (IOException e) {
            e.printStackTrace();
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (ExecutionException e) {
            e.printStackTrace();
        }

    }


}
