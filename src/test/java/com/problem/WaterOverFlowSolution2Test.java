package com.problem;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.Test;

public class WaterOverFlowSolution2Test {
    private WaterOverFlowSolution2 waterOverFlowSolution2 = new WaterOverFlowSolution2();
    private WaterOverFlowSolution1 waterOverFlowSolution1 = new WaterOverFlowSolution1();

    @Test(expected = IllegalArgumentException.class)
    public void willFail_WhenInputColumnIsGreaterThanLayer() {
        waterOverFlowSolution2.findWaterVolumeInCup(1,2,1.0);
    }
    @Test
    public void shouldBeEqualWithAnotherSolution1() {
        double inputWater = 100.0;
        for (int i = 1; i < 20; i++) {
            for (int j = 1; j <= i; j++)
                assertThat(waterOverFlowSolution2.findWaterVolumeInCup(i, j, inputWater)).isEqualTo(waterOverFlowSolution1.waterVolume(i, j, inputWater));
        }
    }
}
