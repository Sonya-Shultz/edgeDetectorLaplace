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
            if (input.getH() < Math.sqrt(env.kernel.length)) throw new IndexOutOfBoundsException();
            int[][] res = StripPool.start(env,input);
            env.showExecutionTime("parallel");
            Image output = new Image(res);
            output.writeToFile(env.outputName);
            /*int [][] tmp = input.getImg();
            env.start = System.currentTimeMillis();
            res = FilterProcessing.apply(tmp,env.kernel,env.threshCount);
            env.end = System.currentTimeMillis();
            env.showExecutionTime("linear");
            output = new Image(res);
            output.writeToFile(env.outputName);*/
        }catch (IOException e) {
            e.printStackTrace();
            System.out.println("ERROR on data reading!");
        } catch (InterruptedException e) {
            e.printStackTrace();
            System.out.println("ERROR in thread finish!");
        } catch (ExecutionException e) {
            e.printStackTrace();
            System.out.println("ERROR when try see already interrupt thread!");
        } catch (IndexOutOfBoundsException e){
            e.printStackTrace();
            System.out.println("ERROR: image smaller then kernel!");
        }

    }


}
