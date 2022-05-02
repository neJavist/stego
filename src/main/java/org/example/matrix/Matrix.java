package org.example.matrix;

import java.util.ArrayList;

public class Matrix {
    private final int rowsLength;
    private final int columnsLength;
    private double[][] matrix;

    public Matrix(int rowsLength, int columnsLength, double[][] matrix) {
        this.rowsLength = rowsLength;
        this.columnsLength = columnsLength;
        this.matrix = matrix;
    }

    public ArrayList<Double> getRow(int numOfRow) {
        ArrayList<Double> row = new ArrayList<>();
        for (int j = 0; j < columnsLength; j++)
            row.add(matrix[numOfRow][j]);
        return row;
    }

    public ArrayList<Double> getColumn(int numOfColumn) {
        ArrayList<Double> column = new ArrayList<>();
        for (int i = 0; i < rowsLength; i++)
            column.add(matrix[i][numOfColumn]);
        return column;
    }

    public void setRow(int numOfRow, ArrayList<Double> row, double[][] matrix) {
        for (int j = 0; j < columnsLength; j++)
            matrix[numOfRow][j] = row.get(j);
        setMatrix(matrix);
    }

    public void setColumn(int numOfColumn, ArrayList<Double> column, double[][] matrix) {
        for (int i = 0; i < columnsLength; i++)
            matrix[i][numOfColumn] = column.get(i);
        setMatrix(matrix);
    }

    public void reverseMatrix(double[][] matrix) {
        for (int i = 0; i < matrix.length; i++) {
            for (int j = 0; j < matrix.length - i; j++) {
                double tmp = matrix[i][j];
                matrix[i][j] = matrix[matrix.length - 1 - j][matrix.length - 1 - i];
                matrix[matrix.length - 1 - j][matrix.length - 1 - i] = tmp;
            }
        }
        setMatrix(matrix);
    }

    public ArrayList<double[][]> getArrayOf8x8Matrix(double[][] mainMatrix) {
        ArrayList<double[][]> arrayOf8x8Matrix = new ArrayList<>();
        for (int i = 0; i < 1080; i += 8) {
            for (int j = 0; j < 1920; j += 8) {
                arrayOf8x8Matrix.add(
                        new double[][]{
                                {mainMatrix[i][j], mainMatrix[i][j + 1], mainMatrix[i][j + 2], mainMatrix[i][j + 3], mainMatrix[i][j + 4], mainMatrix[i][j + 5], mainMatrix[i][j + 6], mainMatrix[i][j + 7]},
                                {mainMatrix[i + 1][j], mainMatrix[i + 1][j + 1], mainMatrix[i + 1][j + 2], mainMatrix[i + 1][j + 3], mainMatrix[i + 1][j + 4], mainMatrix[i + 1][j + 5], mainMatrix[i + 1][j + 6], mainMatrix[i + 1][j + 7]},
                                {mainMatrix[i + 2][j], mainMatrix[i + 2][j + 1], mainMatrix[i + 2][j + 2], mainMatrix[i + 2][j + 3], mainMatrix[i + 2][j + 4], mainMatrix[i + 2][j + 5], mainMatrix[i + 2][j + 6], mainMatrix[i + 2][j + 7]},
                                {mainMatrix[i + 3][j], mainMatrix[i + 3][j + 1], mainMatrix[i + 3][j + 2], mainMatrix[i + 3][j + 3], mainMatrix[i + 3][j + 4], mainMatrix[i + 3][j + 5], mainMatrix[i + 3][j + 6], mainMatrix[i + 3][j + 7]},
                                {mainMatrix[i + 4][j], mainMatrix[i + 4][j + 1], mainMatrix[i + 4][j + 2], mainMatrix[i + 4][j + 3], mainMatrix[i + 4][j + 4], mainMatrix[i + 4][j + 5], mainMatrix[i + 4][j + 6], mainMatrix[i + 4][j + 7]},
                                {mainMatrix[i + 5][j], mainMatrix[i + 5][j + 1], mainMatrix[i + 5][j + 2], mainMatrix[i + 5][j + 3], mainMatrix[i + 5][j + 4], mainMatrix[i + 5][j + 5], mainMatrix[i + 5][j + 6], mainMatrix[i + 5][j + 7]},
                                {mainMatrix[i + 6][j], mainMatrix[i + 6][j + 1], mainMatrix[i + 6][j + 2], mainMatrix[i + 6][j + 3], mainMatrix[i + 6][j + 4], mainMatrix[i + 6][j + 5], mainMatrix[i + 6][j + 6], mainMatrix[i + 6][j + 7]},
                                {mainMatrix[i + 7][j], mainMatrix[i + 7][j + 1], mainMatrix[i + 7][j + 2], mainMatrix[i + 7][j + 3], mainMatrix[i + 7][j + 4], mainMatrix[i + 7][j + 5], mainMatrix[i + 7][j + 6], mainMatrix[i + 7][j + 7]},
                        });
            }
        }

        return arrayOf8x8Matrix;
    }

    public double[][] getBigMatrix(ArrayList<double[][]> arrayOf8x8Matrix) {
        double[][] bigMatrix = new double[1080][1920];
        int count = 0;
        for (int i = 0; i < 1080; i += 8) {
            for (int j = 0; j < 1920; j += 8, count++) {
                double[][] tmpMatrix = arrayOf8x8Matrix.get(count);
                bigMatrix[i][j] = tmpMatrix[0][0];
                bigMatrix[i][j + 1] = tmpMatrix[0][1];
                bigMatrix[i][j + 2] = tmpMatrix[0][2];
                bigMatrix[i][j + 3] = tmpMatrix[0][3];
                bigMatrix[i][j + 4] = tmpMatrix[0][4];
                bigMatrix[i][j + 5] = tmpMatrix[0][5];
                bigMatrix[i][j + 6] = tmpMatrix[0][6];
                bigMatrix[i][j + 7] = tmpMatrix[0][7];
                bigMatrix[i + 1][j] = tmpMatrix[1][0];
                bigMatrix[i + 1][j + 1] = tmpMatrix[1][1];
                bigMatrix[i + 1][j + 2] = tmpMatrix[1][2];
                bigMatrix[i + 1][j + 3] = tmpMatrix[1][3];
                bigMatrix[i + 1][j + 4] = tmpMatrix[1][4];
                bigMatrix[i + 1][j + 5] = tmpMatrix[1][5];
                bigMatrix[i + 1][j + 6] = tmpMatrix[1][6];
                bigMatrix[i + 1][j + 7] = tmpMatrix[1][7];
                bigMatrix[i + 2][j] = tmpMatrix[2][0];
                bigMatrix[i + 2][j + 1] = tmpMatrix[2][1];
                bigMatrix[i + 2][j + 2] = tmpMatrix[2][2];
                bigMatrix[i + 2][j + 3] = tmpMatrix[2][3];
                bigMatrix[i + 2][j + 4] = tmpMatrix[2][4];
                bigMatrix[i + 2][j + 5] = tmpMatrix[2][5];
                bigMatrix[i + 2][j + 6] = tmpMatrix[2][6];
                bigMatrix[i + 2][j + 7] = tmpMatrix[2][7];
                bigMatrix[i + 3][j] = tmpMatrix[3][0];
                bigMatrix[i + 3][j + 1] = tmpMatrix[3][1];
                bigMatrix[i + 3][j + 2] = tmpMatrix[3][2];
                bigMatrix[i + 3][j + 3] = tmpMatrix[3][3];
                bigMatrix[i + 3][j + 4] = tmpMatrix[3][4];
                bigMatrix[i + 3][j + 5] = tmpMatrix[3][5];
                bigMatrix[i + 3][j + 6] = tmpMatrix[3][6];
                bigMatrix[i + 3][j + 7] = tmpMatrix[3][7];
                bigMatrix[i + 4][j] = tmpMatrix[4][0];
                bigMatrix[i + 4][j + 1] = tmpMatrix[4][1];
                bigMatrix[i + 4][j + 2] = tmpMatrix[4][2];
                bigMatrix[i + 4][j + 3] = tmpMatrix[4][3];
                bigMatrix[i + 4][j + 4] = tmpMatrix[4][4];
                bigMatrix[i + 4][j + 5] = tmpMatrix[4][5];
                bigMatrix[i + 4][j + 6] = tmpMatrix[4][6];
                bigMatrix[i + 4][j + 7] = tmpMatrix[4][7];
                bigMatrix[i + 5][j] = tmpMatrix[5][0];
                bigMatrix[i + 5][j + 1] = tmpMatrix[5][1];
                bigMatrix[i + 5][j + 2] = tmpMatrix[5][2];
                bigMatrix[i + 5][j + 3] = tmpMatrix[5][3];
                bigMatrix[i + 5][j + 4] = tmpMatrix[5][4];
                bigMatrix[i + 5][j + 5] = tmpMatrix[5][5];
                bigMatrix[i + 5][j + 6] = tmpMatrix[5][6];
                bigMatrix[i + 5][j + 7] = tmpMatrix[5][7];
                bigMatrix[i + 6][j] = tmpMatrix[6][0];
                bigMatrix[i + 6][j + 1] = tmpMatrix[6][1];
                bigMatrix[i + 6][j + 2] = tmpMatrix[6][2];
                bigMatrix[i + 6][j + 3] = tmpMatrix[6][3];
                bigMatrix[i + 6][j + 4] = tmpMatrix[6][4];
                bigMatrix[i + 6][j + 5] = tmpMatrix[6][5];
                bigMatrix[i + 6][j + 6] = tmpMatrix[6][6];
                bigMatrix[i + 6][j + 7] = tmpMatrix[6][7];
                bigMatrix[i + 7][j] = tmpMatrix[7][0];
                bigMatrix[i + 7][j + 1] = tmpMatrix[7][1];
                bigMatrix[i + 7][j + 2] = tmpMatrix[7][2];
                bigMatrix[i + 7][j + 3] = tmpMatrix[7][3];
                bigMatrix[i + 7][j + 4] = tmpMatrix[7][4];
                bigMatrix[i + 7][j + 5] = tmpMatrix[7][5];
                bigMatrix[i + 7][j + 6] = tmpMatrix[7][6];
                bigMatrix[i + 7][j + 7] = tmpMatrix[7][7];
            }
        }
        return bigMatrix;
    }

    public double[][] getMatrix() {
        return this.matrix;
    }

    public void setMatrix(double[][] matrix) {
        this.matrix = matrix;
    }
}
