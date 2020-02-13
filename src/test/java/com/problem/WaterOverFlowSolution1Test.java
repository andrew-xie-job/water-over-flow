package com.problem;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.Test;

public class WaterOverFlowSolution1Test {

    private WaterOverFlowSolution1 waterOverFlowSolution1 = new WaterOverFlowSolution1();

    @Test(expected = IllegalArgumentException.class)
    public void willFail_WhenInputColumnIsGreaterThanLayer() {
       waterOverFlowSolution1.waterVolume(1,2,1.0);
    }

    @Test
    public void verify_WhenPour10UnitWater() {
        assertThat(waterOverFlowSolution1.waterVolume(1,1,10.0)).isEqualTo(1.0);
        assertThat(waterOverFlowSolution1.waterVolume(2,1,10.0)).isEqualTo(1.0);
        assertThat(waterOverFlowSolution1.waterVolume(2,2,10.0)).isEqualTo(1.0);
        assertThat(waterOverFlowSolution1.waterVolume(3,1,10.0)).isEqualTo(1.0);
        assertThat(waterOverFlowSolution1.waterVolume(3,2,10.0)).isEqualTo(1.0);
        assertThat(waterOverFlowSolution1.waterVolume(3,3,10.0)).isEqualTo(1.0);
        assertThat(waterOverFlowSolution1.waterVolume(4,1,10.0)).isEqualTo(0.375);
        assertThat(waterOverFlowSolution1.waterVolume(4,2,10.0)).isEqualTo(1.0);
        assertThat(waterOverFlowSolution1.waterVolume(4,3,10.0)).isEqualTo(1.0);
        assertThat(waterOverFlowSolution1.waterVolume(4,4,10.0)).isEqualTo(0.375);
        assertThat(waterOverFlowSolution1.waterVolume(5,1,10.0)).isEqualTo(0.0);
        assertThat(waterOverFlowSolution1.waterVolume(5,2,10.0)).isEqualTo(0.3125);
        assertThat(waterOverFlowSolution1.waterVolume(5,3,10.0)).isEqualTo(0.625);
        assertThat(waterOverFlowSolution1.waterVolume(5,4,10.0)).isEqualTo(0.3125);
        assertThat(waterOverFlowSolution1.waterVolume(5,5,10.0)).isEqualTo(0.0);
        assertThat(waterOverFlowSolution1.waterVolume(6,1,10.0)).isEqualTo(0.0);
        assertThat(waterOverFlowSolution1.waterVolume(6,2,10.0)).isEqualTo(0.0);
        assertThat(waterOverFlowSolution1.waterVolume(6,3,10.0)).isEqualTo(0.0);
        assertThat(waterOverFlowSolution1.waterVolume(6,4,10.0)).isEqualTo(0.0);
        assertThat(waterOverFlowSolution1.waterVolume(6,5,10.0)).isEqualTo(0.0);
        assertThat(waterOverFlowSolution1.waterVolume(6,6,10.0)).isEqualTo(0.0);
    }

    @Test
    public void should_TotalWaterVolumeEqualToInput() {
        double inputWater = 10.0;
        double totalWaterInGlasses = 0.0;
        for (int i = 1; i<7; i++) {
            for (int j=1; j<=i; j++) {
                totalWaterInGlasses += waterOverFlowSolution1.waterVolume(i, j, inputWater);
            }
        }
        assertThat(totalWaterInGlasses).isEqualTo(10.0);
    }

    @Test
    public void shouldBeSymmetric() {
        double inputWater = 100.0;
        for (int i = 1; i<20; i++) {
            for (int j=1; j<=i; j++) {
                int symmetricNumber = i-j+1;
                if(symmetricNumber<=0)
                    break;
                assertThat(waterOverFlowSolution1.waterVolume(i, j, inputWater)).isEqualTo(waterOverFlowSolution1.waterVolume(i, symmetricNumber, inputWater));
            }
        }
    }

    @Test
    public void shouldBeEqualWithAnotherIterationSolution() {
        double glassVolume = 1.0;
        double inputWater = 100.0;
        for(int i=1; i<20; i++) {
            for(int j=1; j<=i; j++)
            assertThat(waterOverFlowSolution1.waterVolume(i, j, inputWater)).isEqualTo(findWaterInCup(i, j, glassVolume, inputWater));
        }
    }

    static double findWaterInCup(int layer, int column, final double glassVolume, final double inputVolume) {
        final int returnIndex = (layer * (layer - 1) / 2 + column - 1);
        double[] glass = new double[layer * (layer + 1)/2];
        glass[0] = inputVolume;
        for (int row = 1,index = 0; row <= layer ; ++row) {
            for (int col = 1; col <= row; ++col, ++index) {
                // Get the water from current glass
                double currentVolume = glass[index];
                glass[index] = Math.min(currentVolume, glassVolume);

                if(index == returnIndex)
                    return glass[index];
                if(row < layer) {
                    double remainingVolume = Math.max(currentVolume - glassVolume, 0.0);
                    glass[index + row] += remainingVolume / 2;
                    glass[index + row + 1] += remainingVolume / 2;
                }
            }
        }
        return glass[(layer * (layer - 1) / 2 + column - 1)];
    }

}