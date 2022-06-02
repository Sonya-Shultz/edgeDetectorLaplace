package com.company;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.awt.image.DataBufferByte;
import java.io.File;
import java.io.IOException;

public class Image {
    private int w = 0;
    private int h = 0;
    int[][] img;
    public Image() throws IOException {
        w = 0;
        h = 0;
        img = new int [0][0];
    }
    public Image(int[][] arr) throws IOException {
        setImg(arr);
    }
    public Image(String fName) throws IOException {
        read(fName);
    }
    public Image(int W, int H) throws IOException {
        w = W;
        h = H;
        img = new int [h][w];
    }
    public void read(String fileName) throws IOException {
        BufferedImage bImage = ImageIO.read(new File(fileName));
        w = bImage.getWidth();
        h = bImage.getHeight();
        img = convertToInt(bImage,w,h);
    }

    public int getW(){return w;}
    public int getH(){return h;}
    public int[][] getImg(){return img;}
    public int[][] getPartOfImg(int start, int end){
        int [][] res = new int[end-start][w];
        if (end - start >= 0) System.arraycopy(img, start, res, 0, end - start);
        return res;
    }
    public void setImg(int[][] Img){
        img = Img;
        h = Img.length;
        w = Img[0].length;
    }

    static int[][] convertToInt(BufferedImage img, int w, int h) {
        final byte[] px = ((DataBufferByte) img.getRaster().getDataBuffer()).getData();
        final boolean alpha = img.getAlphaRaster() != null;
        int[][] result = new int[h][w];
        if (alpha) {
            final int pxL = 4;
            int r = 0;
            int g = 0;
            int b = 0;
            int s = 0;
            for (int pxl = 0, row = 0, col = 0; pxl + 3 < px.length; pxl += pxL) {
                int argb = 0;
                argb += (((int) px[pxl] & 0xff) << 24);
                b = ((int) px[pxl + 1] & 0xff);
                g = ((int) px[pxl + 2] & 0xff00) >> 8;
                r = ((int) px[pxl + 3] & 0xff0000) >> 16;
                s = (r + g + b) / 3;
                argb += s;
                argb += s << 8;
                argb += s << 16;

                result[row][col] = argb;
                col++;
                if (col == w) {
                    col = 0;
                    row++;
                }
            }
        } else {
            final int pxL = 3;
            int r = 0;
            int g = 0;
            int b = 0;
            int s = 0;
            for (int pxl = 0, row = 0, col = 0; pxl + 2 < px.length; pxl += pxL) {
                int argb = 0;
                argb += -16777216;
                b = (px[pxl] & 0xff);
                g = (px[pxl + 1] & 0xff00) >> 8;
                r = (px[pxl + 2] & 0xff0000) >> 16;
                s = (r + g + b) / 3;
                argb += s;
                argb += (s << 8);
                argb += (s << 16);
                result[row][col] = argb;
                col++;
                if (col == w) {
                    col = 0;
                    row++;
                }
            }
        }
        return result;
    }

    void writeToFile(String name) throws IOException{
        BufferedImage b = new BufferedImage(img[0].length,img.length,4);
        for (int i=0; i<img.length;i++){
            for (int j=0; j<img[0].length;j++){
                int argb = img[i][j]<<24 |img[i][j]<<16|img[i][j]<<8|img[i][j];
                b.setRGB(j,i,argb);
            }
        }
        ImageIO.write(b,"jpg",new File(name));
        System.out.println("Write to "+name+" file. Status: complete.");
    }
}
