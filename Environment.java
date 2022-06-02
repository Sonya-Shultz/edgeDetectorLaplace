package com.company;

public class Environment {
    String fileName = "src/resurce/input.jpg";
    double[] kernel = Kernel.positive3X3;
    private String k_name = "positive 3X3";
    double threshCount = 0.75;
    String outputName = "src/resurce/output.jpg";
    int threadCount = 5;
    long start;
    long end;

    public void showInput(){
        System.out.println("____________________________________");
        System.out.println("Input file: "+fileName);
        System.out.println("Output file: "+outputName);
        System.out.println("Thresh lvl: "+threshCount);
        System.out.println("Filter kernel: "+k_name);
        System.out.println("Number of Thread: "+threadCount);
        System.out.println("____________________________________");
    }

    public void showExecutionTime(String name){
        if (threadCount == 1) name = "linear";
        System.out.println("____________________________________");
        System.out.println("For "+name+" execution time is "+(end-start)+" milli seconds.");
        System.out.println("____________________________________");
    }

    public void parseInputData(String[] args) throws NumberFormatException{
        for (int i=0; i<args.length; i++){
            if (args[i].contains("--input=")){
                fileName = args[i].replaceAll("--input=","");
                fileName = fileName.replaceAll("\"","");
            }
            if (args[i].contains("--output=")){
                outputName = args[i].replaceAll("--output=","");
                outputName = outputName.replaceAll("\"","");
            }
            if (args[i].contains("--kernel=")){
                String tmp = args[i].replaceAll("--kernel=","");
                int tmp2 = Integer.parseInt(tmp);
                switch (tmp2){
                    case (1):
                        kernel=Kernel.positive3X3small;
                        k_name = "positive 3X3 small";
                        break;
                    case (2):
                        kernel=Kernel.positive3X3;
                        k_name = "positive 3X3";
                        break;
                    case (3):
                        kernel=Kernel.positive5X5;
                        k_name = "positive 5X5";
                        break;
                    case (4):
                        kernel=Kernel.positiveLoG1n4;
                        k_name = "positive LoG(1.4)";
                        break;
                    default:
                        System.out.println("Can't find your kernel, so put LoG(1.4)");
                        break;
                }
            }
            if (args[i].contains("--thresh=")){
                String tmp = args[i].replaceAll("--thresh=","");
                threshCount = Double.parseDouble(tmp);
                if (threshCount > 1 || threshCount < 0 ){
                    System.out.println("Wrong thresh lvl, set it to 0.75");
                    threshCount = 0.75;
                }
            }
            if (args[i].contains("--thread=")){
                String tmp = args[i].replaceAll("--thread=","");
                threadCount = Integer.parseInt(tmp);
                if (threadCount <= 0 ){
                    System.out.println("Wrong thread number, set it to 5");
                    threadCount = 5;
                }
            }
        }
    }
}
