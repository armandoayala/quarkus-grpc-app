package org.lab.arm.app.support;

import java.util.List;

public class Util {

    public static double calculateSD(List<Double> values) {
        double sum = 0.0, standardDeviation = 0.0;
        int length = values.size();

        for (double num : values) {
            sum += num;
        }

        double mean = sum / length;

        for (double num : values) {
            standardDeviation += Math.pow(num - mean, 2);
        }

        return Math.sqrt(standardDeviation / length);
    }
}
