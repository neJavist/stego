package org.example.matrix;

import org.example.rgb_ycrcb.RGB_To_YCrCb;
import org.example.rgb_ycrcb.YCrCb;
import org.example.rgb_ycrcb.YCrCb_To_RGB;

import java.awt.*;
import java.awt.image.BufferedImage;

public class RGB_YCrCb_Matrix {

    RGB_To_YCrCb rgb_to_yCrCb = new RGB_To_YCrCb();
    YCrCb_To_RGB yCrCb_to_rgb = new YCrCb_To_RGB();

    public Color[][] createRGBMatrix(BufferedImage image) {
        Color[][] matrix = new Color[1080][1920];

        for (int i = 0; i < 1080; i++) {
            for (int j = 0; j < 1920; j++) {
                matrix[i][j] = new Color(image.getRGB(j,i));
            }
        }

        return matrix;
    }

    public YCrCb[][] createYCrCbMatrix(Color[][] color) {
        YCrCb[][] matrix = new YCrCb[1080][1920];

        for (int i = 0; i < 1080; i++) {
            for (int j = 0; j < 1920; j++) {
                matrix[i][j] = new YCrCb(rgb_to_yCrCb.getY(color[i][j]), rgb_to_yCrCb.getCr(color[i][j]), rgb_to_yCrCb.getCb(color[i][j]));
            }
        }

        return matrix;
    }

    public Color[][] YCrCb_To_RGBMatrix(YCrCb[][] color) {
        Color[][] matrix = new Color[1080][1920];

        for (int i = 0; i < 1080; i++) {
            for (int j = 0; j < 1920; j++) {
                int newRed = yCrCb_to_rgb.getRed(color[i][j]);
                int newGreen = yCrCb_to_rgb.getGreen(color[i][j]);
                int newBlue = yCrCb_to_rgb.getBlue(color[i][j]);

                matrix[i][j] = new Color(newRed, newGreen, newBlue);
            }
        }

        return matrix;
    }

    public double[][] createYMatrix(YCrCb[][] color, int blockWidth, int blockHeight) {
        double[][] matrix = new double[blockWidth][blockHeight];

        for (int i = 0; i < blockWidth; i++) {
            for (int j = 0; j < blockHeight; j++) {
                matrix[i][j] = color[i][j].getY();
            }
        }

        return matrix;
    }

    public YCrCb[][] YMatrix_To_YCrCbMatrix(YCrCb[][] color, double[][] yMatrix) {
        YCrCb[][] tmpMatrix = new YCrCb[1080][1920];

        for (int i = 0; i < 1080; i++) {
            for (int j = 0; j < 1920; j++) {
                tmpMatrix[i][j] = new YCrCb(yMatrix[i][j], color[i][j].getCr(), color[i][j].getCb());
            }
        }

        return tmpMatrix;
    }


}
