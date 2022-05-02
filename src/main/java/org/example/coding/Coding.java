package org.example.coding;

import org.example.Property;
import org.example.WaveletTransform;
import org.example.byte_conversion.ByteTransformation;
import org.example.loader.Loader;
import org.example.matrix.Matrix;
import org.example.matrix.RGB_YCrCb_Matrix;
import org.example.rgb_ycrcb.YCrCb;
import org.example.video.Video;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class Coding {

    private final Property property = new Property();
    private final Loader loader = new Loader();
    private final RGB_YCrCb_Matrix rgb_yCrCb_matrix = new RGB_YCrCb_Matrix();
    private final WaveletTransform waveletTransform = new WaveletTransform();
    private final ByteTransformation byteTransformation = new ByteTransformation();
    private final int a1x = 4, a1y = 4, a2x = 7, a2y = 7;
    private final Video video = new Video();
    private int countBits;

    private static int countEnoughFrames(int countBits) {
        double tmp = countBits / 32400.00;
        return (int) tmp + 1;
    }

    private static void getListOfBitsCharArray(int countEnoughFrames, ArrayList<char[]> listOfBitsCharArray, char[] bitsCharArray, int countBits) {
        if (countEnoughFrames == 1)
            listOfBitsCharArray.add(bitsCharArray);
        else {
            ArrayList<Character> tmp = new ArrayList<>();
            for (char ch : bitsCharArray)
                tmp.add(ch);
            int tmpCountBits;
            tmpCountBits = countBits - (((countEnoughFrames - 1) * 32400));
            int fromIndex = 0, toIndex = 32400;
            for (int i = 0; i < countEnoughFrames; i++) {

                if (i == countEnoughFrames - 1) {

                    List<Character> tmpCharArray = tmp.subList(fromIndex, toIndex - 32400 + tmpCountBits);
                    char[] tmpCharArray2 = new char[tmpCharArray.size()];
                    for (int j = 0; j < tmpCharArray.size(); j++) {
                        tmpCharArray2[j] = tmpCharArray.get(j);
                    }
                    listOfBitsCharArray.add(tmpCharArray2);
                } else {
                    List<Character> tmpCharArray = tmp.subList(fromIndex, toIndex);
                    char[] tmpCharArray2 = new char[tmpCharArray.size()];
                    for (int j = 0; j < tmpCharArray.size(); j++) {
                        tmpCharArray2[j] = tmpCharArray.get(j);
                    }
                    listOfBitsCharArray.add(tmpCharArray2);
                    fromIndex += 32400;
                    toIndex += 32400;
                }
            }
        }

    }

    public void encryption() throws IOException {
        video.videoFragmentation(property.getEmptyFramesPath(), "input.mp4");
        video.videoFragmentation(property.getFramesPath(), "input.mp4");

        String[] bitsArray = byteTransformation.byteArrayToStringBitsArray();
        char[] bitsCharArray = new char[bitsArray.length * 8];
        countBits = bitsCharArray.length;
        int countEnoughFrames = countEnoughFrames(countBits);

        int k = 0;
        for (String str : bitsArray) {
            char[] tmp = str.toCharArray();
            for (char ch : tmp) {
                bitsCharArray[k] = ch;
                k++;
            }
        }

        ArrayList<char[]> listOfBitsCharArray = new ArrayList<>();
        getListOfBitsCharArray(countEnoughFrames, listOfBitsCharArray, bitsCharArray, countBits);

        for (int i = 0; i < countEnoughFrames; i++) {

            Color[][] rgbMatrix = rgb_yCrCb_matrix.createRGBMatrix(loader.loadFrames(i + 1));
            YCrCb[][] yCrCbMatrix = rgb_yCrCb_matrix.createYCrCbMatrix(rgbMatrix);
            double[][] yMatrix = rgb_yCrCb_matrix.createYMatrix(yCrCbMatrix, 1080, 1920);

            Matrix matY = new Matrix(1080, 1920, yMatrix);

            ArrayList<double[][]> arrayOf8x8TransformedMatrix = waveletTransform.arrayOfMatrixDirectTransform(matY.getArrayOf8x8Matrix(yMatrix));

            for (int j = 0; j < listOfBitsCharArray.get(i).length; j++) {
                double[][] tmpMatrix = arrayOf8x8TransformedMatrix.get(j);
                double p = 10;
                switch (listOfBitsCharArray.get(i)[j]) {
                    case '1': {
                        if ((tmpMatrix[a2x][a2y] - tmpMatrix[a1x][a1y]) < -p) {
                            tmpMatrix[a1x][a1y] += 5;
                            tmpMatrix[a2x][a2y] += 5;
                            arrayOf8x8TransformedMatrix.set(j, tmpMatrix);
                            break;
                        } else if ((tmpMatrix[a2x][a2y] - tmpMatrix[a1x][a1y]) >= -p) {
                            while ((tmpMatrix[a2x][a2y] - tmpMatrix[a1x][a1y]) > -p) {
                                tmpMatrix[a2x][a2y] -= 5;
                                tmpMatrix[a1x][a1y] += 5;
                            }
                            arrayOf8x8TransformedMatrix.set(j, tmpMatrix);
                            break;
                        }
                    }
                    case '0': {
                        if ((tmpMatrix[a2x][a2y] - tmpMatrix[a1x][a1y]) > p) {
                            tmpMatrix[a1x][a1y] += 5;
                            tmpMatrix[a2x][a2y] += 5;
                            arrayOf8x8TransformedMatrix.set(j, tmpMatrix);
                            break;
                        } else if ((tmpMatrix[a2x][a2y] - tmpMatrix[a1x][a1y]) <= p) {
                            while ((tmpMatrix[a2x][a2y] - tmpMatrix[a1x][a1y]) < p) {
                                tmpMatrix[a2x][a2y] += 5;
                                tmpMatrix[a1x][a1y] -= 5;
                            }
                            arrayOf8x8TransformedMatrix.set(j, tmpMatrix);
                            break;
                        }
                    }
                }
            }

            ArrayList<double[][]> arrayOfMatrixInverseTransform = waveletTransform.arrayOfMatrixInverseTransform(arrayOf8x8TransformedMatrix);
            YCrCb[][] YCrCbFinalMatrix = rgb_yCrCb_matrix.YMatrix_To_YCrCbMatrix(yCrCbMatrix, matY.getBigMatrix(arrayOfMatrixInverseTransform));
            Color[][] RGBFinalMatrix = rgb_yCrCb_matrix.YCrCb_To_RGBMatrix(YCrCbFinalMatrix);

            BufferedImage result = new BufferedImage(loader.loadFrames(i + 1).getWidth(), loader.loadFrames(i + 1).getHeight(), loader.loadFrames(i + 1).getType());

            for (int l = 0; l < 1080; l++) {
                for (int j = 0; j < 1920; j++) {
                    Color newColor = new Color(RGBFinalMatrix[l][j].getRed(), RGBFinalMatrix[l][j].getGreen(), RGBFinalMatrix[l][j].getBlue());
                    result.setRGB(j, l, newColor.getRGB());
                }
            }

            File output = new File(property.getFramesPath() + "frame" + (i + 1) + ".jpg");
            ImageIO.write(result, "jpg", output);
            arrayOf8x8TransformedMatrix.clear();
            arrayOfMatrixInverseTransform.clear();
        }
        video.videoCompiler();
    }

    public void decryption() throws IOException {
        video.videoFragmentation(property.getEncryptionFramesPath(), "output.mp4");

        int countEnoughFrames = countEnoughFrames(countBits);

        ArrayList<Character> tmpCharArrayList = new ArrayList<>();
        int[] lengthOfBitsArray = new int[countEnoughFrames];

        int g = 0;
        int count = 0;
        for (int i = 1; i < countBits + 1; i++) {
            lengthOfBitsArray[g] = count + 1;
            if (i == 32400 * (g + 1)) {
                g++;
                count = 0;
            } else count++;
        }

        for (int i = 0; i < countEnoughFrames; i++) {
            Color[][] rgbMatrix = rgb_yCrCb_matrix.createRGBMatrix(loader.loadEncryptionFrames(i + 1));
            YCrCb[][] yCrCbMatrix = rgb_yCrCb_matrix.createYCrCbMatrix(rgbMatrix);
            double[][] yMatrix = rgb_yCrCb_matrix.createYMatrix(yCrCbMatrix, 1080, 1920);

            Matrix matY = new Matrix(1080, 1920, yMatrix);

            ArrayList<double[][]> arrayOf8x8TransformedMatrix = waveletTransform.arrayOfMatrixDirectTransform(matY.getArrayOf8x8Matrix(yMatrix));

            for (int j = 0; j < lengthOfBitsArray[i]; j++) {
                double[][] tmpMatrix = arrayOf8x8TransformedMatrix.get(j);

                if (tmpMatrix[a2x][a2y] > tmpMatrix[a1x][a1y])
                    tmpCharArrayList.add('0');
                else tmpCharArrayList.add('1');
            }
            arrayOf8x8TransformedMatrix.clear();
        }

        StringBuilder tmp = new StringBuilder();

        for (char ch : tmpCharArrayList)
            tmp.append(ch);

        String[] bitsStringArray = tmp.toString().split("(?<=\\G.{8})");

        byte[] byteArray = byteTransformation.bitsArrayToByteArray(bitsStringArray);

        byteTransformation.byteArrayToFile(byteArray, "decryption.txt");
    }
}
