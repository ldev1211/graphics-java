package config;

public class MatrixCalculate {
    public static double[][] mulMatrix3x3(double[][] matrixA,double[][] matrixB){
        return new double[][]{
          new double[]{
                  matrixA[0][0]*matrixB[0][0] + matrixA[0][1]*matrixB[1][0] + matrixA[0][2]*matrixB[2][0],
                  matrixA[0][0]*matrixB[0][1] + matrixA[0][1]*matrixB[1][1] + matrixA[0][2]*matrixB[2][1],
                  matrixA[0][0]*matrixB[0][2] + matrixA[0][1]*matrixB[1][2] + matrixA[0][2]*matrixB[2][2]
          },
          new double[]{
                  matrixA[1][0]*matrixB[0][0] + matrixA[1][1]*matrixB[1][0] + matrixA[1][2]*matrixB[2][0],
                  matrixA[1][0]*matrixB[0][1] + matrixA[1][1]*matrixB[1][1] + matrixA[1][2]*matrixB[2][1],
                  matrixA[1][0]*matrixB[0][2] + matrixA[1][1]*matrixB[1][2] + matrixA[1][2]*matrixB[2][2]
          },
          new double[]{
                  matrixA[2][0]*matrixB[0][0] + matrixA[2][1]*matrixB[1][0] + matrixA[2][2]*matrixB[2][0],
                  matrixA[2][0]*matrixB[0][1] + matrixA[2][1]*matrixB[1][1] + matrixA[2][2]*matrixB[2][1],
                  matrixA[2][0]*matrixB[0][2] + matrixA[2][1]*matrixB[1][2] + matrixA[2][2]*matrixB[2][2]
          }
        };
    }

    public static double[][] mulMatrix1x3(double[][] matrix1x3, double[][] matrix3x3){
        return new double[][]{
                new double[]{
                        matrix1x3[0][0]*matrix3x3[0][0] + matrix1x3[0][1]*matrix3x3[1][0] + matrix1x3[0][2]*matrix3x3[2][0],
                        matrix1x3[0][0]*matrix3x3[0][1] + matrix1x3[0][1]*matrix3x3[1][1] + matrix1x3[0][2]*matrix3x3[2][1],
                        matrix1x3[0][0]*matrix3x3[0][2] + matrix1x3[0][1]*matrix3x3[1][2] + matrix1x3[0][2]*matrix3x3[2][2]
                }
        };
    }
}
