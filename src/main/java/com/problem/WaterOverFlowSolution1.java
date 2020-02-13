package com.problem;

public class WaterOverFlowSolution1 {
    private static double CUP_CAPACITY = 1.0;
    private static double ZERO_CAPACITY = 0.0;

    private double inflow(int layer, int column, double totalInjectVolume) {
        if(column == 0 || column == layer + 1) {
            return 0.0;
        } else if ((column == 1) && (layer == 1)) {
            return totalInjectVolume;
        }
        return outflow(layer - 1, column - 1, totalInjectVolume)/2.0 + outflow(layer - 1, column, totalInjectVolume)/2.0;
    }

    private double outflow(int level, int column, double totalInjectVolume) {
        return Math.max(ZERO_CAPACITY, inflow(level , column , totalInjectVolume) - CUP_CAPACITY);
    }

    public double waterVolume(int layer, int column, double totalInjectVolume) {
        if(column > layer) {
            throw new IllegalArgumentException("column is greater than layer");
        }
        return Math.min(CUP_CAPACITY, inflow(layer, column, totalInjectVolume));
    }
}
