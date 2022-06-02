package com.company;

public class FilterProcessing {

    static int[][] apply(int[][] pixels, double[] c, double threshLvl){
        int dif = (int) ((Math.sqrt(c.length)-1)/2);
        int h = pixels.length -dif*2;
        int w = pixels[0].length -dif*2;
        int[][] my_pxls = new int[h][w];
        int color=0;
        int[] a = new int[c.length];
        for(int y =dif; y<h-dif; y++){
            for (int x = dif; x<w-dif; x++){
                for (int i=-dif; i<=dif;i++){
                    for (int j=-dif;j<=dif;j++){
                        a[(i+dif)*(2*dif+1)+(j+dif)] = pixels[y+i][x+j] & 0xff;
                    }
                }
                color = 0;
                for (int n=0; n<a.length;n++){
                    color +=a[n]*c[n];
                }
                if(color < 0) color = 0;
                else if(color >255) color = 255;
                my_pxls[y-1][x-1] = (255 << 24) | color |(color << 8)|(color<<16);
            }
        }
        zeroOrOne(my_pxls, threshLvl);
        return  my_pxls;
    }

    private static void zeroOrOne(int[][] px, double enter){

        int color = 0;
        for (int i=0; i<px.length; i++){
            for (int j=0; j<px[0].length;j++){
                color = px[i][j] & 0xff;
                //color = (color - min)/(max-min);
                if (color > enter*255) color = 255 ;
                else color=0;
                px[i][j] = (255 << 24) | color |(color << 8)|(color<<16);

            }
        }
    }

}
