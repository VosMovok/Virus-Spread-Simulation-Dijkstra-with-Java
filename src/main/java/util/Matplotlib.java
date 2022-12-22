package util;

import com.github.sh0nk.matplotlib4j.Plot;
import com.github.sh0nk.matplotlib4j.PythonExecutionException;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.TreeMap;


public class Matplotlib {
    public static void dataToPlot(TreeMap<Double, Double> data) throws PythonExecutionException, IOException {
        List<Double> keys = new ArrayList<>(data.keySet());
        List<Double> values = new ArrayList<>();
        for (Double key : keys) {
            values.add(data.get(key));
        }
        Double maxX = Collections.max(keys);
        Double maxY = Collections.max(values);
        Plot plt = Plot.create();
        plt.figure("Graph");
        plt.plot()
                .add(keys, values)
                .label("Days to Infected")
                .color("b");
        Interpolator.interpolate(keys, values);
        plt.plot()
                .add(keys, values)
                .label("Days to Infected")
                .color("r");
        plt.xlim(0, maxX);
        plt.ylim(0, maxY + 1);
        plt.xlabel("Days");
        plt.ylabel("Infected");
        plt.title("Line Graph of Days Infected");
        plt.legend();
        plt.show();
    }
}
