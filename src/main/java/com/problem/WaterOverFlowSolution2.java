package com.problem;

import static java.lang.System.exit;

import java.util.Scanner;

public class WaterOverFlowSolution2 {
    private static double CUP_CAPACITY = 1.0;  //set 1 unit = 250ml
    private static double ZERO = 0.0;

    public double findWaterVolumeInCup(int layer, int column, double inputWaterVolume) {
        if(column > layer) {
            throw new IllegalArgumentException("column is greater than layer");
        }
        double[] glassesOfLastLayer = pourWaterFromTop(layer, inputWaterVolume);
        return  Math.min(CUP_CAPACITY, glassesOfLastLayer[column -1]);
    }

    private double[] pourWaterFromTop(final int toLayer, final double inputWaterVolume) {
        int FIRST_LAYER = 1;
        double[] glasses = new double[FIRST_LAYER];
        glasses[0] = inputWaterVolume;
        if(toLayer == FIRST_LAYER) {
            return glasses;
        }
        return pourWaterToLastLayer(FIRST_LAYER + 1, toLayer, glasses) ;
    }

    private double[] pourWaterToLastLayer(final int fromLayer, final int toLayer, final double[] inputGlasses) {
        double[] nextLayerGlasses = new double[fromLayer + 1];
        for(int i=0; i<inputGlasses.length; i++) {
            double overFlowVolume =  Math.max(ZERO, inputGlasses[i] - CUP_CAPACITY);
            nextLayerGlasses[i] += overFlowVolume/2.0;
            nextLayerGlasses[i + 1] += overFlowVolume/2.0;
        }
        if(fromLayer == toLayer) {
            return nextLayerGlasses;
        }
        return pourWaterToLastLayer(fromLayer + 1, toLayer,nextLayerGlasses);
    }


    public static void main(String... args) {
        WaterOverFlowSolution2 waterOverFlow = new WaterOverFlowSolution2();
        Scanner input = new Scanner(System.in);

        while(true) {
            System.out.print("Please Enter glass layer: ");
            int layer = input.nextInt();
            System.out.print("Please Enter glass column: ");
            int column = input.nextInt();
            if(column > layer) {
                System.out.println("Column cannot be greater Layer, please Enter column again:");
                column = input.nextInt();
            }
            System.out.print("Please Enter input volume: ");
            double inputVolume = input.nextInt();
            System.out.println(waterOverFlow.findWaterVolumeInCup(layer, column, inputVolume));

            System.out.print("If You want to continue Y/N? ");
            String line = input.next();
            if(line.equalsIgnoreCase("n")) {
                exit(0);
            }
        }
    }
}
