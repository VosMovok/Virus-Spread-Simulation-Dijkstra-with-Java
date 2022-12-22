package util;

import org.apache.commons.math3.analysis.UnivariateFunction;
import org.apache.commons.math3.analysis.interpolation.SplineInterpolator;

import java.util.Collections;
import java.util.List;

public class Interpolator {
    public static void interpolate (List<Double> xList, List <Double> yList) {
        Double maxX = Collections.max(xList);

        double[] xValues = xList.stream().mapToDouble(Double::doubleValue).toArray(); // x-coordinates of the data points
        double[] yValues = yList.stream().mapToDouble(Double::doubleValue).toArray(); // y-coordinates of the data points

        SplineInterpolator interpolator = new SplineInterpolator();
        UnivariateFunction function = interpolator.interpolate(xValues, yValues);

        xList.clear();
        yList.clear();
        for (double x = 0; x <= maxX; x += 0.1) {
            xList.add(x);
            double y = function.value(x);
            yList.add(y);
        }

    }
}
